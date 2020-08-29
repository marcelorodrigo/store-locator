package com.marcelorodrigo.neareststore.exception;

public class InvalidLatitudeException extends InvalidCoordinateException {
    public InvalidLatitudeException() {
        super("Invalid latitude");
    }
}
