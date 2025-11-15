package com.solvd.airportsystem.service;

import com.solvd.airportsystem.domain.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerService {

    Passenger create(Passenger passenger);

    Passenger update(Passenger passenger);

    void deleteById(Long id);

    Optional<Passenger> findById(Long id);

    List<Passenger> findAll();
}

