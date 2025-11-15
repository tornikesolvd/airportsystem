package com.solvd.airportsystem.persistence.repository;

import com.solvd.airportsystem.domain.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightRepository {

    void create(Flight flight);

    void update(Flight flight);

    void deleteById(Long id);

    Optional<Flight> findById(Long id);

    List<Flight> findAll();

    List<Flight> findAllWithAllData();
}

