package com.example.demo.LogicModel;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.FileUpload.storage.StorageService;

import com.example.demo.JSONParser.JSONLogicParser;
import com.example.demo.JSONParser.JSONWrongFormatException;
import com.example.demo.LogicModel.grammar.ModelParser;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LogicModelController {

  private final StorageService storageService;
  private LogicModel logicModel = new LogicModel();
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
  private boolean is_there_a_json_error_message = false;

  @ModelAttribute("is_there_a_json_error_message")
  private boolean getIsThereAJsonErrorMessage() {
    return this.is_there_a_json_error_message;
  }
  @ModelAttribute("logic_model")
  private LogicModel getLogicModel() {
    return this.logicModel;
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

  public void configFromLogicModel(LogicModel logicModel) throws IOException, ParseCancellationException  {
    this.logicModel = logicModel;

    List<String> facts = logicModel.getFacts();
    List<String> rules = logicModel.getRules();
    List<String> supRules = logicModel.getSupRules();

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

    String JSONcontent = logicModel.toJSONString(); // BASIC STYLE JSON
    ModelParser.parse(JSONcontent); //Here throws ParseCancellationException if wrong
    
    JSONcontent = logicModel.toCustomJSONString(); // THIS IS AN ATTEMPT TO FORMALIZE JSON FORMAT
    System.out.println(JSONcontent.replace(" ", ""));
    
    // TODO: json could arrive from http post or some web REST/SOAP interface
    TheoryExtension theoryExtension = new TheoryExtension(JSONcontent).computeExtension();
    Validator validator = new Validator().validate(theoryExtension.getLiterals());

    this.plusDeltaString = theoryExtension.getPlusDeltaString();
    this.minusDeltaString = theoryExtension.getMinusDeltaString();
    this.plusPartialString = theoryExtension.getPlusPartialString();
    this.minusPartialString = theoryExtension.getMinusPartialString();
    this.undecidableDeltaBecauseOfLoops = theoryExtension.getInLoopRulesDeltaString();
    this.undecidablePartialBecauseOfLoops = theoryExtension.getInLoopRulesPartialString();
    this.literalsInvolvedInSupRelCycles = validator.getLiteralsInvolvedInSupRelCycles();
    this.literalsValidation = validator.getLiteralsValidation();
  }

	@Autowired
	public LogicModelController(StorageService storageService) {
		this.storageService = storageService;
	}

  @GetMapping("/")
  public String homeForm(Model model, @RequestParam(name = "is_there_a_json_error_message", defaultValue = "false") boolean is_there_a_json_error_message) {
    this.is_there_a_json_error_message = (boolean)(is_there_a_json_error_message);
    try {
      this.configFromLogicModel(new LogicModel());
    } catch(Exception e) {
      //It's impossibible to have an exception here
      System.out.println("Exception in GET /");
    }
    return "home";
  }

  //Use this route to clear model
  @GetMapping("/home")
  public String home2Form(Model model) {
    try {
      this.configFromLogicModel(new LogicModel());
      model.addAttribute("logic_model", new LogicModel());
    } catch(IOException e) {
      //It's impossibible to have an exception here
      System.out.print(e);
    }
    return "redirect:/";
  }

  @GetMapping("/sets")
  public String setForm(Model model) {
    return "sets";
  }

  @PostMapping("/")
  public String homeSubmit(@ModelAttribute LogicModel logicModel, Model model) {

    //Update data
    try {
      this.configFromLogicModel(logicModel);
      return "redirect:/sets";
    } catch(IOException e) {
      return "redirect:/";
    }
  }

	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes, Model model) throws IOException, JSONWrongFormatException {

		//storageService.store(file);

    //TODO handle IOException here
    String content = new String(file.getBytes());

    JSONLogicParser parser = new JSONLogicParser(content);

    try {
      LogicModel parsed_model = parser.parseJson();
      this.configFromLogicModel(parsed_model);
      return "redirect:/sets";
    } catch(JSONWrongFormatException e) {
      //JSON is not formatted correctly
      redirectAttributes.addFlashAttribute("json_error_message", e.getMessage());
      return "redirect:/";
    } catch(IOException e) {
      //JSON is formatted correctly but its facts, rules etc. are NOT
      return "redirect:/";
    }
	}
  
}
