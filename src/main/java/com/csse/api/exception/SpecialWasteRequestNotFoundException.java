package com.csse.api.exception;

public class SpecialWasteRequestNotFoundException extends RuntimeException {
    public SpecialWasteRequestNotFoundException(long id) {
        super("Special Waste Request not found with id: " + id);
    }
}
