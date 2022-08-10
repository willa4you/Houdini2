package com.example.demo.LogicModel;import java.util.*;

public class Rule {

    private Literal head;
    private TreeSet<Literal> tail = new TreeSet<Literal>();
    private RuleType type;
    private Set<String> winsOver = new TreeSet<String>();
    private Set<String> losesAfter = new TreeSet<String>();
    // private boolean losesAfterActiveRule; not a property of the rule itself, I guess it shouldn't be here

    public Rule(Literal head, TreeSet<Literal> tail, RuleType type) {
        this.head = head;
        this.tail = tail;
        this.type = type;
    }

    public Literal getHead() {
        return head;
    }

    public void setHead(Literal head) {
        this.head = head;
    }

    public TreeSet<Literal> getTail() {
        return tail;
    }

    public void setTail(TreeSet<Literal> tail) {
        this.tail = tail;
    }

    public RuleType getType() {
        return type;
    }

    public void setType(RuleType type) {
        this.type = type;
    }

    public Set<String> getWinsOver() {
        return winsOver;
    }

    public void setWinsOver(Set<String> winsOver) {
        this.winsOver = winsOver;
    }

    public Set<String> getLosesAfter() {
        return losesAfter;
    }

    public void setLosesAfter(Set<String> losesAfter) {
        this.losesAfter = losesAfter;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Rule)) return false;
        if (!super.equals(object)) return false;
        Rule rule = (Rule) object;
        return getHead().equals(rule.getHead()) && getTail().equals(rule.getTail()) && getType().equals(rule.getType());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getHead(), getTail(), getType());
    }

}