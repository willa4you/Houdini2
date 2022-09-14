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

        long final_time = System.currentTimeMillis();
        this.elapsedtime = (final_time - start_time)/1000.0;
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
        
        Set<Literal> strictConclusions = new HashSet<Literal>(); // a Set because we don't want duplicates
        strictConclusions.addAll(theory.getFacts());
        strictConclusions.forEach(lit -> {lit.setPlusDelta();}); // setting plus delta to all facts

        // we remove native empty tail strict rules
        for (Rule strictRule: theory.getRules(RuleType.STRICT)) { // iteration on an ArrayList which is not the theory rules Set
		    if (strictRule.getTail().isEmpty()) {
			    strictConclusions.add(strictRule.getHead());
                strictRule.getHead().setPlusDelta();
			    theory.removeRule(strictRule); // no problems with iteration because object is not removed from the iterating list
		    }
        }

	    // TRIGGER PART (Injection)
        // we remove strict conclusion literals from tails and check if a rule get active by an empty tail
        // For the following iteration we need a list so we have to double up the data structures (a set and a list)
        // TODO: This double data structure approach should be optimized
	    { // defining the arraylist scope
        ArrayList<Literal> injectables = new ArrayList<>(strictConclusions); // passing to a list for a better iteration
        int i = 0;
        while(i < injectables.size()) {
            Literal injectable = injectables.get(i);
            for (Rule strictRule: theory.getRules(RuleType.STRICT)) {// iteration on an ArrayList which is not the theory rules Set
                if (strictRule.getTail().contains(injectable)) {
                    strictRule.removeFromTail(injectable);
                    if (strictRule.getTail().isEmpty()) { // we found an empty tail '-> a' rule
                        if (strictConclusions.add(strictRule.getHead())) { // i will inject this head iff not already a strict conclusions
                            strictRule.getHead().setPlusDelta(); // an empty tail '-> a' rule makes 'a' a plus Delta
                            injectables.add(strictRule.getHead()); // we will inject this head so while get longer (analogous to fixpoint)
                        }
                        theory.removeRule(strictRule); // no problems with iteration because object is not removed from the iterating list
                    }
                }
            }
            i++;
        } // end while
        } // get rid of injectables

        // UNTRIGGER PART
        // now we remove strict rules hav this form:
        // 1: a, ¬s, ... w -> h (where ¬s is the complementary of a strict conclusion)
        // consider that if ¬s itself were also a strict conclusion we could not find it in any tail
        // 2: a, b, c, ... w -> ¬s (where ¬s is the complementary of a strict conclusion)
        // 3: a, b, c, ... w -> s (where s a strict conclusion)
        
        for (Literal s: strictConclusions) {
            for (Rule r: theory.getRules(RuleType.STRICT)) {// iteration on an ArrayList which is not the theory rules Set
                if (r.getTail().contains(s.getOpposite()) || r.getHead() == s.getOpposite() || r.getHead() == s)
                    theory.removeRule(r); // no problems with iteration because object is not removed from the iterating list
            }
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
