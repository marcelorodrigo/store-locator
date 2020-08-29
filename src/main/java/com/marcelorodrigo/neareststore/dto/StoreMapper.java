package com.marcelorodrigo.neareststore.dto;

import com.marcelorodrigo.neareststore.entity.Store;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class StoreMapper {

    private StoreMapper() {
        // You must not be instantiating this class
    }

    public static Optional<StoreDTO> map(Optional<Store> store) {
        return store.map(StoreMapper::map);
    }

    public static StoreDTO map(Store store) {
        return new ModelMapper().map(store, StoreDTO.class);
    }
}
