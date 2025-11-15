package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Aircraft;
import com.solvd.airportsystem.domain.Pilot;
import com.solvd.airportsystem.persistence.repository.AircraftRepository;
import com.solvd.airportsystem.persistence.repository.PilotRepository;
import com.solvd.airportsystem.persistence.repository.mybatis.AircraftMapperImpl;
import com.solvd.airportsystem.persistence.repository.mybatis.PilotMapperImpl;
import com.solvd.airportsystem.service.AircraftService;

import java.util.List;
import java.util.Optional;

public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final PilotRepository pilotRepository;

    public AircraftServiceImpl() {
        this.aircraftRepository = new AircraftMapperImpl();
        this.pilotRepository = new PilotMapperImpl();
    }

    @Override
    public Aircraft create(Aircraft aircraft) {
        aircraft.setId(null);
        aircraftRepository.create(aircraft);

        if (aircraft.getPilots() != null && !aircraft.getPilots().isEmpty()) {
            for (Pilot pilot : aircraft.getPilots()) {
                if (pilot.getId() == null) {
                    pilot.setId(null);
                    pilotRepository.create(pilot);
                }
            }
        }

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

