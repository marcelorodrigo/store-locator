package com.marcelorodrigo.neareststore.controller;

import com.marcelorodrigo.neareststore.dto.CoordinateDTO;
import com.marcelorodrigo.neareststore.dto.StoreDTO;
import com.marcelorodrigo.neareststore.dto.StoreMapper;
import com.marcelorodrigo.neareststore.service.StoreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/store", produces = StoreControllerV1.V1_JSON)
public class StoreControllerV1 {

    static final String V1_JSON = "application/vnd.neareststore.v1+json";
    private final StoreService storeService;

    public StoreControllerV1(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping(value = "/{uuid}")
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
