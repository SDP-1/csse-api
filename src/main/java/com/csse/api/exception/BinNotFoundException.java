package com.csse.api.exception;

public class BinNotFoundException extends RuntimeException {
    public BinNotFoundException(String message) {
        super(message);
    }
}
