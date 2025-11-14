package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Aircraft;
import com.solvd.airportsystem.persistence.repository.AircraftRepository;
import com.solvd.airportsystem.persistence.repository.impl.AircraftRepositoryImpl;
import com.solvd.airportsystem.service.AircraftService;

import java.util.List;
import java.util.Optional;

public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;

    public AircraftServiceImpl() {
        this.aircraftRepository = new AircraftRepositoryImpl();
    }

    @Override
    public Aircraft create(Aircraft aircraft) {
        aircraft.setId(null);
        aircraftRepository.create(aircraft);
        return aircraft;
    }

    @Override
    public Aircraft update(Aircraft aircraft) {
        Aircraft existingAircraft = aircraftRepository.findById(aircraft.getId())
                .orElseThrow(() -> new RuntimeException("Aircraft with id " + aircraft.getId() + " cannot be found"));
        existingAircraft.setAircraftType(aircraft.getAircraftType());
        existingAircraft.setCapacity(aircraft.getCapacity());
        aircraftRepository.update(existingAircraft);
        return existingAircraft;
    }

    @Override
    public void deleteById(Long id) {
        aircraftRepository.deleteById(id);
    }

    @Override
    public Optional<Aircraft> findById(Long id) {
        return aircraftRepository.findById(id);
    }

    @Override
    public List<Aircraft> findAll() {
        return aircraftRepository.findAll();
    }
}

