package com.example.demo.LogicModel;
import java.util.*;

public class Literal implements Comparable<Literal> {

    public enum LiteralType {
        POSITIVE,
        NEGATIVE;

        public LiteralType opposite() {
            switch(this) {
                case POSITIVE : return NEGATIVE;
                case NEGATIVE : return POSITIVE;
                default: return POSITIVE;
            }
        };
    };

    public enum ExtensionState {UNDECIDED, PLUS, MINUS, UNDECIDABLE}

    private String label;
    private LiteralType type;
    private Literal opposite;
    private Set<Rule> rulesIsHeadOf = new TreeSet<Rule>();
    // private boolean hasActiveRules; not a property of the literal itself, I guess it shouldn't be here
    private ExtensionState deltaState = ExtensionState.UNDECIDED;
    private ExtensionState partialState = ExtensionState.UNDECIDED;

    public Literal(String label, LiteralType type) {
        this.label = label;
        this.type = type;
    }

    public Literal(String label) {
        this.label = label;
        boolean startsWithTilde = label.startsWith("~");
        if (!startsWithTilde) {
            this.type = LiteralType.POSITIVE;
            //Must call this constructor otherwise there will be recursion
            this.opposite = new Literal("~".concat(label), LiteralType.NEGATIVE, this);
        }
        else {
            this.type = LiteralType.NEGATIVE;
            //Must call this constructor otherwise there will be recursion
            this.opposite = new Literal(label.substring(1), LiteralType.POSITIVE, this);
        }
    }
    
    public Literal(String label, LiteralType type, Literal opposite) {
        this.label = label;
        this.type = type;
        this.opposite = opposite;
    }
    
    public String getLabel() {
        return label;
    }

    public LiteralType getType() {
        return type;
    }

    public Literal getOpposite() {
        return opposite;
    }

    public void setOpposite(Literal opposite) {
        this.opposite = opposite;
    }

    public Set<Rule> getRulesIsHeadOf() {
        return rulesIsHeadOf;
    }

    public void addRuleIsHeadOf(Rule r) {this.rulesIsHeadOf.add(r);};

    public ExtensionState getDeltaState() {
        return deltaState;
    }

    public void setPlusDelta() {
        this.deltaState = ExtensionState.PLUS;
    }

    public void setMinusDelta() {
        this.deltaState = ExtensionState.MINUS;
    }

    public void setPlusPartial() {
        this.partialState = ExtensionState.PLUS;
    }

    public void setMinusPartial() {
        this.partialState = ExtensionState.MINUS;
    }

    public ExtensionState getPartialState() {
        return partialState;
    }

    public void setPartialState(ExtensionState partialState) {
        this.partialState = partialState;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {return true;}
        if (!(object instanceof Literal)) {return false;}
        Literal literal = (Literal) object;
        return (getLabel().equals(literal.getLabel()) && (this.getType().equals(literal.getType())));
    }

    @Override
    public int compareTo(Literal o) {
        if (this.label.equals(o.label)) {
            if (this.type == o.type) {
                return 0;
            } else if (this.type == LiteralType.POSITIVE) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return this.label.compareTo(o.getLabel());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabel(), getType());
    }

    public String toString() {
        return ((type == LiteralType.POSITIVE) ? "" : "~") + getLabel();
    }

}

