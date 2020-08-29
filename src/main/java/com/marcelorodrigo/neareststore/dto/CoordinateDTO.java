package com.marcelorodrigo.neareststore.dto;

import com.marcelorodrigo.neareststore.exception.InvalidCoordinateException;
import com.marcelorodrigo.neareststore.exception.InvalidLatitudeException;
import com.marcelorodrigo.neareststore.exception.InvalidLongitudeException;
import lombok.Getter;

public class CoordinateDTO {

    @Getter
    private final double latitude;
    @Getter
    private final double longitude;
    private static final double LATITUDE_MIN = -90;
    private static final double LATITUDE_MAX = 90;
    private static final double LONGITUDE_MIN = -180;
    private static final double LONGITUDE_MAX = 180;

    public CoordinateDTO(double latitude, double longitude) throws InvalidCoordinateException {
        validate(latitude, longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private void validate(double latitude, double longitude) throws InvalidCoordinateException {
        if (latitude < LATITUDE_MIN || latitude > LATITUDE_MAX) {
            throw new InvalidLatitudeException();
        }
        if (longitude < LONGITUDE_MIN || longitude > LONGITUDE_MAX) {
            throw new InvalidLongitudeException();
        }
    }
}
