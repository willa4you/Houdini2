package it.univr.houdini.model;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LogicModelController {
  private List<Boolean> areFactsValid = new ArrayList<Boolean>(Arrays.asList(true));
  private List<Boolean> areRulesValid = new ArrayList<Boolean>(Arrays.asList(true));
  private List<Boolean> areSupRulesValid = new ArrayList<Boolean>(Arrays.asList(true));
  private int factsLength = 0;
  private int rulesLength = 0;
  private int supRulesLength = 0;
  private Boolean loadingFile = false;
  private String plusDeltaString = "∅";
  private String minusDeltaString = "∅";
  private String plusPartialString = "∅";
  private String minusPartialString = "∅";
  private String undecidableDeltaBecauseOfLoops;
  private String undecidablePartialBecauseOfLoops;
  private String literalsInvolvedInSupRelCycles;
  private String literalsValidation;
  private String ambiguityPropagationString;
  private String ambiguousLiterals;
  private boolean is_there_a_json_error_message = false;

  @ModelAttribute("is_there_a_json_error_message")
  private boolean getIsThereAJsonErrorMessage() {
    return this.is_there_a_json_error_message;
  }

  @ModelAttribute("are_facts_valid")
  private List<Boolean> getAreFactsValid() {
    return this.areFactsValid;
  }

  @ModelAttribute("are_rules_valid")
  private List<Boolean> getAreRulesValid() {
    return this.areRulesValid;
  }

  @ModelAttribute("are_suprules_valid")
  private List<Boolean> getAreSupRulesValid() {
    return this.areSupRulesValid;
  }

  @ModelAttribute("facts_length")
  private int getFactsLength() {
    return this.factsLength;
  }

  @ModelAttribute("rules_length")
  private int getRulesLength() {
    return this.rulesLength;
  }

  @ModelAttribute("suprules_length")
  private int getSupRulesLength() {
    return this.supRulesLength;
  }

  @ModelAttribute("loading_file")
  private Boolean getLoadingFile() {
    return this.loadingFile;
  }

  @ModelAttribute("plus_delta_string")
  private String getPlusDeltaString() {
    return this.plusDeltaString;
  }

  @ModelAttribute("minus_delta_string")
  private String getMinusDeltaString() {
    return this.minusDeltaString;
  }

  @ModelAttribute("plus_partial_string")
  private String getPlusPartialString() {
    return this.plusPartialString;
  }

  @ModelAttribute("minus_partial_string")
  private String getMinusPartialString() {
    return this.minusPartialString;
  }

  @ModelAttribute("undecidable_delta_loops_string")
  private String getUndecidableDeltaBecauseOfLoops() {
    return this.undecidableDeltaBecauseOfLoops;
  }

  @ModelAttribute("undecidable_partial_loops_string")
  private String getUndecidablePartialBecauseOfLoops() {
    return this.undecidablePartialBecauseOfLoops;
  }

  @ModelAttribute("literalsInvolvedInSupRelCycles_string")
  private String getLiteralsInvolvedInSupRelCycles() {
    return this.literalsInvolvedInSupRelCycles;
  }

  @ModelAttribute("literalsValidation_string")
  private String getLiteralsValidation() {
    return this.literalsValidation;
  }

  @ModelAttribute("ambiguityPropagation_string")
  private String getAmbiguityPropagationString() {
    return this.ambiguityPropagationString;
  }

  @ModelAttribute("ambiguous_literals_string")
  private String getAmbiguousLiterals() {
    return this.ambiguousLiterals;
  }

  public void configFromLogicModel() throws IOException, ParseCancellationException  {

    List<String> facts = new ArrayList<String>(); // NON HA SENSOOOO NIENTEEEE QUESTA ROBA È QUI SOLO PER DARE UN'OCCHIATA
    List<String> rules = new ArrayList<String>();
    List<String> supRules = new ArrayList<String>();

    this.areFactsValid = facts.stream().map(f -> true).collect(Collectors.toList());
    this.areFactsValid = rules.stream().map(f -> true).collect(Collectors.toList());
    this.areFactsValid = supRules.stream().map(f -> true).collect(Collectors.toList());
    //to deal with the case where the inputs are wrong and we need to
    //resend the data back. This will let the home.html js to correctly add labels
    //and tags to new fact input fields that are dynamically generated when the user
    //inserts some characters. -1 is necessary because otherwise it'd off by one
    this.factsLength = (facts.size()-1 < 0) ? 0 : facts.size()-1;
    this.rulesLength = (rules.size()-1 < 0) ? 0 : rules.size()-1;
    this.supRulesLength = (supRules.size()-1 < 0) ? 0 : supRules.size()-1;

    /* Validating */

    String JSONcontent = "logicModel.toJSONString()"; // BASIC STYLE JSON
    //ModelParser.parse(JSONcontent); //Here throws ParseCancellationException if wrong
    
    //JSONcontent = logicModel.toCustomJSONString(); // THIS IS AN ATTEMPT TO FORMALIZE JSON FORMAT
    System.out.println(JSONcontent.replace(" ", ""));
    
    // TODO: json could arrive from http post or some web REST/SOAP interface or upload FATTOOOOO!!!!! ;)

    boolean ambiguityPropagation = true; // TUTTO ANDATO A PUTTANEEEEEEEEEEE
    ambiguityPropagationString = ambiguityPropagation ? "ON" : "OFF";

    ModelExtensionComputator theoryExtension = new ModelExtensionComputator(JSONcontent);
    Validator validator = new Validator().validate(theoryExtension.getLiterals());

    this.plusDeltaString = theoryExtension.getPlusDeltaString();
    this.minusDeltaString = theoryExtension.getMinusDeltaString();
    this.plusPartialString = theoryExtension.getPlusPartialString();
    this.minusPartialString = theoryExtension.getMinusPartialString();
    this.undecidableDeltaBecauseOfLoops = theoryExtension.getUndecidablesDeltaInStrictRulesLoop();
    this.undecidablePartialBecauseOfLoops = theoryExtension.getUndecidablesPartialInRulesLoop();
    this.literalsInvolvedInSupRelCycles = validator.getLiteralsInvolvedInSupRelCycles();
    this.literalsValidation = validator.getLiteralsValidation();
    this.ambiguousLiterals = theoryExtension.getAmbiguousLiterals();
  }

  @GetMapping("/sets")
  public String setForm(Model model) {
    return "sets";
  }
  
}
