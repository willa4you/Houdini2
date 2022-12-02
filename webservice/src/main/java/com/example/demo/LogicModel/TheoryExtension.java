package com.example.demo.LogicModel;

import java.util.*;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.LogicModel.Rule.RuleType;
import static com.example.demo.LogicModel.Rule.RuleType.*;
import static com.example.demo.LogicModel.Literal.LiteralType.*;

public class TheoryExtension {

    private boolean ambiguityPropagation;

    private List<Literal> literals = new ArrayList<Literal>(); // constructor already checks there will be no duplicates
    private List<Rule> rules = new ArrayList<Rule>(); // there can not be duplicate rules by design: if duplicate name is found, program halts
    
    private Set<Literal> plusDelta = new HashSet<Literal>(); // we don't want duplicates and later we'll need to do contains in O(1)
    List<Literal> minusDelta = new ArrayList<Literal>(); // no contains, no removes, only adds and iterations
    List<Literal> plusPartial = new ArrayList<Literal>(); // no contains, no removes, only adds and iterations
    List<Literal> minusPartial = new ArrayList<Literal>(); // no contains, no removes, only adds and iterations
    Set<Literal> undecidablesDeltaInStrictRulesLoop; // we need a Set for future contains in O(1)
    List<Literal> undecidablesPartialInRulesLoop;
    Set<Literal> ambiguousLiterals = new HashSet<Literal>(); // we don't want duplicates
    private String nonPresentOpposites = "";

    public TheoryExtension (String JSONTheoryString, boolean ambiguityPropagation) throws IOException {
        
        this.ambiguityPropagation = ambiguityPropagation;

        // important: in order to retrieve the original literal every time we encounter the same JSON literal
        // we implement a Map where key = value, which allows us to look for a literal and retrieve it
        Map<Literal, Literal> mapOfLiterals = new HashMap<>();

        // TODO: this is based on a custom formalization of JSON, currently not accepted in the project
        byte[] jsonData = JSONTheoryString.getBytes();
        ObjectMapper objectMapper = new ObjectMapper(); //create ObjectMapper instance
        JsonNode rootNode = objectMapper.readTree(jsonData); //read JSON like DOM Parser
        
        // extract facts
        JsonNode factsNode = rootNode.path("facts");
        Iterator<JsonNode> elements = factsNode.elements();
        while(elements.hasNext()){
            JsonNode factNode = elements.next();
            Literal fact = getLiteralAndUpdateTheory(factNode, mapOfLiterals);
            setPlusDeltaAndPlusPartial(fact);
        }
        // end facts
        
        // extract rules
        // we create a map to check duplicate names which we will use to retrieve rules when evaluating superiority relations
        Map<String, Rule> mapOfRules = new HashMap<String, Rule>();
        
        JsonNode rulesNode = rootNode.path("rules");
        elements = rulesNode.elements(); // reusing "elements" variable
        while(elements.hasNext()) {
            JsonNode ruleNode = elements.next();
            String ruleLabel = ruleNode.get("label").asText();
            
            // checking if there are two rules with the same name because it's forbidden
            if(mapOfRules.get(ruleLabel) != null) {
                System.out.println("Rules with the same name are not allowed in a well formed theory.");
                System.exit(1);
                // TODO: here we must throw an exception or something;
            }
            RuleType ruleType;
            switch(ruleNode.get("type").asText()) {
                case "strict" : ruleType = STRICT; break;
                case "defeasible" : ruleType = DEFEASIBLE; break;
                case "defeater" : ruleType = DEFEATER; break;
                default : ruleType = STRICT; // TODO: this should be already checked
            }
            Literal head = getLiteralAndUpdateTheory(ruleNode.get("head"), mapOfLiterals);
            JsonNode tailNode = ruleNode.get("tail");
            HashSet<Literal> tail = new HashSet<>(); // TODO: should duplicate literals in a tail be allowed?
            
            // creating new rule (tail is empty now, but we'are going to pouplate it)
            Rule rule = new Rule(ruleLabel, ruleType, head, tail);
            
            head.addToRulesIsHeadOf(rule); // adding this rule to the rules this literal is head of
            Iterator<JsonNode> JSONtail = tailNode.elements(); // visiting tail in JSON
            while(JSONtail.hasNext()) {
                Literal tailLiteral = getLiteralAndUpdateTheory(JSONtail.next(), mapOfLiterals);
                tail.add(tailLiteral);
                tailLiteral.addToRulesIsTailOf(rule);
            }

            // if this is an empty tail rule, we can early assign some conclusions
            if (tail.isEmpty()) {
                if (ruleType == DEFEASIBLE) {// rules of type '=> a'
                    head.setHasActiveRule(); // head literals is now known to have an active rule
                }
                else if (ruleType == STRICT) {// rules of type '-> a'
                    head.setHasActiveRule(); // head literals is now known to have an active rule
                    setPlusDeltaAndPlusPartial(head); // head is like a fact
                }
                // defeaters with empty tail don't need any assignment
            }

            rules.add(rule); // adding this rule to the theory global list rules
            mapOfRules.put(ruleLabel, rule);// adding this rule to the local map for future checks
        } // end json rules while
        // end rules
            
        // extract superiority relations
        JsonNode supRelsNode = rootNode.path("superiorityRelations");
        elements = supRelsNode.elements(); // reusing elements variable
        while(elements.hasNext()) {
            JsonNode supRelNode = elements.next();
            Rule superior = mapOfRules.get(supRelNode.get("superior").asText()); // retrieving rule by label
            Rule inferior = mapOfRules.get(supRelNode.get("inferior").asText()); // retrieving rule by label
            // we want to avoid relations referring to non existing rules (non esistent names)
            if (superior != null && inferior != null) { // if both names are presents and rules are taken from the map
                // now we also want to avoid superiority relations about non complementary heads
                if (superior.getHead() == inferior.getHead().getOpposite()) { // we use == because our references are coherent
                    // superiority relations are stored into rules themselves: no needs for explicit objects
                    superior.addToWinsOver(inferior);
                    inferior.addToLosesAfter(superior);
                    // if superior rule is active, inferior must know
                    if ((superior.isDefeasible() || superior.isStrict()) && superior.isEmptyTail()) {
                        inferior.setLosesAfterActiveRule();
                    }
                } else { // non complementary heads
                    // TODO: WARNING superiority relations with non complementary heads exception
                    System.out.println("Non complementary head rules found in a superiority relation:");
                    System.out.println(supRelNode.asText());
                }
            } else { // wrong rule names
                // TODO: WARNING superiority relations with wrong name exception
                System.out.println("Wrong rule name found in a superiority relation:");
                System.out.println(supRelNode.asText());
            }
        } // end while superiority relations
        // end superiority relations

    } // end constructor

