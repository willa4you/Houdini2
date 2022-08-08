package com.example.demo.LogicModel;

import java.util.ArrayList;
import java.util.Arrays;

public class LogicModel {

  private ArrayList<String> facts = new ArrayList<String>(Arrays.asList(""));
  private ArrayList<String> rules = new ArrayList<String>(Arrays.asList(""));
  private ArrayList<String> supRules = new ArrayList<String>(Arrays.asList(""));

  public ArrayList<String> getFacts() {
    return facts;
  }

  public void setFacts(ArrayList<String> facts) {
    this.facts = facts;
  }

  public ArrayList<String> getRules() {
    return rules;
  }

  public void setRules(ArrayList<String> rules) {
    this.rules = rules;
  }

  public ArrayList<String> getSupRules() {
    return supRules;
  }

  public void setSupRules(ArrayList<String> supRules) {
    this.supRules = supRules;
  }

}