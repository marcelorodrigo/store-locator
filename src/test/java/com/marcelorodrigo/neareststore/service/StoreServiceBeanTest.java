package com.marcelorodrigo.neareststore.service;

import com.marcelorodrigo.neareststore.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StoreServiceBeanTest {

    @Spy
    public StoreRepository repository;

    @InjectMocks
    public StoreServiceBean storeService;

    @Test
    void findNearest() {
        double latitude = 5.90654;
        double longitude = 18.578744;
        Pageable pageable = Pageable.unpaged();

        storeService.findNearest(latitude, longitude, pageable);
        verify(repository).findNearest(latitude, longitude, pageable);
    }
}