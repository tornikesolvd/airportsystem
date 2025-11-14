package com.solvd.airportsystem.service;

import com.solvd.airportsystem.domain.Pilot;

import java.util.List;
import java.util.Optional;

public interface PilotService {

    Pilot create(Pilot pilot);
    Pilot update(Pilot pilot);
    void deleteById(Long id);
    Optional<Pilot> findById(Long id);
    List<Pilot> findAll();
}

