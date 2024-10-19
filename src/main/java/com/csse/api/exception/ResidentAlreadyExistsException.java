package com.csse.api.exception;

public class ResidentAlreadyExistsException extends RuntimeException {
    public ResidentAlreadyExistsException(String message) {
        super(message);
    }
}
