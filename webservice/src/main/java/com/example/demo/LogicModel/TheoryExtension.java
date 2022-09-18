package com.example.demo.LogicModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static com.example.demo.LogicModel.Rule.RuleType.*;

public class TheoryExtension {
    
    Theory theory;
    Set<Literal> plusDelta; // this is not defined because we will reference directly the theory strictConclusions set
    Set<Literal> minusDelta = new TreeSet<Literal>();
    Set<Literal> plusPartial = new TreeSet<Literal>();
    Set<Literal> minusPartial = new TreeSet<Literal>();

    public TheoryExtension (Theory consumableTheory) {
        this.theory = consumableTheory;
    }

    /**
     * Computes the extension of the given theory. The theory associated with this extension computator will be consumed by the process.
     */
    public void computeExtension() {

        computeStrictExtension();
        computeDefeasibleExtension();

    }
    
    private void computeStrictExtension() {
        computePlusDelta();
        computeMinusDelta();
    }

    private void computePlusDelta() {
        
        plusDelta = theory.getStrictConclusions(); // this was null until now; from now on we use directly theory strictConclusions set
        plusPartial.addAll(plusDelta); // early assignment for optimization

	    // TRIGGER PART (Injection)
        // we remove strict conclusion literals from tails and check if a rule get active by an empty tail
        ArrayList<Literal> injectables = new ArrayList<>(plusDelta);
        ArrayList<Literal> newInjectables = new ArrayList<>();
        while(!injectables.isEmpty()) {
            for (Literal injectable : injectables) {
                for (Rule strictRule: theory.getRules(STRICT)) { // only strict rules
                    if (strictRule.getTail().contains(injectable)) {
                        strictRule.removeFromTail(injectable);
                        if (
                            strictRule.getTail().isEmpty() && // we found an empty tail '-> a' rule
                            plusDelta.add(strictRule.getHead()) // we inject this head iff not already a strict conclusions
                        ) { 
                            Literal newStrictConclusion = strictRule.getHead();
                            newInjectables.add(newStrictConclusion); // while get longer (analogous to fixpoint)
                            newStrictConclusion.setPlusDelta();
                            newStrictConclusion.setPlusPartial(); // early assignment
                            plusPartial.add(newStrictConclusion); // early assignment
                        }
                    }
                }
            }
            injectables = newInjectables; // we move new injectables into main list
            newInjectables.clear(); // we clear the new injectables
        }

    }

    private void computeMinusDelta() {
        
        // to compute minus delta, we want to work only with stict rules
        // having non-empty tail and whose head literal is not already plus delta decided
        
        List<Rule> candidateToMinusDeltaRules = theory.getRules().
            stream().filter(r -> r.isStrict() && !r.isEmptyTail() && !plusDelta.contains(r.getHead())).collect(Collectors.toList());
        
        
        // now we collect all candidateToMinusDeltaRules heads and we count the occurencies
        Map<Literal, Integer> candidateToMinusDeltaHeads = new HashMap<Literal, Integer>();
        for (Rule rule : candidateToMinusDeltaRules) {
            if (candidateToMinusDeltaHeads.containsKey(rule.getHead())) {
                candidateToMinusDeltaHeads.put(rule.getHead(), candidateToMinusDeltaHeads.get(rule.getHead()) + 1); // increment
            } else {
                candidateToMinusDeltaHeads.put(rule.getHead(), 1); // set first occurency
            }
        }
        
        // The initial content of a minus delta set contains all candidateToMinusDeltaRules tail literals...
        // ...which are not head of any candidateToMinusDeltaRules rule: we'll referer to these literals as 'orphans'
        Set<Literal> orphans = new HashSet<>();
        
        // we look for all orphans
        for (Rule rule: candidateToMinusDeltaRules) {
            for (Literal tailLiteral: rule.getTail()) {
                if (!candidateToMinusDeltaHeads.containsKey(tailLiteral)) {
                    orphans.add(tailLiteral);
                }
            }
        }
        
	    // UNTRIGGER PART (Injection)
        // now, injectables deactivate rules: when a candidateToMinusDeltaHeads decrease to zero it becomes a new injectables
        // when a rule is deactivated, it is removed from the Set: this is useful to check at the end those rules who remains
        ArrayList<Literal> injectables = new ArrayList<>(orphans);
        ArrayList<Literal> newInjectables = new ArrayList<>();
        
        while(!injectables.isEmpty()) {
            for (Literal injectable : injectables) {
                Iterator<Rule> ctmrIterator = candidateToMinusDeltaRules.iterator();
                while (ctmrIterator.hasNext()) {
                    Rule rule = ctmrIterator.next();
                    Literal head = rule.getHead();
                    if (rule.getTail().contains(injectable)) {
                        // rule now is not a candidate to -delta anymore, IT IS a -delta, so we remove it from candidates
                        ctmrIterator.remove();
                        // we decrement the occurrencies of its head
                        candidateToMinusDeltaHeads.put(head, candidateToMinusDeltaHeads.get(head) - 1);
                        if (candidateToMinusDeltaHeads.get(head) == 0) { // if we reach count zero, head is definitely a -delta
                            newInjectables.add(head); // while gets longer (as a fixpoint)
                            candidateToMinusDeltaHeads.remove(head); // this is not a candidate -delta literal, IT IS a -delta
                        }
                    }
                } // end iterator loop on candidateToMinusDeltaRules
            } // end for loop on injectables
            injectables = newInjectables; // injectables points to the newInjectable list
            newInjectables = new ArrayList<Literal>(); // newInjectables is created brand new and empty
        } // end fixpoint while

        // at the end of this process, all remaining rules in candidateToMinusDeltaRules are in strict rules loops
        // all remaining literals in candidateToMinusDeltaHeads are DEFINITELY not plus delta, nor minus delta, then UNDECIDABLE
        Set<Literal> undecidables = candidateToMinusDeltaHeads.keySet();
        undecidables.forEach(undecidable -> undecidable.setUnidecidableDelta());
        
        // finally, all literals which are not in plusDelta, nor in undecidables, ARE DEFINITELY minusDelta
        for(Literal literal : theory.getLiterals()) {
            if (!plusDelta.contains(literal) && !undecidables.contains(literal)) {
                minusDelta.add(literal);
            }
        }

    }

