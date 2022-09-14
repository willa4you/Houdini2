package com.example.demo.LogicModel.Extension;

import com.example.demo.LogicModel.*;
import com.example.demo.LogicModel.Rule.RuleType;
import com.example.demo.LogicModel.Rule.RuleState;
import com.example.demo.Pair;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class DefeasibleExtensionComputator implements ExtensionComputator{
    public double elapsedtime = 0.0;
    @Override
    public Pair<Theory, TheoryExtension> computeExtension(Theory theory, TheoryExtension extension) {
        long start_time = System.currentTimeMillis();

        theory.getRules(RuleState.ACTIVABLE, new ArrayList<>(List.of(RuleType.STRICT))).values().forEach(r -> r.setType(RuleType.DEFEASIBLE));
        Set<Literal> defeasibleHeads = theory.getHeads(new HashSet<>(theory.getRules(RuleState.ACTIVABLE, new ArrayList<>(List.of(RuleType.DEFEASIBLE))).values()));
        defeasibleHeads.addAll(theory.getHeads(new HashSet<>(theory.getRules(RuleState.ACTIVE, new ArrayList<>(List.of(RuleType.DEFEASIBLE))).values())));
        extension.getPlusPartial().addAll(extension.getPlusDelta());

        for (Literal l : extension.getMinusDelta()) {
            if(extension.getPlusDelta().contains(l.getOpposite()) || !defeasibleHeads.contains(l)){
                extension.getMinusPartial().add(l);
                l.setPartialState(State.MINUS);
            }
        }

        Set<Literal> triggerable = new TreeSet<>(extension.getPlusPartial());
        Set<Literal> untriggerable = new TreeSet<>(extension.getMinusPartial());

        do{
            for (Rule r : theory.getRules(RuleState.ACTIVABLE).values()) {
                if (!Collections.disjoint(r.getTail(), untriggerable)) {
                    r.setRuleState(RuleState.DEACTIVATED);
                }
                else for (Literal l : triggerable) {
                    r.getTail().remove(l);
                    if (r.getTail().isEmpty()){
                        r.setRuleState(RuleState.ACTIVE);
                    }
                }
            }
            triggerable.clear();
            untriggerable.clear();

            //plus partial check
            Map<String, Rule> activeRules = new HashMap<>(theory.getRules(RuleState.ACTIVE, new ArrayList<>(List.of(RuleType.DEFEASIBLE))));
            for (Literal lit : theory.getLiterals(State.UNDECIDED)) {
                // plus partial check
                if (theory.getHeads(new HashSet<>(activeRules.values())).contains(lit)) {
                    boolean isPlusPartial = true;
                    for (String s : lit.getOpposite().getRulesIsHeadOf()) {
                        Rule opposite = theory.getRules().get(s);
                        /*TODO check this: condition 2.3 works when all opposite rules are deactivated, or, for any non-deactivated opposite rule,
                        //there is an active rule with head the literal that prevails over it. Here, for any opposite rule, we only check that there is 
                        no active rule that prevails over it, BUT we do not check if it's deactivated (if it is, it's not necessary.) Moreover, I think here
                        it retrieves ALL the opposite rules, not only those whose head is lit
                        */ 
                        isPlusPartial = isPlusPartial && !Collections.disjoint(activeRules.keySet(), opposite.getLosesAfter());
                    }
                    if (isPlusPartial) {
                        if (lit.getOpposite().getDeltaState() == State.MINUS) {
                            triggerable.add(lit);
                            lit.setPartialState(State.PLUS);
                        } else {
                            lit.setPartialState(State.UNDECIDABLE);
                        }
                        continue; // no need to check minusPartial anymore
                    }
                }
                //TODO we need to continue checking for +partial here, checking if the active rules have head lit it's not sufficient
                //(it's an or condition)
                boolean CHECK_HERE = true;

                // minus partial check
                boolean isMinusPartial = true;
                for (String s : lit.getRulesIsHeadOf()) {
                    Rule r = theory.getRules().get(s);
                    if (r.getType() != RuleType.DEFEATER) {
                        isMinusPartial = false;
                        break;
                    }
                }
                if (!isMinusPartial) {
                    for (String s : lit.getOpposite().getRulesIsHeadOf()) {
                        Rule r = theory.getRules().get(s);
                        if (r.getTail().isEmpty()) {
                            isMinusPartial = true;
                            for (String s_rLosesAfter : r.getLosesAfter()) {
                                Rule rLosesAfter = theory.getRules().get(s_rLosesAfter);
                                if (rLosesAfter.getHead().equals(r.getHead().getOpposite()) && rLosesAfter.getType() != RuleType.DEFEATER) {
                                    isMinusPartial = false;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (isMinusPartial) {
                    if (lit.getDeltaState().equals(State.MINUS)) {
                        lit.setPartialState(State.MINUS);
                        untriggerable.add(lit);
                    } else {
                        lit.setPartialState(State.UNDECIDABLE);
                    }
                }
            }
            extension.getPlusPartial().addAll(triggerable);
            extension.getMinusPartial().addAll(untriggerable);
        //TODO we might need to check the rule state when using getRules method
        } while(!triggerable.isEmpty() || !untriggerable.isEmpty());

        long final_time = System.currentTimeMillis();
        this.elapsedtime = (final_time - start_time)/1000.0;
        return new Pair<Theory, TheoryExtension>(theory, extension);
    }
    
}
