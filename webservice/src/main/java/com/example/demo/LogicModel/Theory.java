package com.example.demo.LogicModel;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.demo.LogicModel.Literal.LiteralType;
import com.example.demo.LogicModel.Literal.ExtensionState;
import com.example.demo.LogicModel.Rule.RuleType;

public class Theory {
    private TreeSet<Literal> literals = new TreeSet<Literal>();
    private Set<Literal> facts = new HashSet<Literal>();
    private TreeSet<Rule> rules = new TreeSet<Rule>(); // there can not be duplicate rules by design
    
    public Theory(String JSONTheoryString) throws IOException {

        byte[] jsonData = JSONTheoryString.getBytes();
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // TODO: actually we don't know the source of this JSON, so we need controls to check its validity

        //read JSON like DOM Parser
        JsonNode rootNode = objectMapper.readTree(jsonData);
        
        // extract facts
        JsonNode factsNode = rootNode.path("facts");
        Iterator<JsonNode> elements = factsNode.elements();
        while(elements.hasNext()){
            JsonNode factNode = elements.next();
            Literal fact = getLiteralAndUpdateLiterals(factNode);
            facts.add(fact);
        }
        // end facts
        
        // extract rules
        HashSet<String> ruleNames = new HashSet<String>(); // we don't want rules having the same name
        JsonNode rulesNode = rootNode.path("rules");
        elements = rulesNode.elements(); // reusing elements variable
        while(elements.hasNext()) {
            JsonNode ruleNode = elements.next();
            String label = ruleNode.get("label").asText();
            if(ruleNames.contains(label)) {
                System.out.println("Theories with duplicate rule names are forbidden.");
                System.exit(1);
                // TODO: here we must throw an exception or something throw new IOException("Bla bla.");
            }
            RuleType ruleType;
            switch(ruleNode.get("type").asText()) {
                case "strict" : ruleType = RuleType.STRICT; break;
                case "defeasible" : ruleType = RuleType.DEFEASIBLE; break;
                case "defeater" : ruleType = RuleType.DEFEATER; break;
                default : ruleType = RuleType.STRICT; break;
            }
            Literal head = getLiteralAndUpdateLiterals(ruleNode.get("head"));
            JsonNode tailNode = ruleNode.get("tail");
            TreeSet<Literal> tailSet = new TreeSet<>();
            Iterator<JsonNode> tailElements = tailNode.elements();
            while(tailElements.hasNext()) {
                JsonNode tailLiteral = tailElements.next();
                tailSet.add(getLiteralAndUpdateLiterals(tailLiteral));
            }
            Rule rule = new Rule(
                label,
                ruleType,
                head,
                tailSet
                );
            head.addRuleIsHeadOf(rule); // adding this rule to the rules this head literal is head of
            rules.add(rule); // adding this rule to the theory rules
        } // end rules while
        // end rules
            
        // extract superiority relations
        JsonNode supRelsNode = rootNode.path("superiorityRelations");
        elements = supRelsNode.elements(); // reusing elements variable
        while(elements.hasNext()) {
            JsonNode supRelNode = elements.next();
            Rule superior = new Rule(supRelNode.get("superior").asText()); // temporary to check presence of rule
            Rule inferior = new Rule(supRelNode.get("inferior").asText()); // temporary to check presence of rule
            // we want to avoid relations reffering to non existing rules
            if (rules.contains(superior) && rules.contains(inferior)) { // this is only based on labels, we use temporary rules
                // if they are present (as labels) i get the real ones
                superior = rules.ceiling(superior);
                inferior = rules.ceiling(inferior);
                // now we also want to avoid to store superiority relations about non complementary heads
                if (superior.getHead() == inferior.getHead().getOpposite()) { // we use == because our references are coherent
                    superior.addToWinsOver(inferior);
                    inferior.addToLosesAfter(superior);
                }
            }
        } // end while supRels
        // end superiority relations

    } // end constructor

    /**
     * (Used only by the constructor) Checks the JSON literal and if present, returns the existing one.
     * If not present, adds the new one to the literals set and sets the opposite reference if the opposite is already present.
     * @param literalNode The literal JSON object
     * @return The literal object, new or existing, associated to the literal JSON object.
     */
    private Literal getLiteralAndUpdateLiterals(JsonNode literalNode) {
        // first we create an instance of an object associated to the literal JSON object
        Literal literal = new Literal(literalNode.get("label").asText(),
            (literalNode.get("type").asText().equals("positive") ? LiteralType.POSITIVE : LiteralType.NEGATIVE)
            );
        // if it is already existing
        if (this.literals.contains(literal)) {
            return this.literals.ceiling(literal); // we return and use the object already existing
        }
        
        // else
        // gotta control whether the opposite is already in the set and, if so, we update each other reference
        Literal opposite = new Literal(literalNode.get("label").asText(),
        (literalNode.get("type").asText().equals("positive") ? LiteralType.NEGATIVE : LiteralType.POSITIVE)
        ); // same label, but second parameter is the inverse of lit
        if (this.literals.contains(opposite)) {
            opposite = this.literals.ceiling(opposite); // taking the original one
            opposite.setOpposite(literal);
            literal.setOpposite(opposite);
        }
        
        // we add the new object to literals and we return it
        literals.add(literal);
        return literal;
    
    }

    public Set<Literal> getLiterals() {
        return literals;
    }
    public Set<Literal> getLiterals(ExtensionState s) {
        return literals.stream().filter(l -> l.getPartialState().equals(s)).collect(Collectors.toSet());
    }

    public Set<Literal> getFacts() {
        return facts;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public List<Rule> getRules(RuleType ruleType) {
        return getRules(new ArrayList<RuleType>(Arrays.asList(ruleType)));
    }

    public List<Rule> getRules(ArrayList<RuleType> ruleTypes) {
        return this.rules.stream().filter(rule -> ruleTypes.contains(rule.getType())).collect(Collectors.toList());
    }

    public void removeRule(Rule r) {
        this.rules.remove(r);
    }

    public Set<Literal> getHeads(Set<Rule> rules){
        Set<Literal> heads = new TreeSet<>();
        for (Rule r:rules) {
            heads.add(r.getHead());
        }
        return heads;
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