package com.csse.api.exception;

public class TrackingDeviceException extends RuntimeException {
    public TrackingDeviceException(String message) {
        super(message);
    }

    public TrackingDeviceException(String message, Throwable cause) {
        super(message, cause);
    }
}