    /**
     * (Used only by the constructor) Checks the JSON literal and if present, returns the existing one.
     * If not present, checks if it is the complementary of an existing literal and link each other,
     * then adds the new one to the literals map for further checks and global literals list.
     * @param literalNode The literal JSON object
     * @param mapOfLiterals A Map containing the already created literals (modified by the process)
     * @return The literal object, new or existing, associated to the literal JSON object.
     */
    private Literal getLiteralAndUpdateTheory(JsonNode literalNode, Map<Literal, Literal> mapOfLiterals) {
        String literalLabel = literalNode.get("label").asText();
        String literalType = literalNode.get("type").asText();
        // first we create an instance of an object associated to the JSON literal
        Literal newLiteral = new Literal(
            literalLabel,
            (literalType.equals("positive") ? POSITIVE : NEGATIVE)
        );

        Literal actualLiteral = mapOfLiterals.get(newLiteral);
        if (actualLiteral != null) { // already in the map
            return actualLiteral;
        }

        // IF IT IS A BRAND NEW LITERAL
        // first we check if it is complementary of an existing one and, in case, we link each other
        Literal opposite = mapOfLiterals.get(
            new Literal(
                literalLabel, // this works only with a JSON structure where complementary literals shares the same label
                (literalType.equals("positive") ? NEGATIVE : POSITIVE)
            ) // same label, but second parameter is inverted
        ); // we try and get the existing opposite, if present
                
        if (opposite != null) { // if the opposite is present in the literals map
            // then we must link each other complmentary literal
            opposite.setOpposite(newLiteral);
            newLiteral.setOpposite(opposite);
        } // if the opposite is not present we do nothing
                
        // we put the new Literal for the future and we check if it is opposite of an existing one
        mapOfLiterals.put(newLiteral, newLiteral); // this map has key = value
        literals.add(newLiteral); // we also add it to the literals global list: now we're sure it has no duplicates

        return newLiteral;
    
    }

