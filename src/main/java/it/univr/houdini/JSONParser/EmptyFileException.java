package it.univr.houdini.JSONParser;

public class EmptyFileException extends RuntimeException {
    public EmptyFileException(String message) {
        super(message);
    }

    public EmptyFileException(String message, Throwable cause) {
		super(message, cause);
	}
}
