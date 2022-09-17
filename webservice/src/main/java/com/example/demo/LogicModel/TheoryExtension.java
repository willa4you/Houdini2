package com.example.demo.LogicModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.example.demo.LogicModel.Rule.RuleType;

public class TheoryExtension {
    
    Theory theory;
    Set<Literal> plusDelta = new TreeSet<Literal>();
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

        long start_time = System.currentTimeMillis();

        computeStrictExtension();
        // TODO: bisogna cazzare le strict rimanenti nelle defeasible in qualche modo
        computeDefeasibleExtension();

    }
    
    private void computeStrictExtension() {
        computePlusDelta();
        computeMinusDelta();
    }

    private void computeDefeasibleExtension() {
        computePlusPartial();
        computeMinusPartial();
    }

    private void computePlusDelta() {
        
        Set<Literal> strictConclusions = theory.getStrictConclusions();

	    // TRIGGER PART (Injection)
        // we remove strict conclusion literals from tails and check if a rule get active by an empty tail
        ArrayList<Literal> injectables = new ArrayList<>(strictConclusions);
        ArrayList<Literal> newInjectables = new ArrayList<>();
        while(!injectables.isEmpty()) {
            for (Literal injectable : injectables) {
                for (Rule strictRule: theory.getRules(RuleType.STRICT)) { // only strict rules
                    if (strictRule.getTail().contains(injectable)) {
                        strictRule.removeFromTail(injectable);
                        if (
                            strictRule.getTail().isEmpty() && // we found an empty tail '-> a' rule
                            strictConclusions.add(strictRule.getHead()) // we inject this head iff not already a strict conclusions
                        ) { 
                            newInjectables.add(strictRule.getHead()); // while get longer (analogous to fixpoint)
                            strictRule.getHead().setPlusDelta();
                            strictRule.getHead().setPlusPartial();
                        }
                    }
                }
            }
            injectables = newInjectables; // we move new injectables into main list
            newInjectables.clear(); // we clear the new injectables
        }

        plusDelta.addAll(strictConclusions);

    }

    private void computeMinusDelta() {
        
        // We want to know the occurrencies of every strict-non-empty-tail-rule head
        Map<Literal, Integer> strictHeads = new HashMap<Literal, Integer>();
        for (Rule rule : theory.getRules(RuleType.STRICT)) {
            if (strictHeads.containsKey(rule.getHead())) {
                strictHeads.put(rule.getHead(), strictHeads.get(rule.getHead())+1);
            } else {
                strictHeads.put(rule.getHead(), 1);
            }
        }

        Set<Literal> strictHeads = theory.getRules().
            stream().filter(r -> r.isStrict() && !r.isEmptyTail()).map(r -> r.getHead()).collect(Collectors.toSet());
        
        Set<Literal> toMinusDelta = theory.getLiterals().
            stream().filter(literal -> !(plusDelta.contains(literal) || strictHeads.contains(literal))).collect(Collectors.toSet());
        
         
        for(Literal strictHead : strictHeads)
            untriggerHeads.put(strictHead, 0);
        

        while(!toMinusDelta.isEmpty()){
            toMinusDelta.removeAll(removeFromCandidates);
            toMinusDelta.forEach(c -> c.setDeltaState(State.MINUS));
            extension.getMinusDelta().addAll(toMinusDelta);
            toMinusDelta.clear();
            removeFromCandidates.clear();
            for (Rule r: theory.getRules(RuleState.ACTIVABLE, new ArrayList<>(List.of(RuleType.STRICT))).values()) {
                if (!Collections.disjoint(r.getTail(),extension.getMinusDelta())){
                    toMinusDelta.add(r.getHead());
                    r.setType(RuleType.DEFEASIBLE);
                } else removeFromCandidates.add(r.getHead());
            }
        }

    }

    private void computePlusPartial() {

    }

    private void computeMinusPartial() {

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