    private void setPlusDeltaAndPlusPartial(Literal literal) {
        plusDelta.add(literal);
        literal.setPlusDelta();
        plusPartial.add(literal); // early assignment
        literal.setPlusPartial(); // early assignment
    }


    /**
     * Computes the extension of the given theory.
     * The theory passed to this extension computator will be consumed by the process.
     * @return A reference to the object itself.
     */
    public TheoryExtension computeExtension() {

        computeStrictExtension();
        computeDefeasibleExtension();
        return this;

    }
    
    private void computeStrictExtension() {
        computePlusDelta();
        computeMinusDelta();
    }

    private void computePlusDelta() {
        
	    // TRIGGER PART (Injection)
        // we remove strict conclusion literals from rule tails and check if a strict rule get active by an empty tail
        ArrayList<Literal> injectables = new ArrayList<Literal>(plusDelta); // we start injecting the plusDelta literals
        ArrayList<Literal> newInjectables = new ArrayList<Literal>(); // new found +Delta literals will be stored here until the end of every iteration
        while(!injectables.isEmpty()) {
            for (Literal injectable : injectables) {
                for (Rule ruleToInject : injectable.getRulesIsTailOf()) { // all rules: strict, defeasible, defeaters
                    ruleToInject.removeFromTail(injectable);
                    if (
                        ruleToInject.isStrict() &&
                        ruleToInject.getTail().isEmpty() && // we got an empty tail '-> a' rule
                        !plusDelta.contains(ruleToInject.getHead()) // head is not already a plusDelta
                    ) {
                        setPlusDeltaAndPlusPartial(ruleToInject.getHead());
                        newInjectables.add(ruleToInject.getHead()); // while get longer (analogous to fixpoint)
                    }
                }
                // injectable.getRulesIsTailOf().clear();
            }
            
            injectables = newInjectables; // we get rid of the injected list and set new injectables as the main list
            newInjectables = new ArrayList<Literal>(); // we clear the new injectables
        
        } // end while injectables
        
    }

    private void computeMinusDelta() {
        
        // now, why everything non +PlusDelta is not automatically -Delta? Because of possible loops in strict rules.
        // In order to find them we want to work only with strict rules (1),
        // whose head literal is not already plus delta decided (2): we'll call them candidate rules.
        // Heads of those rules can be either -Delta or undecidableDelta: we'll call them candidate heads.
        // We will find and remove from candidate heads, all the heads defintely -Delta (having a -Delta in every tail 
        // of rules they're head of) until the remainings will be defintely the undecidables (if present)

        // in order to do this we need to collect all the candidate heads and for each one,
        // count in how many candidate rules they're present as head
        Map<Literal, Integer> candidateHeads = new HashMap<Literal, Integer>();

        // we need also the set of certainly -Delta literals we can try and inject to untrigger rules
        // during the first iteration
        // The set of those literals must contain all candidates rules tail literals
        // which are not head of any candidate rule: we'll referer to these literals as 'orphans'
        Set<Literal> orphans = new HashSet<>();

        for (Rule rule : rules) {
            if (rule.isStrict() && !plusDelta.contains(rule.getHead())) { // (1) & (2)
                // first we update the candidate heads situation
                if (candidateHeads.containsKey(rule.getHead())) { // if the head is already present
                    candidateHeads.put(rule.getHead(), candidateHeads.get(rule.getHead()) + 1); // increment
                } else { // if this is the first time we check this head
                    candidateHeads.put(rule.getHead(), 1); // set first occurency
                }
                // temporary, all the tail literals are potentially orphans
                orphans.addAll(rule.getTail());
            }
        }
        
        // now we remove the heads of candidates rules from the orphans
        candidateHeads.keySet().forEach(literal -> orphans.remove(literal));

	    // UNTRIGGER PART (Injection)
        // injectables present in tails deactivate rules: when a candidate head decrease to zero rules,
        // all of its rules have been deactivated and the head becomes a new injectable which will deactivate more rules
        ArrayList<Literal> injectables = new ArrayList<>(orphans); // the first literals to inject are the orphans
        ArrayList<Literal> newInjectables = new ArrayList<>();
        
        while(!injectables.isEmpty()) {
            for (Literal injectable : injectables) {
                for (Rule rule : injectable.getRulesIsTailOf()) {
                    Literal head = rule.getHead();
                    // we decrement the occurrencies of this head because this rule of it has been deactivated
                    candidateHeads.put(head, candidateHeads.get(head) - 1);
                    if (candidateHeads.get(head) == 0) { // if we reach count zero, head is definitely a -delta
                        newInjectables.add(head); // while gets longer (as a fixpoint)
                        candidateHeads.remove(head); // this is not a candidate -delta literal, IT IS a -delta
                    }
                }
            } // end for loop on injectables
            injectables = newInjectables; // injectables points to the newInjectable list
            newInjectables = new ArrayList<Literal>(); // newInjectables is cleared
        } // end fixpoint while

        // at the end of this process, all remaining heads, are heads of rules which are in strict rules loops
        // these heads are DEFINITELY not plus delta, nor minus delta, then UNDECIDABLE
        undecidablesDeltaInStrictRulesLoop = candidateHeads.keySet(); // global variable is now referenced, O(1)
        undecidablesDeltaInStrictRulesLoop.forEach(undecidable -> undecidable.setUndecidableDelta());
        
        // finally, all literals which are not in plusDelta, nor in undecidables, ARE DEFINITELY minusDelta
        // this is valid also and moreover for all the only defeasible literals which we didn't mention before
        // also, all statements which are the opposite of a present literal, but they're not present in the theory
        // belong by definition both to -Delta, and to -Partial; in order to represent them at the end of the process,
        // we produce now the simple String which will represent them
        
        for(Literal literal : literals) { // we iterate over all literals
            
            // checking if literal is -Delta
            if (!plusDelta.contains(literal) && !undecidablesDeltaInStrictRulesLoop.contains(literal)) {
                minusDelta.add(literal);
                literal.setMinusDelta();
            }

            // checking if it has no opposite, so we can add the missing opposite's representation string for final exposition
            if (literal.getOpposite() == null) {
                nonPresentOpposites +=
                    ((literal.isPositive()) ? "~" + literal.getLabel() : literal.getLabel()) + ", "; // this works if negative labels have not the "~"
            }

        }

        // removing last ", " from the non present opposits string
        if (nonPresentOpposites.length() > 0) {
            nonPresentOpposites = nonPresentOpposites.substring(0, nonPresentOpposites.length() - 2);
        }

    }

