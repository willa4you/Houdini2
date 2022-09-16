package com.example.demo.LogicModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;
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
        // For the following iteration we need a list so we have to double up the data structures (a set and a list)
        // TODO: This double data structure approach should be optimized
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
                        }
                    }
                }
            }
            injectables = newInjectables; // i move new injectables into main list
            newInjectables.clear(); // i clear the new injectables
        }

        plusDelta.addAll(strictConclusions);

    }

    private void computeMinusDelta() {
        
        
        
        Set<Literal> candidates = new TreeSet<Literal>(theory.getLiterals());
        Set<Literal> removeFromCandidates = new TreeSet<>(extension.getPlusDelta());
        removeFromCandidates.addAll(theory.getHeads(new HashSet<>(theory.getRules(RuleState.ACTIVABLE, new ArrayList<>(List.of(RuleType.STRICT))).values())));

        while(!candidates.isEmpty()){
            candidates.removeAll(removeFromCandidates);
            candidates.forEach(c -> c.setDeltaState(State.MINUS));
            extension.getMinusDelta().addAll(candidates);
            candidates.clear();
            removeFromCandidates.clear();
            for (Rule r: theory.getRules(RuleState.ACTIVABLE, new ArrayList<>(List.of(RuleType.STRICT))).values()) {
                if (!Collections.disjoint(r.getTail(),extension.getMinusDelta())){
                    candidates.add(r.getHead());
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
