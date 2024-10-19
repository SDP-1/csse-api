package com.csse.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BinTypeNotFoundException extends RuntimeException {
    public BinTypeNotFoundException(String message) {
        super(message);
    }
}
