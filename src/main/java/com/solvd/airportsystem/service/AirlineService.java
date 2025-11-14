package com.solvd.airportsystem.service;

import com.solvd.airportsystem.domain.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineService {

    Airline create(Airline airline);
    Airline update(Airline airline);
    void deleteById(Long id);
    Optional<Airline> findById(Long id);
    List<Airline> findAll();
}

