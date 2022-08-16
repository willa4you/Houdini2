package com.example.demo.LogicModel.Extension;
import com.example.demo.LogicModel.*;
import com.example.demo.Pair;

import java.util.*;
import java.util.stream.Collectors;


public class StrictExtensionComputator implements ExtensionComputator {

    @Override
    public Pair<Theory, Extension> computeExtension(Theory theory, Extension extension) {
        Set<Literal> injectableLiterals = theory.getFacts();
        injectLiterals(injectableLiterals, extension, theory);
        while(!injectableLiterals.isEmpty()) { //TODO if the theory contains strict rules with empty tail but no facts the loop doesn't start
            injectableLiterals.clear();
            for (Rule r : theory.getRules(RuleState.ACTIVABLE, new ArrayList<>(List.of(RuleType.STRICT)))) {
                if (r.getTail().isEmpty()) {
                    injectableLiterals.add(r.getHead());
                    r.activate();
                }
            }
            if (!injectableLiterals.isEmpty())
                injectLiterals(injectableLiterals, extension, theory);
        }
        extension.getMinusDelta().addAll(theory.getLiterals());
        extension.getMinusDelta().removeAll(extension.getPlusDelta());
        extension.getMinusDelta().removeAll(theory.getHeads(theory.getRules(RuleState.ACTIVABLE, new ArrayList<>(List.of(RuleType.STRICT)))));
        Set<Literal> candidates = new TreeSet<Literal>(extension.getMinusDelta());
        while(!candidates.isEmpty()){
            extension.getMinusDelta().addAll(candidates);
            candidates.clear();
            for (Rule r: theory.getRules(RuleState.ACTIVABLE, new ArrayList<>(List.of(RuleType.STRICT)))) {
                if (!Collections.disjoint(r.getTail(),extension.getMinusDelta())){
                    candidates.add(r.getHead());
                    //TODO should we define another state for converted rules? setting them as defeasible changes the input theory
                    r.setType(RuleType.DEFEASIBLE);
                } else candidates.remove(r.getHead());
            }
        }

        return new Pair<Theory, Extension>(theory, extension);
    }

    public void injectLiterals(Set<Literal> literals, Extension ext, Theory th){
        for (Literal l : literals) {
            for (Rule r : th.getRules(RuleState.ACTIVABLE, new ArrayList<>(List.of(RuleType.STRICT)))) {
                if (r.getHead().equals(l)) r.deactivate();
                if (r.getTail().contains(l)) {
                    r.removeFromTail(l);
                }
            }
            ext.getPlusDelta().add(l);
        }
    }
}
