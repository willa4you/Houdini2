package backend;
import java.util.*;

public class Literal{

    private String label;
    private Boolean truthValue;
    private Literal opposite;
    private Set<String> rules = new TreeSet<String>();
    // private boolean hasActiveRules; not a property of the literal itself, I guess it shouldn't be here
    private State deltaState = State.UNDECIDED;
    private State partialState = State.UNDECIDED;

    public Literal(String label, Boolean truthValue, backend.Literal opposite) {
        this.label = label;
        this.truthValue = truthValue;
        this.opposite = opposite;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getTruthValue() {
        return truthValue;
    }

    public void setTruthValue(Boolean truthValue) {
        this.truthValue = truthValue;
    }

    public backend.Literal getOpposite() {
        return opposite;
    }

    public void setOpposite(backend.Literal opposite) {
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

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Literal)) return false;
        if (!super.equals(object)) return false;
        Literal literal = (Literal) object;
        return getLabel().equals(literal.getLabel()) && getTruthValue().equals(literal.getTruthValue());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getLabel(), getTruthValue());
    }
}