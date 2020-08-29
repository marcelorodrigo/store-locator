package com.marcelorodrigo.neareststore.exception;

public class InvalidLongitudeException extends InvalidCoordinateException {
    public InvalidLongitudeException() {
        super("Invalid longitude");
    }
}
