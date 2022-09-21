package com.example.demo.LogicModel;
import java.util.*;
import static com.example.demo.LogicModel.Literal.ExtensionCase.*;
import static com.example.demo.LogicModel.Literal.ExtensionState.*;

public class Literal implements Comparable<Literal> {

    public enum LiteralType {POSITIVE, NEGATIVE};
    public enum ExtensionState {UNDECIDED, PLUS, MINUS, UNDECIDABLE}
    public static enum ExtensionCase {CASE_A, CASE_B, CASE_C, CASE_D, CASE_E, CASE_F, CASE_X}

    private String label;
    private LiteralType type;
    private Literal opposite;
    private List<Rule> rulesIsHeadOf = new ArrayList<Rule>();
    private boolean hasActiveRule;
    private ExtensionState deltaState = ExtensionState.UNDECIDED;
    private ExtensionState partialState = ExtensionState.UNDECIDED;
    private ExtensionCase extensionCase;

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
    
    public Literal getOpposite() {
        return opposite;
    }

    public void setOpposite(Literal opposite) {
        this.opposite = opposite;
    }
    
    public boolean isPositive() {
        return type == LiteralType.POSITIVE;
    }

    public List<Rule> getRulesIsHeadOf() {
        return rulesIsHeadOf;
    }

    public void addRuleIsHeadOf(Rule r) {this.rulesIsHeadOf.add(r);};

    public ExtensionState getDeltaState() {
        return deltaState;
    }

    public void setPlusDelta() {
        this.deltaState = PLUS;
    }

    public void setMinusDelta() {
        this.deltaState = MINUS;
    }

    public void setUndecidableDelta() {
        this.deltaState = UNDECIDABLE;
    }

    public ExtensionState getPartialState() {
        return partialState;
    }

    public void setPlusPartial() {
        setPartialState(PLUS);
    }

    public void setMinusPartial() {
        setPartialState(MINUS);
    }

    public void setUndecidablePartial() {
        setPartialState(UNDECIDABLE);
    }

    private void setPartialState(ExtensionState partialState) {
        this.partialState = partialState;
        this.setExtensionCase();
    }

    private void setExtensionCase() {
        if (deltaState == UNDECIDABLE && partialState == UNDECIDABLE) {extensionCase = CASE_A;}
        else if (deltaState == UNDECIDABLE && partialState == PLUS) {extensionCase = CASE_B;}
        else if (deltaState == PLUS && partialState == PLUS) {extensionCase = CASE_C;}
        else if (deltaState == MINUS && partialState == PLUS) {extensionCase = CASE_D;}
        else if (deltaState == MINUS && partialState == UNDECIDABLE) {extensionCase = CASE_E;}
        else if (deltaState == MINUS && partialState == MINUS) {extensionCase = CASE_F;}
        else {extensionCase = CASE_X;} // TODO: in case X we should throw and ecception and terminate
    }

    public ExtensionCase getExtensionCase() {
        return extensionCase;
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

