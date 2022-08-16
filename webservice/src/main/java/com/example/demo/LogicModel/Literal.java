package com.example.demo.LogicModel;
import java.util.*;

public class Literal implements Comparable<Literal> {

    private String label;
    private LITERALTYPE type;
    private Literal opposite;
    private Set<String> rules = new TreeSet<String>();
    // private boolean hasActiveRules; not a property of the literal itself, I guess it shouldn't be here
    private State deltaState = State.UNDECIDED;
    private State partialState = State.UNDECIDED;

    private enum LITERALTYPE {
        LITERAL,
        NEGATED;

        public LITERALTYPE opposite() {
            switch(this) {
                case LITERAL : return NEGATED;
                case NEGATED : return LITERAL;
                default: return LITERAL;
            }
        };
    };

    public Literal(String label) {
        this.label = label;
        boolean startsWithTilde = label.startsWith("~");
        
        if (!startsWithTilde) {
            this.type = LITERALTYPE.LITERAL;
            //Must call this constructor otherwise there will be recursion
            this.opposite = new Literal("~".concat(label), LITERALTYPE.NEGATED, this);
        }
        else {
            this.type = LITERALTYPE.NEGATED;
            //Must call this constructor otherwise there will be recursion
            this.opposite = new Literal(label.substring(1), LITERALTYPE.LITERAL, this);
        }
    }
    
    public Literal(String label, LITERALTYPE type, Literal opposite) {
        this.label = label;
        this.type = type;
        this.opposite = opposite;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }

    public LITERALTYPE getType() {
        return type;
    }

    public void setType(LITERALTYPE type) {
        this.type = type;
    }

    public Literal getOpposite() {
        return opposite;
    }

    public void setOpposite(Literal opposite) {
        this.opposite = opposite;
    }

    public Set<String> getRules() {
        return rules;
    }

    public void setRules(Set<String> rules) {
        this.rules = rules;
    }

    public State getDeltaState() {
        return deltaState;
    }

    public void setDeltaState(State deltaState) {
        this.deltaState = deltaState;
    }

    public State getPartialState() {
        return partialState;
    }

    public void setPartialState(State partialState) {
        this.partialState = partialState;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Literal)) return false;
        //if (!super.equals(object)) return false;
        Literal literal = (Literal) object;
        return (getLabel().equals(literal.getLabel()) && (this.getType().equals(literal.getType())));
    }

    @Override
    public int compareTo(Literal o) {
        return this.label.compareTo(o.getLabel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLabel(), getType());
    }

    public String toString() {
        return getLabel();
    }

}

