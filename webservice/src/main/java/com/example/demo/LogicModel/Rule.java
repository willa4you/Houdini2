package com.example.demo.LogicModel;
import java.util.*;

public class Rule implements Comparable<Rule>{
    
    public enum RuleType{STRICT, DEFEASIBLE, DEFEATER}

    private String label;
    private RuleType type;
    private Literal head;
    private TreeSet<Literal> tail;
    
    private Set<Rule> winsOver = new TreeSet<Rule>();
    private Set<Rule> losesAfter = new TreeSet<Rule>();
    
    // private boolean losesAfterActiveRule; not a property of the rule itself, I guess it shouldn't be here
    
    public Rule(String label, RuleType type, Literal head, TreeSet<Literal> tail) {
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

    public TreeSet<Literal> getTail() {
        return tail;
    }

    public RuleType getType() {
        return type;
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

    public int hashCode() {
        return Objects.hash(super.hashCode(), getHead(), getTail(), getType());
    }

}