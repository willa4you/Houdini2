package com.example.demo.JSONParser;

import java.io.IOException;

public class WrongFormatException extends IOException {

    public WrongFormatException(String message) {
        super(message);
    }

    public WrongFormatException(String message, Throwable cause) {
		super(message, cause);
	}
}
