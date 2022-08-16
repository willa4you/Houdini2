package com.example.demo.JSONParser;

import com.example.demo.LogicModel.LogicModel;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class JSONLogicParser {
    JsonFactory factory = JsonFactory.builder().build();
    private String rawJson;

    public JSONLogicParser(String rawJson) {
        this.rawJson = rawJson;
    }

    public LogicModel parseJson() throws JSONWrongFormatException {
        //String s = rawJson;
        JSONLogicModel model = new JSONLogicModel();
        ObjectMapper mapper = new ObjectMapper();

        try {
            model = mapper.readValue(rawJson, JSONLogicModel.class);
        } catch(Exception e) {
            throw new JSONWrongFormatException("File content is not compatible with a valid logic model. See the Help page.");
        }
        return model.getLogicModel();
    }
    
    @ExceptionHandler(EmptyFileException.class)
    public String handleEmptyFileException() {
        return "a";
    }
    
}
