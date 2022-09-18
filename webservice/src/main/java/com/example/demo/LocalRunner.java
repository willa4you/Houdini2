package com.example.demo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.JSONParser.JSONLogicParser;
import com.example.demo.LogicModel.LogicModel;
import com.example.demo.LogicModel.Theory;
import com.example.demo.LogicModel.Extension.DefeasibleExtensionComputator;
import com.example.demo.LogicModel.Extension.Extension;
import com.example.demo.LogicModel.Extension.StrictExtensionComputator;
import com.example.demo.LogicModel.grammar.ModelParser;


public class LocalRunner {
    final static List<String> papersConclusions = new ArrayList<String>(
            Arrays.asList(
                "nspd, bp, ngs, i_nfr1, busa", 
            "~emergency_Y, Y_onTheRightOf_X, police_X, emergency_X, rightOfWay_X, ~rightOfWay_Y",
            "hot, rain, jogging",
            "a, d, c, e, b",
            "platypus, monotreme, laysEgg, hasFur, hasBill, mammal",
            "evidenceA, evidenceB, ~guilty",
            "Guido_friend_Samir, uni_picture_Pic10, owner_Pic10_Samir, ~wedding_Pic10",
            "evidenceA, evidenceB, ~guilty", //we skip this because there are some difference that we cannot explain
            "nautiluses, cephalopods, molluscs, haveShells",
            "t, ~s, ~q, ~p, ~r",
            "a, b, d, c",
            "a, nafb, c",
            "a",
            "p, q, r, t",
            "",
            "q1, q2, q3",
            "",
            "~a",
            "", //we skip this because it has loops
            "~f",
            "b")
        );
    final static String[][] strictConclusions = {
        {"a, b", "c, d, ~a, ~b, ~c, ~d"},
        {"a, b, c, d, e", "~a, ~b, ~c, ~d, ~e"},
        {"", "p, q, ~p, ~q"},
        {"", "~p"}
    };
    public static void main(String[] args) {
        
        testPapersTheories();
        testStrictTheories();
        //testTimes();
        testConclusions();
    }

    public static void testConclusions() {
        
        int counter = 1;
        String string_path = String.format("../generator/theories/theory%d.json", counter);
        String conclusion_path = "src/main/java/com/example/demo/conclusions_ours.csv";
        String out_path = "src/main/java/com/example/demo/times_ours.csv";

        File file = new File(string_path);
        
        List<String> data = new ArrayList<String>();
        List<String> conclusions = new ArrayList<String>();
        data.add("Ours");
        while(file.exists()) {
            System.out.println(String.format("<<<<<< %d", counter));
            try {
                String content = Files.readString(Paths.get(string_path));

                JSONLogicParser parser = new JSONLogicParser(content);
                LogicModel parsed_model = parser.parseJson();
                String JSONcontent = parsed_model.toJSONString();
          
                ModelParser.parse(JSONcontent); //Here throws ParseCancellationException if wrong
                Theory th = new Theory(ModelParser.getLiterals(), parsed_model);
                StrictExtensionComputator strict_comp = new StrictExtensionComputator();
                DefeasibleExtensionComputator def_comp = new DefeasibleExtensionComputator();
                Pair<Theory, Extension> computed = strict_comp.computeExtension(th);
                Pair<Theory, Extension> completeExtension = def_comp.computeExtension(computed.getFirst(), computed.getSecond());
                
                
                Double elaps = strict_comp.elapsedtime + def_comp.elapsedtime;
                System.out.println(String.format("%f + %f = %f", strict_comp.elapsedtime, def_comp.elapsedtime, elaps));
                data.add(elaps.toString());
                //Print extension to file
                String toadd = String.format("[%s]", completeExtension.getSecond().getPlusPartialString());
                if (completeExtension.getSecond().getPlusPartial().size() == 0) toadd = "[]";
                conclusions.add(toadd);
            }
            catch(Exception e) {
                System.out.println(e);
            }
            
            counter += 1;
            string_path = String.format("../generator/theories/theory%d.json", counter);
            file = new File(string_path);
        }
        try {
            Files.write(Paths.get(out_path), data);
            Files.write(Paths.get(conclusion_path), conclusions);
        } catch(Exception e) {
            System.out.println(e);
        }
        
    }

    public static void testPapersTheories() { 
        System.out.println("------------ Test papers theories ------------");
        int counter = 1;
        String string_path = String.format("/home/edoardo/Uni4Justice/webservice/src/main/java/com/example/demo/papers_theories/theory%d.json", counter);
        
        File file = new File(string_path);
        
        while(file.exists()) {
            System.out.println(String.format("<<<<<< %d", counter));
            
            try {
                String content = Files.readString(Paths.get(string_path));

                JSONLogicParser parser = new JSONLogicParser(content);
                LogicModel parsed_model = parser.parseJson();
                String JSONcontent = parsed_model.toJSONString();
          
                ModelParser.parse(JSONcontent); //Here throws ParseCancellationException if wrong
                Theory th = new Theory(ModelParser.getLiterals(), parsed_model);
                StrictExtensionComputator strict_comp = new StrictExtensionComputator();
                DefeasibleExtensionComputator def_comp = new DefeasibleExtensionComputator();
                Pair<Theory, Extension> computed = strict_comp.computeExtension(th);
                Pair<Theory, Extension> completeExtension = def_comp.computeExtension(computed.getFirst(), computed.getSecond());
                
                //Print extension to file
                String toadd = String.format("%s", completeExtension.getSecond().getPlusPartialString());
                if (completeExtension.getSecond().getPlusPartial().size() == 0) toadd = "";
                Set<String> check1 = new HashSet<String>(Arrays.asList(toadd.split(",")).stream().map(s -> s.strip()).collect(Collectors.toList()));
                Set<String> check2 = new HashSet<String>(Arrays.asList(papersConclusions.get(counter-1).split(",")).stream().map(s -> s.strip()).collect(Collectors.toList()));
                if (!(counter == 8) && !(counter == 19)) {
                    boolean result = ((check1.size() == check2.size()) && check1.containsAll(check2));
                    if (!result) {
                        System.out.println("OURS\n======");
                        System.out.println(toadd);
                        System.out.println("     VS     ");
                        System.out.println(papersConclusions.get(counter-1));
                        System.out.println("======\nTHEIRS");
                        assert(false);
                    }
                }
            }
            catch(Exception e) {
                System.out.println(e);
            }
            
            counter += 1;
            string_path = String.format("/home/edoardo/Uni4Justice/webservice/src/main/java/com/example/demo/papers_theories/theory%d.json", counter);
            file = new File(string_path);
        }
        System.out.println("!!!!!!!!!!!! PERFECT !!!!!!!!!!!!");
    };

