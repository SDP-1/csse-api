package com.csse.api.exception;

public class ResidentNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResidentNotFoundException(String message) {
        super(message);
    }

    public ResidentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
