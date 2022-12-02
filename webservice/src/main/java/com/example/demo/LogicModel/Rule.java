package com.example.demo.LogicModel;
import java.util.*;

public class Rule implements Comparable<Rule>{
    
    public enum RuleType{STRICT, DEFEASIBLE, DEFEATER}

    private String label;
    private RuleType type;
    private Literal head;
    private HashSet<Literal> tail;
    
    private Set<Rule> winsOver = new HashSet<Rule>(); // at some point we want to remove objects in O(1)
    private Set<Rule> losesAfter = new HashSet<Rule>(); // at some point we want to remove objects in O(1)
    private boolean isLosingAfterActiveRule = false;
    
    public Rule(String label, RuleType type, Literal head, HashSet<Literal> tail) {
        this.label = label;
        this.type = type;
        this.head = head;
        this.tail = tail;
    }

    public Rule(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Literal getHead() {
        return head;
    }

    public HashSet<Literal> getTail() {
        return tail;
    }

    public boolean isEmptyTail() {
        return this.tail.isEmpty();
    }

    public RuleType getType() {
        return type;
    }

    public boolean isStrict() {
        return this.type == RuleType.STRICT;
    }

    public boolean isDefeasible() {
        return this.type == RuleType.DEFEASIBLE;
    }

    public boolean isDefeater() {
        return this.type == RuleType.DEFEATER;
    }

    public Set<Rule> getWinsOver() {
        return winsOver;
    }

    public void addToWinsOver(Rule inferior) {
        winsOver.add(inferior);
    }

    public Set<Rule> getLosesAfter() {
        return losesAfter;
    }

    public void addToLosesAfter(Rule superior) {
        losesAfter.add(superior);
    }

    public void removeFromTail(Literal literal){
        this.tail.remove(literal);
    }

    public boolean isLosingAfterActiveRule() {
        return isLosingAfterActiveRule;
    }
    /**
     * Sets the information that this rule loses after an active rule to true.
     */
    public void setLosesAfterActiveRule() {
        isLosingAfterActiveRule = true;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {return true;}
        if (!(object instanceof Rule)) {return false;}

        return this.label.equals(((Rule)object).label); // two rules are the same iff they have the same label
    }

    @Override
    public int compareTo(Rule otherRule) {
        return this.label.compareTo(otherRule.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.label);
    }

    @Override
    public String toString() {
        String ruleString = "";
        for (Literal tailLiteral : tail){ruleString += tailLiteral.toString() + ", ";}
        ruleString = ruleString.substring(0, ruleString.length() - 2); // remove last ", "
        switch(type) {
            case STRICT: ruleString += " -> "; break;
            case DEFEASIBLE: ruleString += " => "; break;
            case DEFEATER: ruleString += " ~> "; break;
        }
        return ruleString + head.toString();
    }
}