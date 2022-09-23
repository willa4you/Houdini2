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
    private List<Literal> literals = new ArrayList<Literal>(); // constructor already checks there will be no duplicates
    private HashSet<Literal> onlyDefeasibleLiterals = new HashSet<Literal>();
    private Set<Literal> strictConclusions = new HashSet<Literal>(); // we don't want duplicates and later we'll need to do contains in O(1)
    private List<Rule> rules = new ArrayList<Rule>(); // there can not be duplicate rules by design: if duplicate name is found, program halts
    
    public Theory(String JSONTheoryString) throws IOException {
        
        // important: in order to retrieve the original literal every time we encounter the same JSON literal
        // we implement a Map where key = value, which allows us to look for a literal and retrieve it
        Map<Literal, Literal> mapOfLiterals = new HashMap<>();

        // TODO: this is based on a custom formalization of JSON, currently not accepted in the project
        byte[] jsonData = JSONTheoryString.getBytes();
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
        //read JSON like DOM Parser
        JsonNode rootNode = objectMapper.readTree(jsonData);
        
        // extract facts
        JsonNode factsNode = rootNode.path("facts");
        Iterator<JsonNode> elements = factsNode.elements();
        while(elements.hasNext()){
            JsonNode factNode = elements.next();
            Literal fact = manageFoundedJSONLiterals(factNode, mapOfLiterals);
            strictConclusions.add(fact);
            fact.setPlusDelta();
            fact.setPlusPartial(); // early assignment
        }
        // end facts
        
        // extract rules
        // we create a map to check duplicate names and which we will use to retrieve rules when evaluating superiority relations
        Map<String, Rule> mapOfRules = new HashMap<String, Rule>();
        
        JsonNode rulesNode = rootNode.path("rules");
        elements = rulesNode.elements(); // reusing "elements" variable
        while(elements.hasNext()) {
            JsonNode ruleNode = elements.next();
            String ruleLabel = ruleNode.get("label").asText();
            if(mapOfRules.get(ruleLabel) != null) { // there are two rules with the same name and this is forbidden
                System.out.println("Rules with the same name are not allowed in a well formed theory.");
                System.exit(1);
                // TODO: here we must throw an exception or something throw new Exception("Bla bla.");
            }
            RuleType ruleType;
            switch(ruleNode.get("type").asText()) {
                case "strict" : ruleType = RuleType.STRICT; break;
                case "defeasible" : ruleType = RuleType.DEFEASIBLE; break;
                case "defeater" : ruleType = RuleType.DEFEATER; break;
                default : ruleType = RuleType.STRICT; break; // TODO: this should be already checked
            }
            Literal head = manageFoundedJSONLiterals(ruleNode.get("head"), mapOfLiterals);
            JsonNode tailNode = ruleNode.get("tail");
            HashSet<Literal> tail = new HashSet<>(); // TODO: should duplicate literals in a tail be allowed?
            Iterator<JsonNode> tailElements = tailNode.elements();
            while(tailElements.hasNext()) {
                JsonNode tailLiteral = tailElements.next();
                tail.add(manageFoundedJSONLiterals(tailLiteral, mapOfLiterals));
            }
            Rule rule = new Rule(ruleLabel, ruleType, head, tail);
            head.addRuleIsHeadOf(rule); // adding this rule to the rules this literal is head of
            // also, we want already check if this is an empty tail strict rule, because, in case, the head is a strict conclusion
            if (ruleType == RuleType.STRICT && tail.isEmpty()) { // rules of type '-> a'
                head.setPlusDelta();
                head.setPlusPartial(); // early assignment
                strictConclusions.add(head);
            }
            rules.add(rule); // adding this rule to the theory global list rules
            mapOfRules.put(ruleLabel, rule);
        } // end rules while
        // end rules
            
        // extract superiority relations
        JsonNode supRelsNode = rootNode.path("superiorityRelations");
        elements = supRelsNode.elements(); // reusing elements variable
        while(elements.hasNext()) {
            JsonNode supRelNode = elements.next();
            Rule superior = mapOfRules.get(supRelNode.get("superior").asText());
            Rule inferior = mapOfRules.get(supRelNode.get("inferior").asText());
            // we want to avoid relations reffering to non existing rules
            if (superior != null && inferior != null) { // if both names are presents
                // now we also want to avoid to store superiority relations about non complementary heads
                if (superior.getHead() == inferior.getHead().getOpposite()) { // we use == because our references are coherent
                    superior.addToWinsOver(inferior);
                    inferior.addToLosesAfter(superior);
                } else {
                    // TODO: WARNING superiority relations with non complementary heads
                    System.out.println("Non complementary head rules founded in a superiority relation:");
                    System.out.println(supRelNode.asText());
                }
            } else {
                // TODO: WARNING superiority relations with wrong name
                System.out.println("Wrong rule name founded in a superiority relation:");
                System.out.println(supRelNode.asText());
            }
        } // end while supRels
        // end superiority relations

    } // end constructor

    /**
     * (Used only by the constructor) Checks the JSON literal and if present, returns the existing one.
     * If not present, checks if it is the complementary of an existing literal and link each other,
     * then adds the new one to the literals map for further checks and global literals list.
     * @param literalNode The literal JSON object
     * @param mapOfLiterals A Map containing the already created literals (modified by the process)
     * @return The literal object, new or existing, associated to the literal JSON object.
     */
    private Literal manageFoundedJSONLiterals(JsonNode literalNode, Map<Literal, Literal> mapOfLiterals) {
        String literalLabel = literalNode.get("label").asText();
        String literalType = literalNode.get("type").asText();
        // first we create an instance of an object associated to the JSON literal
        Literal newLiteral = new Literal(
            literalLabel,
            (literalType.equals("positive") ? LiteralType.POSITIVE : LiteralType.NEGATIVE)
        );

        Literal actualLiteral = mapOfLiterals.get(newLiteral);
        if (actualLiteral != null) { // aready in the map
            return actualLiteral;
        }

        // IF IT IS A BRAND NEW LITERAL
        // first we check if it is complementary of an existing one and, in case, we link each other
        Literal opposite = mapOfLiterals.get(
            new Literal(
                literalLabel, // this works only with a JSON structure where complementary literals shares the same label
                (literalType.equals("positive") ? LiteralType.NEGATIVE : LiteralType.POSITIVE)
            ) // same label, but second parameter is inverted
        ); // we try and get the existing opposite, if present
                
        if (opposite != null) { // if the opposite is present in the literals map
            // then we must link each other complmentary literal
            opposite.setOpposite(newLiteral);
            newLiteral.setOpposite(opposite);
        } // if the opposite is not present we do nothing
                
        // we put the new Literal for the future and we check if it is opposite of an existing one
        mapOfLiterals.put(newLiteral, newLiteral); // this map has key = value
        literals.add(newLiteral); // we also add it to the literals global list: now we're sure it has no duplicates

        return newLiteral;
    
    }

    public List<Literal> getLiterals() {
        return literals;
    }
    
    public Set<Literal> getLiterals(ExtensionState s) {
        return literals.stream().filter(l -> l.getPartialState().equals(s)).collect(Collectors.toSet());
    }

    public Set<Literal> getStrictConclusions() {
        return strictConclusions;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public List<Rule> getRules(RuleType ruleType) {
        return this.rules.stream().
            filter(rule -> rule.getType() == ruleType).collect(Collectors.toList());
    }

    public List<Rule> getRules(RuleType ruleType1, RuleType ruleType2) {
        return this.rules.stream().
            filter(rule -> (rule.getType() == ruleType1 || rule.getType() == ruleType2)).collect(Collectors.toList());
    }
}