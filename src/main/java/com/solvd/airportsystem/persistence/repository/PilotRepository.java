package com.solvd.airportsystem.persistence.repository;

import com.solvd.airportsystem.domain.Pilot;

import java.util.List;
import java.util.Optional;

public interface PilotRepository {

    void create(Pilot pilot);

    void update(Pilot pilot);

    void deleteById(Long id);

    Optional<Pilot> findById(Long id);

    List<Pilot> findAll();
}

