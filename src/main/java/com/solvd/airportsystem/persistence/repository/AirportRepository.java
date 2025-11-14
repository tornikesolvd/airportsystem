package com.solvd.airportsystem.persistence.repository;

import com.solvd.airportsystem.domain.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportRepository {

    void create(Airport airport);
    void update(Airport airport);
    void deleteById(Long id);
    Optional<Airport> findById(Long id);
    List<Airport> findAll();
}

