package com.marcelorodrigo.neareststore.dto;

import com.marcelorodrigo.neareststore.entity.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StoreMapperTest {

    private final Store store = new Store();

    @BeforeEach
    void setup() {
        store.setUuid("6749847-a");
        store.setAddressName("Van Driel 5");
        store.setCity("Best");
        store.setStreet("Van Driel");
        store.setStreet2("5");
        store.setStreet3("X");
        store.setPostalCode("1234 BC");
        store.setComplexNumber(null);
        store.setLatitude(5.45);
        store.setLongitude(51.27807);
        store.setTodayOpen("08:00");
        store.setTodayClose("22:00");
        store.setLocationType("MEGA_STORE");
        store.setSapStoreID(1897234);
        store.setCollectionPoint(true);
        store.setShowWarningMessage(true);
    }

    @Test
    void of() {
        final StoreDTO dto = StoreMapper.map(store);
        validateProperties(dto);
    }

    @Test
    void testOf() {
        final Optional<StoreDTO> dtoOptional = StoreMapper.map(Optional.of(store));
        assertTrue(dtoOptional.isPresent());
        validateProperties(dtoOptional.get());
    }

    private void validateProperties(StoreDTO dto) {
        assertEquals(store.getUuid(), dto.getUuid());
        assertEquals(store.getAddressName(), dto.getAddressName());
        assertEquals(store.getCity(), dto.getCity());
        assertEquals(store.getStreet(), dto.getStreet());
        assertEquals(store.getStreet2(), dto.getStreet2());
        assertEquals(store.getStreet3(), dto.getStreet3());
        assertEquals(store.getPostalCode(), dto.getPostalCode());
        assertEquals(store.getComplexNumber(), dto.getComplexNumber());
        assertEquals(store.isShowWarningMessage(), dto.isShowWarningMessage());
        assertEquals(store.getLatitude(), dto.getLatitude());
        assertEquals(store.getLongitude(), dto.getLongitude());
        assertEquals(store.getTodayOpen(), dto.getTodayOpen());
        assertEquals(store.getTodayClose(), dto.getTodayClose());
        assertEquals(store.isCollectionPoint(), dto.isCollectionPoint());
    }
}