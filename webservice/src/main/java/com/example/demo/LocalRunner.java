package com.example.demo;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.demo.JSONParser.JSONLogicParser;
import com.example.demo.LogicModel.LogicModel;
import com.example.demo.LogicModel.Theory;
import com.example.demo.LogicModel.Extension.DefeasibleExtensionComputator;
import com.example.demo.LogicModel.Extension.Extension;
import com.example.demo.LogicModel.Extension.StrictExtensionComputator;
import com.example.demo.LogicModel.grammar.ModelParser;

import org.springframework.web.bind.EscapedErrors;

public class LocalRunner {
    public static void main(String[] args) {
        int counter = 1;
        String string_path = String.format("/home/edoardo/Uni4Justice/webservice/src/main/java/com/example/demo/theories/theory%d.json", counter);
        String out_path = "/home/edoardo/Uni4Justice/webservice/src/main/java/com/example/demo/theories/times.csv";

        File file = new File(string_path);
        
        List<String> data = new ArrayList<String>();
        data.add("ours");
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
                data.add(elaps.toString());
            }
            catch(Exception e) {
                System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~ERRRRRRRRRRRRRRRRORRRRRRRRRRRRR~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
            counter += 1;
            string_path = String.format("/home/edoardo/Uni4Justice/webservice/src/main/java/com/example/demo/theories/theory%d.json", counter);
            file = new File(string_path);
        }
        try {
            Files.write(Paths.get(out_path), data);
        } catch(Exception e) {

        }
        
    }
}
