package com.marcelorodrigo.neareststore.exception;

public abstract class InvalidCoordinateException extends Exception {

    protected InvalidCoordinateException(String message) {
        super(message);
    }
}
