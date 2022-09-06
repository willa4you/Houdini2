package com.example.demo.LogicModel.Extension;
import com.example.demo.LogicModel.*;
import com.example.demo.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class StrictExtensionComputator implements ExtensionComputator {
    public double elapsedtime = 0.0;
    @Override
    public Pair<Theory, Extension> computeExtension(Theory theory, Extension extension) {
        long start_time = System.currentTimeMillis();
        Set<Literal> injectableLiterals = theory.getFacts();
        injectableLiterals.addAll(theory.getHeads(new HashSet<>(theory.getRules(RuleState.ACTIVE, new ArrayList<>(List.of(RuleType.STRICT))).values())));
        injectLiterals(injectableLiterals, extension, theory);
        do{
            injectableLiterals.clear();
            for (Rule r : theory.getRules(RuleState.ACTIVABLE, new ArrayList<>(List.of(RuleType.STRICT))).values()) {
                if (r.getTail().isEmpty()) {
                    injectableLiterals.add(r.getHead());
                    r.activate();
                }
            }
            if (!injectableLiterals.isEmpty())
                injectLiterals(injectableLiterals, extension, theory);
        } while(!injectableLiterals.isEmpty());

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
        long final_time = System.currentTimeMillis();

        this.elapsedtime = (final_time - start_time)/1000.0;
        return new Pair<Theory, Extension>(theory, extension);
    }

    public void injectLiterals(Set<Literal> literals, Extension ext, Theory th){
        for (Literal l : literals) {
            for (Rule r : th.getRules(RuleState.ACTIVABLE, new ArrayList<>(List.of(RuleType.STRICT))).values()) {
                if (r.getHead().equals(l)) r.deactivate();
                if (r.getTail().contains(l)) {
                    r.removeFromTail(l);
                }
            }
            ext.getPlusDelta().add(l);
            l.setDeltaState(State.PLUS);
            l.setPartialState(State.PLUS);
        }
    }
}
