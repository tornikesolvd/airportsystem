package com.solvd.airportsystem.service;

import com.solvd.airportsystem.domain.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportService {

    Airport create(Airport airport);

    Airport update(Airport airport);

    void deleteById(Long id);

    Optional<Airport> findById(Long id);

    List<Airport> findAll();
}

