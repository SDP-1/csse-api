package com.csse.api.exception;

public class GarbageCollectorNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public GarbageCollectorNotFoundException(Long id) {
        super("Garbage Collector not found with id: " + id);
    }

    public GarbageCollectorNotFoundException(String message) {
        super(message);
    }
}
