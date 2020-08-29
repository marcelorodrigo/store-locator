package com.marcelorodrigo.neareststore.domain;

import com.marcelorodrigo.neareststore.dto.CoordinateDTO;
import com.marcelorodrigo.neareststore.exception.InvalidLatitudeException;
import com.marcelorodrigo.neareststore.exception.InvalidLongitudeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinateDTOTest {

    private static final double VALID_LATITUDE = 5.2452;
    private static final double VALID_LONGITUDE = 94.3489;


    @ParameterizedTest
    @ValueSource(doubles = {-200, -90.00000001, 90.0000001, 200})
    void whenInvalidLatitudes_mustThrowException(double latitude) {
        assertThrows(InvalidLatitudeException.class, () -> new CoordinateDTO(latitude, VALID_LONGITUDE));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-181, -180.00000001, 180.0000001, 181})
    void whenInvalidLongitudes_mustThrowException(double longitude) {
        assertThrows(InvalidLongitudeException.class, () -> new CoordinateDTO(VALID_LATITUDE, longitude));
    }

    @Test
    void validCoordinates() throws Exception {
        CoordinateDTO coordinate = new CoordinateDTO(VALID_LATITUDE, VALID_LONGITUDE);
        assertEquals(VALID_LATITUDE, coordinate.getLatitude());
        assertEquals(VALID_LONGITUDE, coordinate.getLongitude());
    }


}