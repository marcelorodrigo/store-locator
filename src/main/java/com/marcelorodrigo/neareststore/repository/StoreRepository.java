package com.marcelorodrigo.neareststore.repository;

import com.marcelorodrigo.neareststore.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;


@Repository
public interface StoreRepository extends JpaRepository<Store, String> {

    /**
     * Haversine Formula
     * <p>
     * The haversine formula computes the great-circle distance between two points on a sphere based on their longitudes and latitudes
     * For that we need to use earth radius, as the value could range from 6378km in the Equator to 6357km at the pole.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Haversine_formula">Haversine Formula</a>
     * @see <a href="https://en.wikipedia.org/wiki/Earth_radius">Earth Radius</a>
     */
    int EARTH_RADIUS_IN_KM = 6371;
    String DISTANCE = "(" + EARTH_RADIUS_IN_KM + " * acos(cos(radians(:latitude)) " +
            " * cos(radians(s.latitude)) * cos(radians(s.longitude) - radians(:longitude)) " +
            " + sin(radians(:latitude)) * sin(radians(s.latitude))))";

    @Transactional(propagation = NOT_SUPPORTED)
    @Query("FROM Store s ORDER BY " + DISTANCE + " ASC")
    Page<Store> findNearest(
            @Param("latitude") final double latitude,
            @Param("longitude") final double longitude,
            Pageable pageable);
}