    private void computeDefeasibleExtension() {

        // ATTENTION: for this task we will need to check the opposites of literals, but consider that some of
        // the literals have their opposite reference sets to null for optimization purposes.
        // A null opposite reference means that the opposite literal is not present in the theory and by definition
        // it is a -Delta and -Partial literal. Further considerations on null references will be based on this.

        // For this task we need two lists for the while fixpoint
        List<Literal> triggerables = new ArrayList<Literal>(); // at each iteration it'll contain new found +Partial literals
        List<Literal> untriggerables = new ArrayList<Literal>(); // at each iteration it'll contain new found -Partial literals
        
        // we need a list containing the defeasible undecided literals (it'll update at every while iteration getting shorter)
        // At the beginning they are all literals except the plusDelta which already are plusPartial
        Set<Literal> undecideds = theory.getLiterals().
            stream().filter(lit -> !plusDelta.contains(lit)).collect(Collectors.toCollection(HashSet::new));

        // We also need a Set with all strict and defeasible (non defeaters) heads (from now on we call them defeasible heads)
        Set<Literal> defeasibleHeads = theory.getRules(DEFEASIBLE, STRICT). // non defeater, only Rsd
            stream().map(r -> r.getHead()).collect(Collectors.toCollection(HashSet::new));

        // step zero: we fill the list with initial elements
        triggerables.addAll(plusDelta); // simply plusDelta [def(1)]

        // in untriggerable we want literals q which are
        // (q is -delta [def(1)] AND (q is not defeasibleHead [def(2.1)] OR ¬q is +delta [def(2.2)]))
        // these we find are for sure -Partial literals
        for(Literal literal : minusDelta) { // q is -delta
            if (
                !defeasibleHeads.contains(literal) || // q is not defeasibleHead Rsd
                (literal.getOpposite() != null && plusDelta.contains(literal.getOpposite())) // ¬q is +delta
            ) {
                untriggerables.add(literal);
                literal.setMinusPartial(); // everytime we discover an untriggerable, we found a -Partial
                minusPartial.add(literal);
                undecideds.remove(literal); // this is not undecided anymore
            }
        }
        // now it's finally time for the big while fixpoint
        List<Literal> undecidables = new ArrayList<Literal>(); // we use this to store the literals defeasibly undecidables
        while(!triggerables.isEmpty() || !untriggerables.isEmpty()) { // while at least one is not empty
            System.out.println("triggerables: " + triggerables);
            System.out.println("untriggerables: " + untriggerables);
            System.out.println("undiceded: " + undecideds);
            // PHASE 1: we trigger and untrigger the rules with undecided heads
            for(Literal undecided : undecideds) {
                // TRIGGER
                for(Literal triggerable : triggerables) {
                    for(Rule rule : undecided.getRulesIsHeadOf()) {
                        rule.getTail().remove(triggerable); // we remove the triggered from the tail
                        if (rule.getTail().isEmpty() && !rule.isDefeater()) { // if this rule is activated and it's not a defeater
                            undecided.setHasActiveRule(); // sets to true
                            // ATTENTION: we know we don't have supRels with non existing or non complementary rules
                            // because the theory constructor ignored them, so it's not necessary to check it
                            for (Rule rwo : rule.getWinsOver()) {
                                rwo.setLosesAfterActiveRule();
                            }
                        }
                    }
                } // end of TRIGGER
                // UNTRIGGER
                for (Literal untriggerable : untriggerables) {
                    Iterator<Rule> iteratorIsHeadOf = undecided.getRulesIsHeadOf().iterator();
                    while(iteratorIsHeadOf.hasNext()) {
                        Rule rule = iteratorIsHeadOf.next();
                        if (rule.getTail().contains(untriggerable)) { // when a rule is untriggered, it is defintely removed
                            // remove all the superiority relations with r
						    for(Rule rwo : rule.getWinsOver()) {
							    rwo.getLosesAfter().remove(rule);
						    }
						    for(Rule rla : rule.getLosesAfter()) {
							    rla.getWinsOver().remove(rule);
						    }
						    iteratorIsHeadOf.remove(); // the rule and its supRel are defintely removed
                        }
                    }
                } // end UNTRIGGER
            } // end of PHASE 1
            
            // our lists fulfilled their purpose: phase 2 maybe will add literals again
            triggerables.clear();
            untriggerables.clear();

            // PHASE 2: now it's time to decide new literals and, in case, extend the fixpoint
            // we still have our undecided literals: we iterate over them and maybe some of them will be decided and removed
            Iterator<Literal> iteratorUndecideds = undecideds.iterator();
            while(iteratorUndecideds.hasNext()) {
                Literal undecided = iteratorUndecideds.next();
                // PLUS PARTIAL CONTROLS
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
                        for(Rule oppositeRule : undecided.getOpposite().getRulesIsHeadOf()) {
                            if (!oppositeRule.isLosingAfterActiveRule()) {
                                isPlusPartial = false;
                                break; // one is enough
                            }
                        }
                    } // when we are here isPlusPartial is some value
                    if (isPlusPartial) { // if isPlusPartial we are not done yet
                        // TODO?
                        /* if (opposite != null && opposite.isPlusPartial()) {
                          this is a cycle in superiority rules  
                        } */
                        if (opposite == null || opposite.isMinusDelta()) { // if the opposite is -Delta [def(2.2)]
                            // we found a defintely +partial literal
                            undecided.setPlusPartial();
                            plusPartial.add(undecided);
                            triggerables.add(undecided);
                        } else { // if the opposite is not -Delta, this cannot be a +Partial
                            undecided.setUndecidablePartial();
                            undecidables.add(undecided);
                        }
                        iteratorUndecideds.remove(); // we remove it anyway because it's not undecided anymore
                        continue; // we step into the next undecided in while: we don't need to control -Partial anymore
                    }
                }
                // MINUS PARTIAL CONTROLS
                // preconditions: if a literal is a-Delta and the complementary is +Delta, we already decided it as a
                // -Partial by populating the untriggerables on step zero (before the fixpoint): this respects the [def(2.2)]
                // we are not expecting to find these literals here, inside the fixpoint computation
                boolean isMinusPartial = true; // we assume it is and we try to falsify this assumption
                // first check: if this literal is head either of no rule, or only defeaters, it is a -Partial [def(2.1)]
                // we need to do this iterative check all the times because untrigger could have removed some rules
                for(Rule rule : undecided.getRulesIsHeadOf()) {
                    if (!rule.isDefeater()) {
                        isMinusPartial = false; // if the undecided literal is head of a non defeater, we're not sure it is -Partial
                    }
                }
                // if at this point isMinusPartial is true, the information is sufficient
                // if there are rules this undecided literal is head of, we must perform some furher controls
                // these controls involve comparisons with rules of the complementary literal, so it there is no one, we skip them
                if (!isMinusPartial && undecided.getOpposite() != null) {
                    // second check: this undecided literal has the chance to be decided -Partial if an ACTIVE [def(2.3.1)]
                    // rule/defeater for the complementary [def(2.3)] either it doesn't lose against rules of this undecided
                    // at all, or it loses only against defeaters [def(2.3.3)]
                    // otherwise, if it loses against a still activable rule of this undecided we will wait
                    for (Rule oppositeRule : undecided.getOpposite().getRulesIsHeadOf()) {
                        if (oppositeRule.getTail().isEmpty()) { // this opposite rule is active [def(2.3.1)]
                            // we assume again isMinusPartial true in case there are no relSup allowing undecided to beat
                            // the opposite (non entering for loop), or if my opposite rules lose only against my defeaters
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
                    System.out.println(undecided);
                    // there are at least the most important condition to check: undecided must be -Delta
                    if (undecided.isMinusDelta()) { // if it is
                        // we found a defintely -Partial
                        undecided.setMinusPartial();
                        minusPartial.add(undecided);
                        untriggerables.add(undecided);
                    } else { // if it is not
                        // undecided is definitely undecidable, not +Partial nor -Partial
                        undecided.setUndecidablePartial();
                        undecidables.add(undecided);
                    }
                    iteratorUndecideds.remove(); // we remove it anyway because it's not undecided anymore
                }

            } // end of undecideds and end of phase 2

        } // end big fixpoint while

    }

    public String getPlusDeltaString() {
        return this.printSetString(this.plusDelta);
    }
    public String getMinusDeltaString() {
        return this.printSetString(this.minusDelta);
    }
    public String getPlusPartialString() {
        return this.printSetString(this.plusPartial);
    }
    public String getMinusPartialString() {
        return this.printSetString(this.minusPartial);
    }
    public String printSetString(Set<Literal> set) {
        if (set.isEmpty()) {
            return "∅";
        }
        else {
            String s = set.toString();
            return s.substring(1, s.length()-1);
        }
    }
    
}