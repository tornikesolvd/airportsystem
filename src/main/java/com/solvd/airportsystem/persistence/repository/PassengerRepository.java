package com.solvd.airportsystem.persistence.repository;

import com.solvd.airportsystem.domain.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerRepository {

    void create(Passenger passenger);
    void update(Passenger passenger);
    void deleteById(Long id);
    Optional<Passenger> findById(Long id);
    List<Passenger> findAll();
}