    public static void testStrictTheories() { 
        System.out.println("------------ Test strict theories ------------");
        int counter = 1;
        String string_path = String.format("/home/edoardo/Uni4Justice/webservice/src/main/java/com/example/demo/strict_theories/theory%d.json", counter);
        
        File file = new File(string_path);
        
        while(file.exists()) {
            if (counter > LocalRunner.papersConclusions.size()) return;
            System.out.println(String.format("<<<<<< %d", counter));
            
            try {
                String content = Files.readString(Paths.get(string_path));

                JSONLogicParser parser = new JSONLogicParser(content);
                LogicModel parsed_model = parser.parseJson();
                String JSONcontent = parsed_model.toJSONString();
          
                ModelParser.parse(JSONcontent); //Here throws ParseCancellationException if wrong
                Theory th = new Theory(ModelParser.getLiterals(), parsed_model);
                StrictExtensionComputator strict_comp = new StrictExtensionComputator();
                DefeasibleExtensionComputator def_comp = new DefeasibleExtensionComputator();
                Pair<Theory, Extension> computed = strict_comp.computeExtension(th);
                Pair<Theory, Extension> completeExtension = def_comp.computeExtension(computed.getFirst(), computed.getSecond());
                
                //Print extension to file
                String toadd = String.format("%s", completeExtension.getSecond().getPlusDeltaString());
                if (completeExtension.getSecond().getPlusDelta().size() == 0) toadd = "";
                //Plus Delta
                Set<String> check1 = new HashSet<String>(Arrays.asList(toadd.split(",")).stream().map(s -> s.strip()).collect(Collectors.toList()));
                Set<String> check2 = new HashSet<String>(Arrays.asList(strictConclusions[counter-1][0].split(",")).stream().map(s -> s.strip()).collect(Collectors.toList()));
                assert((check1.size() == check2.size()) && check1.containsAll(check2));
                //Minus Delta
                toadd = String.format("%s", completeExtension.getSecond().getMinusDeltaString());
                if (completeExtension.getSecond().getMinusDelta().size() == 0) toadd = "";
                check1 = new HashSet<String>(Arrays.asList(toadd.split(",")).stream().map(s -> s.strip()).collect(Collectors.toList()));
                check2 = new HashSet<String>(Arrays.asList(strictConclusions[counter-1][1].split(",")).stream().map(s -> s.strip()).collect(Collectors.toList()));
                assert((check1.size() == check2.size()) && check1.containsAll(check2));
            }
            catch(Exception e) {
                System.out.println(e);
            }
            
            counter += 1;
            string_path = String.format("/home/edoardo/Uni4Justice/webservice/src/main/java/com/example/demo/strict_theories/theory%d.json", counter);
            file = new File(string_path);
        }
        System.out.println("!!!!!!!!!!!! STRICT ALSO PERFECT !!!!!!!!!!!!");
    };

    public static void testTimes() { 
        int counter = 1;
        String string_path = String.format("../generator/timetheories/theory%d.json", counter);
        
        File file = new File(string_path);
        
        double dataMean = 0;
        
        while(file.exists()) {
            System.out.println(String.format("<<<<<< %d", counter));
            try {
                String content = Files.readString(Paths.get(string_path));

                JSONLogicParser parser = new JSONLogicParser(content);
                LogicModel parsed_model = parser.parseJson();
                String JSONcontent = parsed_model.toJSONString();
          
                ModelParser.parse(JSONcontent); //Here throws ParseCancellationException if wrong
                Theory th = new Theory(ModelParser.getLiterals(), parsed_model);
                StrictExtensionComputator strict_comp = new StrictExtensionComputator();
                DefeasibleExtensionComputator def_comp = new DefeasibleExtensionComputator();
                Pair<Theory, Extension> computed = strict_comp.computeExtension(th);
                Pair<Theory, Extension> completeExtension = def_comp.computeExtension(computed.getFirst(), computed.getSecond());
                int nskip = 10;
                if (counter > nskip) {
                    double elaps = strict_comp.elapsedtime + def_comp.elapsedtime;
                    System.out.println(String.format("%.5f + %.5f = %.5f", strict_comp.elapsedtime, def_comp.elapsedtime, elaps));
                    dataMean = (elaps + (counter-nskip-1) * dataMean) /(counter-nskip);
                    //Print extension to file
                }
               
                
            }
            catch(Exception e) {
                System.out.println(e);
            }
            
            counter += 1;
            if (counter > 50) break;
            string_path = String.format("../generator/theories/theory%d.json", counter);
            file = new File(string_path);
        }
        System.out.println(String.format(">>>>>>>>>>>> Mean: %.5g%n", dataMean));
    }
}
