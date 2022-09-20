package com.example.demo.LogicModel;
import java.util.*;

public class Literal implements Comparable<Literal> {

    public enum LiteralType {POSITIVE, NEGATIVE};
    public enum ExtensionState {UNDECIDED, PLUS, MINUS, UNDECIDABLE}

    private String label;
    private LiteralType type;
    private Literal opposite;
    private List<Rule> rulesIsHeadOf = new ArrayList<Rule>();
    private boolean hasActiveRule;
    private ExtensionState deltaState = ExtensionState.UNDECIDED;
    private ExtensionState partialState = ExtensionState.UNDECIDED;

    public Literal(String label, LiteralType type) {
        this.label = label;
        this.type = type;
    }
    
    public String getLabel() {
        return label;
    }

    public LiteralType getType() {
        return type;
    }

    public boolean isPositive() {
        return type == LiteralType.POSITIVE;
    }

    public Literal getOpposite() {
        return opposite;
    }

    public void setOpposite(Literal opposite) {
        this.opposite = opposite;
    }

    public List<Rule> getRulesIsHeadOf() {
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

    public void setUndecidableDelta() {
        this.deltaState = ExtensionState.UNDECIDABLE;
    }

    public void setPlusPartial() {
        this.partialState = ExtensionState.PLUS;
    }

    public void setMinusPartial() {
        this.partialState = ExtensionState.MINUS;
    }

    public void setUndecidablePartial() {
        this.partialState = ExtensionState.UNDECIDABLE;
    }

    public ExtensionState getPartialState() {
        return partialState;
    }

    public void setPartialState(ExtensionState partialState) {
        this.partialState = partialState;
    }

    public boolean isMinusDelta() {
        return this.deltaState == ExtensionState.MINUS;
    }

    public boolean isPlusPartial() {
        return this.partialState == ExtensionState.PLUS;
    }

    public boolean hasActiveRule() {
        return hasActiveRule;
    }
    /**
     * Sets the information that this literal has at least one active rule to true.
     */
    public void setHasActiveRule() {
        hasActiveRule = true;
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

