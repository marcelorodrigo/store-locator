package com.marcelorodrigo.neareststore.service;

import com.marcelorodrigo.neareststore.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StoreService {

    Page<Store> findNearest(double latitude, double longitude, Pageable pageable);

    Optional<Store> findById(String uuid);
}