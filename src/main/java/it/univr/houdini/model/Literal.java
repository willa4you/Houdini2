package it.univr.houdini.model;

import static it.univr.houdini.model.Literal.LiteralPolarity.*;
import static it.univr.houdini.model.Literal.AmbiguityState.*;
import static it.univr.houdini.model.Literal.ExtensionCase.*;
import static it.univr.houdini.model.Literal.ExtensionState.*;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public class Literal implements Comparable<Literal>, JsonSerializable {

    public enum LiteralPolarity {POSITIVE, NEGATIVE};
    public enum ExtensionState {UNDECIDED, PLUS, MINUS, UNDECIDABLE}
    public static enum AmbiguityState {UNAMBIGUOUS, BASE_CASE, BY_ANTECEDENTS, BY_OPPOSITE}
    public static enum ExtensionCase {CASE_A, CASE_B, CASE_C, CASE_D, CASE_E, CASE_F, CASE_X}

    private String label;
    private LiteralPolarity polarity;
    private Literal opposite;
    private Set<Rule> rulesIsHeadOf = new HashSet<Rule>(); 
    private List<Rule> rulesIsTailOf = new ArrayList<Rule>(); // it performs only adds and iterations, so a List is enough
    private boolean hasActiveRule;
    private AmbiguityState ambiguity;
    private ExtensionState deltaState = UNDECIDED;
    private ExtensionState partialState = UNDECIDED;
    private ExtensionCase extensionCase = CASE_X; // This should be always overwritten

    public Literal(String label, int polarity) {
        this.label = label;
        this.polarity = (polarity > 0) ? POSITIVE : NEGATIVE;
        this.ambiguity = UNAMBIGUOUS;
    }
    
    public String getLabel() {
        return label;
    }

    public LiteralPolarity getPolarity() {
        return polarity;
    }
    
    public Literal getOpposite() {
        return opposite;
    }

    public void setOpposite(Literal opposite) {
        this.opposite = opposite;
    }
    
    public boolean isPositive() {
        return polarity == LiteralPolarity.POSITIVE;
    }

    public AmbiguityState getAmbiguity() {
        return this.ambiguity;
    }

    public void setAmbiguity(AmbiguityState ambiguityState) {
        this.ambiguity = ambiguityState;
    }

    public Set<Rule> getRulesIsHeadOf() {
        return rulesIsHeadOf;
    }

    public List<Rule> getRulesIsTailOf() {
        return rulesIsTailOf;
    }

    public void addToRulesIsHeadOf(Rule r) {this.rulesIsHeadOf.add(r);};

    public void addToRulesIsTailOf(Rule r) {this.rulesIsTailOf.add(r);}

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
        else {extensionCase = CASE_X;} // TODO: in case X we should throw an ecception and terminate
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
        return (getLabel().equals(literal.getLabel()) && (this.getPolarity().equals(literal.getPolarity())));
    }

    @Override
    public int compareTo(Literal o) {
        if (this.label.equals(o.label)) {
            if (this.polarity == o.polarity) {
                return 0;
            } else if (this.polarity == LiteralPolarity.POSITIVE) {
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
        return Objects.hash(getLabel(), getPolarity());
    }

    public String toString() {
        return ((polarity == LiteralPolarity.POSITIVE) ? "" : "~") + getLabel();
    }

    @Override
    public void serialize(JsonGenerator jsonGen, SerializerProvider provider) throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeStringField("proposition", this.label);
        jsonGen.writeNumberField("polarity", (this.isPositive()) ? 1 : -1);
        jsonGen.writeStringField("deltaState", this.deltaState.name());
        jsonGen.writeStringField("partialState", this.partialState.name());
        jsonGen.writeStringField("case", this.extensionCase.name());
        jsonGen.writeStringField("ambiguity", this.ambiguity.name());
        jsonGen.writeEndObject();
    }

    @Override
    public void serializeWithType(JsonGenerator jsonGen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        serialize(jsonGen, provider);
    }
}

