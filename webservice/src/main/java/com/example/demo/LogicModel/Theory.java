package com.example.demo.LogicModel;
import java.util.*;
import java.util.stream.Collectors;

public class Theory {
    private Set<Literal> literals = new HashSet<Literal>();
    private Set<Literal> facts = new HashSet<Literal>();
    private Map<String, Rule> rules = new HashMap<String, Rule>();
    private Set<SuperiorityRelation> superiorityRelations = new HashSet<SuperiorityRelation>();


    public Theory() {}

    //Notice the different literals type here
    public Theory(Set<Literal> literals, Set<Literal> facts, Map<String, Rule> rules, Set<SuperiorityRelation> superiorityRelations) {
        this.literals = literals;
        this.facts = facts;
        this.rules = rules;
        this.superiorityRelations = superiorityRelations;
    }

    public Theory(Set<String> literals, LogicModel logicModel) {
        this(literals, logicModel.getFacts(), logicModel.getRules(), logicModel.getSupRules());
    }

    public Theory(Set<String> literals, List<String> facts, List<String> rules, List<String> superiorityRelations) {
        this.literals = literals.stream().filter(l -> !l.equals("")).map(Literal::new).collect(Collectors.toSet());
        this.facts = facts.stream().filter(f -> !f.equals("")).map(Literal::new).collect(Collectors.toSet());

        Integer rule_counters = 0;
        for (String r : rules) {
            if (r == "") continue;
            String[] splitted_rule = r.trim().split(">");
            
            String tail = splitted_rule[0].trim();
            String head = splitted_rule[1].trim();

            RuleType rtype;
            switch(tail.charAt(tail.length()-1)) {
                case '-' : rtype = RuleType.STRICT;
                case '=' : rtype = RuleType.DEFEASIBLE;
                case '~' : rtype = RuleType.DEFEATER;
                default : rtype = RuleType.STRICT;
            }
    
            List<String> tail_literals = Arrays.asList(tail.substring(0, tail.length() - 1).split(","));
            TreeSet<Literal> set_tail = new TreeSet<>(tail_literals.stream().map(tl -> new Literal(tl.trim())).collect(Collectors.toSet()));
            
            Literal lit_head = new Literal(head);
            String label = rule_counters.toString();
            rule_counters = rule_counters.intValue() + 1;
            this.rules.put(label, new Rule(lit_head, set_tail, rtype));
        };

        for (String sr : superiorityRelations) {
            if (sr == "") continue;
            String[] splitted_srule = sr.trim().split(">");
            String loser;
            String winner;
            if (splitted_srule.length == 1) {
                //we are in a < situation
                splitted_srule = sr.trim().split("<");
                loser = splitted_srule[0].trim();
                winner = splitted_srule[1].trim();
            } else {
                loser = splitted_srule[1].trim();
                winner = splitted_srule[0].trim();
            }

            String loser_index = String.valueOf(Integer.parseInt(loser.substring(1)) - 1);
            String winner_index = String.valueOf(Integer.parseInt(winner.substring(1)) - 1);
            Rule winner_rule = this.rules.get(winner_index);
            Rule loser_rule = this.rules.get(loser_index);

            SuperiorityRelation suprel = new SuperiorityRelation(winner_rule, loser_rule);
            winner_rule.getWinsOver().add(loser_index);
            loser_rule.getLosesAfter().add(winner_index);
            
            this.superiorityRelations.add(suprel);
        }
    }

    public Set<Literal> getLiterals() {
        return literals;
    }

    public void setLiterals(Set<Literal> literals) {
        this.literals = literals;
    }

    public Set<Literal> getFacts() {
        return facts;
    }

    public void setFacts(Set<Literal> facts) {
        this.facts = facts;
    }

    public Map<String, Rule> getRules() {
        return rules;
    }
    public Set<Rule> getRules(RuleState ruleState, ArrayList<RuleType> ruleTypes){
        return this.rules.values().stream().filter(rule -> ruleTypes.contains(rule.getType()) && rule.getRuleState() == ruleState).collect(Collectors.toSet());
    }
    public void setRules(Map<String, Rule> rules) {
        this.rules = rules;
    }

    public Set<SuperiorityRelation> getSuperiorityRelations() {
        return superiorityRelations;
    }

    public void setSuperiorityRelations(Set<SuperiorityRelation> superiorityRelations) {
        this.superiorityRelations = superiorityRelations;
    }

    public Set<Literal> getHeads(Set<Rule> rules){
        Set<Literal> heads = new TreeSet<>();
        for (Rule r:rules) {
            heads.add(r.getHead());
        }
        return heads;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Theory)) return false;
        if (!super.equals(object)) return false;
        Theory theory = (Theory) object;
        return getFacts().equals(theory.getFacts()) && getRules().equals(theory.getRules()) && getSuperiorityRelations().equals(theory.getSuperiorityRelations());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getFacts(), getRules(), getSuperiorityRelations());
    }
}