    private void computeDefeasibleExtension() {

        // ATTENTION: for this task we would need to check complementary literals;
        // it is important to remember that some of the literals have their opposite reference sets to null for optimization purposes.
        // A null opposite reference means that the opposite literal is not present in the theory and by definition
        // it is a -Delta and -Partial literal. Further considerations on null references will be based on this precondition.

        // For this task we'll need two lists for the while fixpoint: both +Partial and -Partial are computed in the same process.
        List<Literal> triggerables = new ArrayList<Literal>(); // at each iteration it'll contain new found +Partial literals
        List<Literal> untriggerables = new ArrayList<Literal>(); // at each iteration it'll contain new found -Partial literals
        
        // we need a list containing all the defeasible undecided literals (it'll get shorter at every while iteration)
        List<Literal> defeasibleUndecideds = new ArrayList<Literal>();

        // At first, these undecideds are all literals which are NOT in +Delta (already directly decided as +Partial),
        // and ALSO except the literals we can decide as -Partial at a specific step zero, before entering fixpoint.
        // These are literals q such as
        // (q is -delta [def(1)] AND (q is not defeasibleHead [def(2.1)] OR ¬q is +delta [def(2.2)]))

        // Hence we need a Set with all strict and defeasible (non defeaters) heads (from now on we call them defeasible heads)
        Set<Literal> defeasibleHeads = new HashSet<Literal>();
        for (Rule rule : rules) {
            if (rule.isStrict() || rule.isDefeasible()) { // non defeater, only Rsd
                defeasibleHeads.add(rule.getHead());
            }
        }

        // so now we split all literals into these three groups (triggerables, untriggerables and defeasibleUndecideds)
        for(Literal literal : literals) {

            // first check: literal is +Partial
            if (literal.isPlusPartial()) { // at this point they're simply the plusDelta [def(1)]
                triggerables.add(literal); // we already set them +Partial
            }
            // second check: literal is -Partial at step zero
            else if (
                literal.isMinusDelta() && // q is -delta [def(1)] AND
                (
                !defeasibleHeads.contains(literal) || // q is not defeasibleHead Rsd [def(2.1)] OR
                (literal.getOpposite() != null && plusDelta.contains(literal.getOpposite())) // ¬q is +delta [def(2.2)] (if ¬q is null it is not +Delta for sure)
                )
            ) {
                untriggerables.add(literal);
                literal.setMinusPartial(); // everytime we discover an untriggerable, we found a -Partial
                minusPartial.add(literal);
            } else { // if literal is not triggerable nor untriggerable at step/zero, it's a yet undecided
                defeasibleUndecideds.add(literal);
            }
            
        }
        // now it's finally time for the big while fixpoint
        do { // we enter a while loop with a mandatory first iteration (in order to find irrefutables)
            // PHASE 1: we trigger and untrigger the rules with undecided heads
            // TRIGGER
            for(Literal triggerable : triggerables) {
                for(Rule ruleIsTailOf : triggerable.getRulesIsTailOf()) {
                    ruleIsTailOf.removeFromTail(triggerable); // a certain +Partial triggerable can be removed from tails
                    // now we check if this rule gets activated
                    if (ruleIsTailOf.isEmptyTail() && !ruleIsTailOf.isDefeater()) { // if this rule is activated and it's not a defeater
                        ruleIsTailOf.getHead().setHasActiveRule(); // sets to true: the literal knows is head of an active rule
                        // ATTENTION: we know we don't have supRels with non existing or non complementary rules
                        for (Rule rwo : ruleIsTailOf.getWinsOver()) {
                            rwo.setLosesAfterActiveRule(); // every inferior rule now knows that it's losing after an active rule
                        }
                    }
                }
            } // end of TRIGGER injection
            // UNTRIGGER
            for(Literal untriggerable : untriggerables) {
                for(Rule ruleIsTailOf : untriggerable.getRulesIsTailOf()) { // when a rule is untriggered, must be defintely removed
                    // remove all the superiority relations in other rules involving this rule
                    for(Rule rwo : ruleIsTailOf.getWinsOver()) {
                        rwo.getLosesAfter().remove(ruleIsTailOf);
                    }
                    for(Rule rla : ruleIsTailOf.getLosesAfter()) {
                        rla.getWinsOver().remove(ruleIsTailOf);
                    }
                    ruleIsTailOf.getHead().getRulesIsHeadOf().remove(ruleIsTailOf); // head literal doesn't consider this rule anymore

                    // AMBIGUITY PROPAGATION
                    // An ambiguity can be propagated only by -Partial literals (firsts to become ambiguous are -Partial by definition)
                    // an ambiguity propagation can occure when we untrigger a rule deriving by an ambiguous literal:
                    // this elimination comes from a literal which is -Partial, but somehow it was derived, or derivable,
                    // THEREFORE it's not completely false too...
                    // THEN the head of this rule is compromised, AND ALSO its opposite! (But not for ambiguity blocking)
                    if (ambiguityPropagation && untriggerable.isAmbiguous()) {
                        ruleIsTailOf.getHead().setAmbiguous();
                        ambiguousLiterals.add(ruleIsTailOf.getHead());
                        if (ruleIsTailOf.getHead().getOpposite() != null) {
                            ruleIsTailOf.getHead().getOpposite().setAmbiguous();
                            ambiguousLiterals.add(ruleIsTailOf.getHead().getOpposite());
                        }
                    }

                }
            } // end UNTRIGGER
            // end of PHASE 1
            
            // our lists fulfilled their purpose: phase 2 maybe will add literals again
            triggerables.clear();
            untriggerables.clear();

            // PHASE 2: now it's time to decide new literals and, in case, extend the fixpoint
            // we still have our undecided literals: we iterate over them and maybe some of them will be decided and removed
            Iterator<Literal> iteratorUndecideds = defeasibleUndecideds.iterator();
            while(iteratorUndecideds.hasNext()) {
                Literal undecided = iteratorUndecideds.next();
                // -------------- PLUS PARTIAL CONTROLS --------------
                if (undecided.hasActiveRule()) {
                    boolean isPlusPartial;
                    Literal opposite = undecided.getOpposite();
                    if (opposite == null) { // if the undecided has an active rule and no rules for the opposite [def(2.3.1)]
                        isPlusPartial = true; // we found a new plus partial [def(2.3.1)]
                    } else { // the opposite is not null
                        isPlusPartial = true; // we assume it is true and we try to falsify soon after
                        // it remains true if we find no rules for the opposite (by non entering the for each loop) [def(2.3.1)]
                        // in order to falsify we need to find at least one rule for the opposite which either doesn't lose or
                        // it loses only after defeaters (hence, isLosingAfterActivRule is false for it)
                        // if we don't find any (meaning all oppositeRules have isLosingAfterActiveRule as true)
                        // then all opposite rules lose after an active Rsd rule of mine [def(2.3.3)]
                        for(Rule oppositeRule : opposite.getRulesIsHeadOf()) {
                            if (!oppositeRule.isLosingAfterActiveRule()) {
                                isPlusPartial = false;
                                break; // one is enough
                            }
                        }
                    } // when we are here isPlusPartial is some value
                    // if isPlusPartial is false, we don't know yet, and we wait for -Partial controls and/or for further iterations
                    if (isPlusPartial) { // if isPlusPartial is true, we are not done yet
                        // TODO ?
                        /* if (opposite != null && opposite.isPlusPartial()) {
                          this is a cycle in superiority rules  
                        } */
                        if (opposite == null || opposite.isMinusDelta()) { // if the opposite is -Delta [def(2.2)]
                            
                            // AMBIGUITY PROPAGATION CHECK
                            // if a literal is ambiguous AND we are computing with ambiguity propagation
                            // NOW it's time to put it in -Partial IN CONTRAST WITH THE AMBIGUITY BLOCKING
                            if (ambiguityPropagation && undecided.isAmbiguous()) {
                                undecided.setMinusPartial();
                                minusPartial.add(undecided);
                                untriggerables.add(undecided);
                            } else {
                               // we found a definitve +Partial literal
                                undecided.setPlusPartial();
                                plusPartial.add(undecided);
                                triggerables.add(undecided); 
                            }
                        } else { // if the opposite is not -Delta, this cannot be a +Partial
                            undecided.setUndecidablePartial();
                        }
                        iteratorUndecideds.remove(); // we remove it from undecideds in any case because it's not undecided anymore
                        continue; // we step into the next undecided in while: we don't need to control -Partial anymore
                    }
                } // end of undecided has active rule

                // -------------- MINUS PARTIAL CONTROLS --------------
                // preconditions: if a literal is a-Delta and the complementary is +Delta, we already decided it as a
                // -Partial by populating the untriggerables on step zero (before the fixpoint): this respects the [def(2.2)]
                // we are not expecting to find these literals here, inside the fixpoint computation
                boolean isMinusPartial = true; // we assume it is and we try to falsify this assumption
                // first check: if this literal is head either of no rule, or only defeaters, it is a -Partial [def(2.1)]
                // TODO: maybe this loop is not necessary because we already checked
                for(Rule rule : undecided.getRulesIsHeadOf()) {
                    if (!rule.isDefeater()) {
                        isMinusPartial = false; // if the undecided literal is head of a non defeater, we're not sure it is -Partial
                        break; // one is enough
                    }
                }
                // if at this point isMinusPartial is true, this information is sufficient

                // otherwise, if there are rules this undecided literal is head of, we must perform some furher controls;
                // these controls involve comparisons with rules of the complementary literal: if there is no one, we skip them
                // having isMinusPartial == false and no opposite, means that we must do nothing and wait for now:
                // if there are Rsd that this literal is head of and no opposite, either these rules will get untriggered and
                // disappear (and this literal will become -Partial), or they will be activated and this literal will be +Partial
                if (!isMinusPartial && undecided.getOpposite() != null) {
                    // second check: this undecided literal has the chance to be decided -Partial if an ACTIVE [def(2.3.1)]
                    // rule/defeater for the complementary [def(2.3)] either it doesn't lose against rules of this undecided
                    // at all, or it loses only against defeaters [def(2.3.3)]
                    // otherwise, if it loses against a still activable rule of this undecided we will wait
                    for (Rule oppositeRule : undecided.getOpposite().getRulesIsHeadOf()) {
                        if (oppositeRule.getTail().isEmpty()) { // this opposite rule is active [def(2.3.1)]
                            // we assume again isMinusPartial true, in case either there are no relSup allowing undecided to beat
                            // the opposite (by non entering for loop), or if opposite rules lose only against undecided defeaters
                            isMinusPartial = true;
                            for(Rule oppositeRuleLosesAfter : oppositeRule.getLosesAfter()) {
                                // non-complementary supRel have been removed by the constructor
                                // but we have now to avoid rules that lose against my defeater
                                if (!oppositeRuleLosesAfter.isDefeater()) {
                                    isMinusPartial = false; // there is still the chance for undecided to be +Partial
                                }
                            }
                        }
                    }
                } // end second check if !minusPartial
                // now we're able to tell if, for most of the conditions, this undecided is strongly candidate for -Partial
                if (isMinusPartial) {
                    // there are at last the most important condition to check: undecided must be -Delta
                    if (undecided.isMinusDelta()) { // if it is
                        // we found a defintely -Partial
                        undecided.setMinusPartial();
                        minusPartial.add(undecided);
                        untriggerables.add(undecided);

                        // AMBIGUITY
                        // now, a literal can be -Partial for many reasons, BUT... if BOTH this literal AND
                        // its opposite have an ACTIVE RULE (Rsd), then there must be missing sup rels,
                        // then both of them have to go to -Partial, THEREFORE they're AMBIGUOUS
                        if(undecided.hasActiveRule() && undecided.getOpposite() != null && undecided.getOpposite().hasActiveRule()) {
                            undecided.setAmbiguous();
                            ambiguousLiterals.add(undecided);
                            undecided.getOpposite().setAmbiguous();
                            ambiguousLiterals.add(undecided.getOpposite());
                            System.out.println("Ambiguous: '" + undecided + "' and '" + undecided.getOpposite()+"'");
                            // TODO this happens twice, for p and ~p, but it's not a problem
                        }

                    } else { // if it is not
                        // undecided is definitely undecidable, not +Partial nor -Partial
                        undecided.setUndecidablePartial();
                    }
                    iteratorUndecideds.remove(); // we remove it from undecideds in any case because it's not undecided anymore
                }

            } // end of undecideds and end of phase 2

            // fixpoint re-enters for another iteration if
            // undecideds is not empty and at least one between triggerables/untriggerables is not empty
        } while((!triggerables.isEmpty() || !untriggerables.isEmpty()) && !defeasibleUndecideds.isEmpty()); // this is a re-enter condition
        // fixpoint exits when either undecideds is empty or both triggerables/untriggerables are empty

        // it is possible that, because of defeasible rules loops, some undecideds remain undecideds:
        // if we have no more triggerables or untriggerables, the undecideds are now defintely not +Partial nor -Partial
        // therefore defintely UNDECIDABLES (remember that being in a loop is not the only reason for a literal to be undecidable)
        undecidablesPartialInRulesLoop = defeasibleUndecideds;
        undecidablesPartialInRulesLoop.forEach(unpar -> unpar.setUndecidablePartial());

    }

