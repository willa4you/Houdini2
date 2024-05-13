package it.univr.houdini.LogicModel;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LogicModel {

  private List<String> facts = new ArrayList<String>(Arrays.asList(""));
  private List<String> rules = new ArrayList<String>(Arrays.asList(""));
  private List<String> supRules = new ArrayList<String>(Arrays.asList(""));
  @JsonIgnore
  private boolean ambiguityPropagation;

  public boolean isAmbiguityPropagation() {
    return ambiguityPropagation;
  }

  public void setAmbiguityPropagation(boolean ambiguityPropagation) {
    this.ambiguityPropagation = ambiguityPropagation;
  }

  public List<String> getFacts() {
    return facts;
  }

  public void setFacts(List<String> facts) {
    this.facts = facts;
  }

  public List<String> getRules() {
    return rules;
  }

  public void setRules(List<String> rules) {
    this.rules = rules;
  }

  public List<String> getSupRules() {
    return supRules;
  }

  public void setSupRules(List<String> supRules) {
    this.supRules = supRules;
  }

  public String toJSONString() {
    try {
      return new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(this);
    }
    catch(Exception e) {
      System.out.println("Couldn't write LogicModel to JSON");
    }
    return "Error";
  }

  public String toCustomJSONString() {
    try {
      JsonFactory factory = new JsonFactory();
      StringWriter jsonObjectWriter = new StringWriter();
      JsonGenerator generator = factory.createGenerator(jsonObjectWriter);
      generator.useDefaultPrettyPrinter(); // pretty print JSON
      generator.writeStartObject(); // start global object
      generator.writeFieldName("facts");
      generator.writeStartArray(); // start facts array
      for (String f : facts) {
        if (f.equals("")) {continue;}
        f = f.replaceAll("\\p{Z}",""); // remove ALL whitespaces
        generator.writeStartObject();
        generator.writeFieldName("label");
        generator.writeString(f.startsWith("~") ? f.substring(1) : f);
        generator.writeFieldName("type");
        generator.writeString(f.startsWith("~") ? "negative" : "positive");
        generator.writeEndObject();
      }
      generator.writeEndArray(); // end facts array
      generator.writeFieldName("rules");
      generator.writeStartArray(); // start rules array
      int ir = 1;
      for (String r : rules) {
        if (r.equals("")) {continue;}
        r = r.replaceAll("\\p{Z}",""); // remove ALL whitespaces
        generator.writeStartObject(); // start rule object
        generator.writeFieldName("label");
        generator.writeString("r"+(ir++));
        generator.writeFieldName("type");
        switch(r.substring(r.indexOf(">") - 1, r.indexOf(">")).charAt(0)) {
          case '-' : generator.writeString("strict"); break;
          case '=' : generator.writeString("defeasible"); break;
          case '~' : generator.writeString("defeater"); break;
          default : generator.writeString("strict"); break;
        }
        String head = r.substring(r.indexOf(">") + 1);
        generator.writeFieldName("head");
        generator.writeStartObject(); // start head object
        generator.writeFieldName("label");
        generator.writeString(head.startsWith("~") ? head.substring(1) : head);
        generator.writeFieldName("type");
        generator.writeString(head.startsWith("~") ? "negative" : "positive");
        generator.writeEndObject(); // end head object
        String[] tail = r.substring(0, r.indexOf(">") - 1).split(",");
        generator.writeFieldName("tail");
        generator.writeStartArray(); // start tail literals array
        for (String l : tail) {
          if (l.equals("")) {continue;}
          generator.writeStartObject();
          generator.writeFieldName("label");
          generator.writeString(l.startsWith("~") ? l.substring(1) : l);
          generator.writeFieldName("type");
          generator.writeString(l.startsWith("~") ? "negative" : "positive");
          generator.writeEndObject();
        }
        generator.writeEndArray(); // end tail literals array
        generator.writeEndObject(); // end rule object
      }
      generator.writeEndArray(); // end rules array
      generator.writeFieldName("superiorityRelations");
      generator.writeStartArray(); // start supRelations array
      for (String sr : supRules) {
        if (sr.equals("")) {continue;}
        generator.writeStartObject();
        generator.writeFieldName("superior");
        generator.writeString(sr.substring(0, sr.indexOf(">")));
        generator.writeFieldName("inferior");
        generator.writeString(sr.substring(sr.indexOf(">") + 1));
        generator.writeEndObject();
      }
      generator.writeEndArray(); // end supRel array
      generator.writeEndObject(); // end global object
      generator.close(); // to close the generator
      return jsonObjectWriter.toString();
      //System.out.println(myJSON);
    }
    catch(Exception e) {
      System.out.println("Couldn't write LogicModel to JSON");
      return "Couldn't write LogicModel to JSON";
    }
  }
}