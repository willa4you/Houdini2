package backend;
import java.util.*;

public class Theory{

    private Set<Literal> facts = new HashSet<Literal>();
    private Map<String, Rule> rules = new HashMap<String, Rule>();
    private Set<SuperiorityRelation> superiorityRelations = new HashSet<SuperiorityRelation>();

    public Theory(Set<Literal> facts, Map<String, Rule> rules, Set<SuperiorityRelation> superiorityRelations) {
        this.facts = facts;
        this.rules = rules;
        this.superiorityRelations = superiorityRelations;
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

    public void setRules(Map<String, Rule> rules) {
        this.rules = rules;
    }

    public Set<SuperiorityRelation> getSuperiorityRelations() {
        return superiorityRelations;
    }

    public void setSuperiorityRelations(Set<SuperiorityRelation> superiorityRelations) {
        this.superiorityRelations = superiorityRelations;
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