package com.solvd.airportsystem.service;

import com.solvd.airportsystem.domain.Aircraft;

import java.util.List;
import java.util.Optional;

public interface AircraftService {

    Aircraft create(Aircraft aircraft);
    Aircraft update(Aircraft aircraft);
    void deleteById(Long id);
    Optional<Aircraft> findById(Long id);
    List<Aircraft> findAll();
}

