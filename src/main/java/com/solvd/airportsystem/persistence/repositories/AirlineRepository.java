package com.solvd.airportsystem.persistence.repositories;

import com.solvd.airportsystem.domain.Airline;

import java.util.List;
import java.util.Optional;

public interface AirlineRepository {

    void create(Airline airline);

    void update(Airline airline);

    void deleteById(Long id);

    Optional<Airline> findById(Long id);

    List<Airline> findAll();
}

