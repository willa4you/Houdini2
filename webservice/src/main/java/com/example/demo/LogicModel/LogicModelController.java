package com.example.demo.LogicModel;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.example.demo.FileUpload.storage.StorageService;
import com.example.demo.JSONParser.EmptyFileException;
import com.example.demo.JSONParser.JSONLogicParser;
import com.example.demo.JSONParser.WrongFormatException;

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
  private LiteralChecker validator = new LiteralChecker();
  private ArrayList<Boolean> areFactsValid = new ArrayList<Boolean>(Arrays.asList(true));
  private ArrayList<Boolean> areRulesValid = new ArrayList<Boolean>(Arrays.asList(true));
  private ArrayList<Boolean> areSupRulesValid = new ArrayList<Boolean>(Arrays.asList(true));
  private int factsLength = 0;
  private int rulesLength = 0;
  private int supRulesLength = 0;
  private Boolean loadingFile = false;
  
  @ModelAttribute("logic_model")
  private LogicModel getLogicModel() {
    return this.logicModel;
  }

  @ModelAttribute("validator")
  private LiteralChecker getValidator() {
    return this.validator;
  }

  @ModelAttribute("are_facts_valid")
  private ArrayList<Boolean> getAreFactsValid() {
    return this.areFactsValid;
  }

  @ModelAttribute("are_rules_valid")
  private ArrayList<Boolean> getAreRulesValid() {
    return this.areRulesValid;
  }

  @ModelAttribute("are_suprules_valid")
  private ArrayList<Boolean> getAreSupRulesValid() {
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

  public void configFromLogicModel(LogicModel logicModel) {
    this.logicModel = logicModel;

    ArrayList<String> facts = logicModel.getFacts();
    ArrayList<String> rules = logicModel.getRules();
    ArrayList<String> supRules = logicModel.getSupRules();

    //to deal with the case where the inputs are wrong and we need to
    //resend the data back. This will let the home.html js to correctly add labels
    //and tags to new fact input fields that are dynamically generated when the user
    //inserts some characters. -1 is necessary because otherwise it'd off by one
    this.factsLength = (facts.size()-1 < 0) ? 0 : facts.size()-1;
    this.rulesLength = (rules.size()-1 < 0) ? 0 : rules.size()-1;
    this.supRulesLength = (supRules.size()-1 < 0) ? 0 : supRules.size()-1;

    LiteralChecker validator = new LiteralChecker();
    
    this.areFactsValid = validator.validate_facts(facts);
    this.areRulesValid = validator.validate_rules(rules);
    this.areSupRulesValid = validator.validate_supRules(supRules);
  }

	@Autowired
	public LogicModelController(StorageService storageService) {
		this.storageService = storageService;
	}

  @GetMapping("/")
  public String homeForm(Model model) {
    this.configFromLogicModel(new LogicModel());
    
    //If the following line is not added then, for some reason, when
    //coming back from "sets.html" by clicking on the "Houdini" title
    //the logic_model is not updated in the view, but only here, 
    //in the field this.logicModel
    //model.addAttribute("logic_model", this.getLogicModel());
    return "home";
  }

    
  @GetMapping("/sets")
  public String setForm(Model model) {
    return "sets";
  }

  @PostMapping("/")
  public String homeSubmit(@ModelAttribute LogicModel logicModel, Model model) {
    
    //Update data
    this.configFromLogicModel(logicModel);
    
    if (this.getAreFactsValid().contains(false) || this.getAreRulesValid().contains(false) || this.getAreSupRulesValid().contains(false)) {
      return "redirect:/";
    } 
    else {
      
      return "redirect:/sets";
    }
    
  }

	@PostMapping("/upload")
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes, Model model) throws IOException, WrongFormatException {

		//storageService.store(file);

    //TODO handle IOException here
    String content = new String(file.getBytes());
    
    JSONLogicParser parser = new JSONLogicParser(content);
    
    try {
      LogicModel parsed_model = parser.parseJson();
      this.configFromLogicModel(parsed_model);
    } catch(WrongFormatException e) {
      redirectAttributes.addFlashAttribute("json_error_message", e.getMessage());
      return "redirect:/";
    }
      
    //redirectAttributes.addFlashAttribute("loading_file", true);
    //redirectAttributes.addFlashAttribute("file_content", content);
        
		return "redirect:/sets";
	}
  
}