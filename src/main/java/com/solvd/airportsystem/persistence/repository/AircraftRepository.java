package com.solvd.airportsystem.persistence.repository;

import com.solvd.airportsystem.domain.Aircraft;

import java.util.List;
import java.util.Optional;

public interface AircraftRepository {

    void create(Aircraft aircraft);
    void update(Aircraft aircraft);
    void deleteById(Long id);
    Optional<Aircraft> findById(Long id);
    List<Aircraft> findAll();
}

