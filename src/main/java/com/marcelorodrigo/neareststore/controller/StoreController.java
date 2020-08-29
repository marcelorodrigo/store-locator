package com.marcelorodrigo.neareststore.controller;

import com.marcelorodrigo.neareststore.dto.CoordinateDTO;
import com.marcelorodrigo.neareststore.dto.StoreDTO;
import com.marcelorodrigo.neareststore.dto.StoreMapper;
import com.marcelorodrigo.neareststore.service.StoreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<StoreDTO> getStore(@PathVariable("uuid") String uuid) {
        return ResponseEntity.of(storeService
                .findById(uuid)
                .map(StoreMapper::map)
        );
    }

    @GetMapping
    public Page<StoreDTO> getNearestStores(
            CoordinateDTO coordinate,
            @PageableDefault(size = 5) Pageable pageable
    ) {
        return storeService
                .findNearest(coordinate.getLatitude(), coordinate.getLongitude(), pageable)
                .map(StoreMapper::map);
    }
}
