package com.solvd.airportsystem.service;

import com.solvd.airportsystem.domain.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightService {

    Flight create(Flight flight);

    Flight update(Flight flight);

    void deleteById(Long id);

    Optional<Flight> findById(Long id);

    List<Flight> findAll();

    List<Flight> findAllWithAllData();
}

