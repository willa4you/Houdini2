package com.example.demo.LogicModel;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LiteralChecker {
    //Internal state ?
    public Set<String> literals = new TreeSet<String>();

    public Set<String> getLiterals() {
       return literals;
    }

    public ArrayList<Boolean> validate_facts(List<String> s) {
        ArrayList<Boolean> bool_array = new ArrayList<Boolean>();
        for (String fact : s) {
            if (!validate_literal(fact)) bool_array.add(false);
            else bool_array.add(true);
        }
        return bool_array;
    }

    public ArrayList<Boolean> validate_rules(List<String> s) {
        ArrayList<Boolean> bool_array = new ArrayList<Boolean>();
        //if (s.size() == 1 && s.get(0) == "") return true; //empty rules

        /* TODO is this possible with regex?
        for (String rule : s) {
            //check whether matches the regex (but only if it's not empty)
            if(rule != "" && !rule.matches("[^ ,-=><~¬\'\";]+[ ]*[-=~]>[ ]*[^ ,-=><~¬\'\";]+")) return false;
           
        }
        return true;
        */
        for (String rule : s) {
            if (rule == "") {
                bool_array.add(true);
                continue;
            }
            String[] splitted_rule = rule.trim().split(">");
            if (splitted_rule.length != 2) {
                bool_array.add(false);
                continue;
            }

            String tail = splitted_rule[0].trim();
            String head = splitted_rule[1].trim();
            
            if (!tail.endsWith("-") && !tail.endsWith("=") && !tail.endsWith("~")) {
                bool_array.add(false);
                continue;
            }
            
            //now, if tail.length() == 1, then the tail is empty
            //check tail if not empty
            if (tail.length() > 1) {
                String[] tail_literals = tail.substring(0, tail.length() - 1).split(",");
                for (String t_l : tail_literals) {
                    if (!validate_literal(t_l)) {
                        bool_array.add(false);
                        continue;
                    }
                }
            }
            
            //check head if not empty (if empty, it's an error)
            if (head.length() == 0) {
                bool_array.add(false);
                continue;
            }
            if (!validate_literal(head)) bool_array.add(false);
            else bool_array.add(true);
        }
        return bool_array;
        
    }

    public ArrayList<Boolean> validate_supRules(List<String> s, List<String> rules) {
        ArrayList<Boolean> bool_array = new ArrayList<Boolean>();
        //Empty
        if (s.size() == 1 && s.get(0) == "") {
            bool_array.add(true);
            return bool_array;
        }
        for (String rule : s) {
            if (rule == "") {
                bool_array.add(true);
                continue;
            }
            //check whether matches the regex (but only if it's not empty)
            if(!rule.matches("r[1-9][0-9]*[ ]*[<>][ ]*r[1-9][0-9]*")) {
                bool_array.add(false);
            }
            String[] split_suprel = rule.split(">");
            if (split_suprel.length == 1) //it is <
            split_suprel = rule.split("<");
            int[] indexes = {Integer.valueOf(split_suprel[0].trim().substring(1)), Integer.valueOf(split_suprel[1].trim().substring(1))};
            int ruleslength = rules.size();
            boolean correctindex = true;
            for (int i : indexes) {
                if (i > ruleslength || rules.get(i-1)=="") {
                    bool_array.add(false);
                    correctindex = false;
                    break;
                }
            }
            if (correctindex) bool_array.add(true);
            
        }
        return bool_array;

    }
    public Boolean validate_literal(String s) {
        String s_trimmed = s.trim();
        String[] banned = {" ", ",", "_", ";", "'", "\"", "\\", ">", "<", "!", "-", "=", "¬"};

        for (String b : banned) {
            //trim the fact so it doesn't contain trailing and intiial spaces, then checks for banned symbols
            if (s_trimmed.contains(b)) {
                literals = new TreeSet<String>();
                return false;
            };
        }
        if (s_trimmed.length() > 0) literals.add(s_trimmed);
        return true;
    }
}
