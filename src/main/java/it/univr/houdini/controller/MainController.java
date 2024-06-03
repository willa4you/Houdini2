package it.univr.houdini.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import it.univr.houdini.model.ModelExtensionComputator;

@Controller
public class MainController {

    @GetMapping("/")
    public String startHoudini() {
        return "home";
    }

    @PostMapping("/posting")
    @ResponseBody
    public String houdiniRESTbackend(@RequestBody String JSONTheory) {
        
        try {
            ModelExtensionComputator theoryExtension = new ModelExtensionComputator(JSONTheory);
            //return "Somebody Once Told me the world is gonna roll me";
            return theoryExtension.toJSONString();
        } catch (IOException e) {
            System.out.println(e);
            return "Object Mapper is unable to recognize a JSON format. Please check your syntax. Error: " + e.toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return "Invalid JSON format, please check the specs. Error: " + e.toString();
        }
    }
}
