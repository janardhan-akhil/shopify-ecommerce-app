package com.example.exception;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super("Resource not found: " + message, cause);
    }

    public ResourceNotFoundException() {
        super("Resource not found on server");
    }
}
