package com.example.demo.JSONParser;

import java.io.IOException;

public class JSONWrongFormatException extends IOException {

    public JSONWrongFormatException(String message) {
        super(message);
    }

    public JSONWrongFormatException(String message, Throwable cause) {
		super(message, cause);
	}
}