    public List<Literal> getLiterals() {
        return this.literals;
    }

    public String getPlusDeltaString() {
        return (plusDelta.isEmpty()) ? "∅" : printSetString(plusDelta);
    }

    public String getMinusDeltaString() {
        if (minusDelta.isEmpty() && nonPresentOpposites.isEmpty()) {
            return "∅";
        }
        else {
            return printSetString(minusDelta) +
            ((minusDelta.isEmpty() || nonPresentOpposites.isEmpty()) ? "" : ", ") +
            nonPresentOpposites;
        }
    }

    public String getPlusPartialString() {
        return (plusPartial.isEmpty()) ? "∅" : printSetString(plusPartial);
    }

    public String getMinusPartialString() {
        if (minusPartial.isEmpty() && nonPresentOpposites.isEmpty()) {
            return "∅";
        }
        else {
            return printSetString(minusPartial) +
            ((minusPartial.isEmpty() || nonPresentOpposites.isEmpty()) ? "" : ", ") +
            nonPresentOpposites;
        }
    }

    public String getUndecidablesDeltaInStrictRulesLoop() {
        return (undecidablesDeltaInStrictRulesLoop.isEmpty()) ? "∅" : printSetString(undecidablesDeltaInStrictRulesLoop);
    }

    public String getUndecidablesPartialInRulesLoop() {
        return (undecidablesPartialInRulesLoop.isEmpty()) ? "∅" : printSetString(undecidablesPartialInRulesLoop);
    }

    public String getAmbiguousLiterals() {
        return (ambiguousLiterals.isEmpty()) ? "∅" : printSetString(ambiguousLiterals);
    }

    public String printSetString(Collection<Literal> collection) {
        String collectionString = collection.toString();
        return collectionString.substring(1, collectionString.length() - 1);
    }
    
}
