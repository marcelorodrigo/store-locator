package com.marcelorodrigo.neareststore.service;

import com.marcelorodrigo.neareststore.entity.Store;
import com.marcelorodrigo.neareststore.repository.StoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

@Service
public class StoreServiceBean implements StoreService {

    private final StoreRepository storeRepository;

    public StoreServiceBean(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }


    @Transactional(propagation = NOT_SUPPORTED)
    public Page<Store> findNearest(final double latitude, final double longitude, final Pageable pageable) {
        return storeRepository.findNearest(latitude, longitude, pageable);
    }

    @Override
    @Transactional(propagation = NOT_SUPPORTED)
    public Optional<Store> findById(String uuid) {
        return storeRepository.findById(uuid);
    }
}
