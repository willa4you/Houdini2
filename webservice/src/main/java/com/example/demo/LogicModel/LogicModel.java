package com.example.demo.LogicModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogicModel {

  private List<String> facts = new ArrayList<String>(Arrays.asList(""));
  private List<String> rules = new ArrayList<String>(Arrays.asList(""));
  private List<String> supRules = new ArrayList<String>(Arrays.asList(""));

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

}