package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Aircraft;
import com.solvd.airportsystem.domain.Pilot;
import com.solvd.airportsystem.persistence.AircraftRepository;
import com.solvd.airportsystem.service.AircraftService;
import com.solvd.airportsystem.service.PilotService;
import com.solvd.airportsystem.service.updater.EntityUpdater;
import com.solvd.airportsystem.service.validator.EntityValidator;

import java.util.List;
import java.util.Optional;

public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;
    private final PilotService pilotService;

    public AircraftServiceImpl(AircraftRepository aircraftRepository, PilotService pilotService) {
        this.aircraftRepository = aircraftRepository;
        this.pilotService = pilotService;
    }

    @Override
    public Aircraft create(Aircraft aircraft) {
        aircraft.setId(null);
        aircraftRepository.create(aircraft);

        if (aircraft.getPilots() != null && !aircraft.getPilots().isEmpty()) {
            for (Pilot pilot : aircraft.getPilots()) {
                if (pilot.getId() == null) {
                    pilot.setId(null);
                    pilotService.create(pilot);
                }
            }
        }

        return aircraft;
    }

    @Override
    public Aircraft update(Aircraft aircraft) {
        Aircraft existingAircraft = validateAndGet(aircraft.getId());
        EntityUpdater.updateAircraft(existingAircraft, aircraft);
        aircraftRepository.update(existingAircraft);
        return existingAircraft;
    }

    private Aircraft validateAndGet(Long id) {
        Optional<Aircraft> optional = aircraftRepository.findById(id);
        return EntityValidator.validateEntityExists(optional.orElse(null), "Aircraft", id);
    }

    @Override
    public void deleteById(Long id) {
        EntityValidator.validateId(id, "Aircraft");
        aircraftRepository.deleteById(id);
    }

    @Override
    public Optional<Aircraft> findById(Long id) {
        EntityValidator.validateId(id, "Aircraft");
        return aircraftRepository.findById(id);
    }

    @Override
    public List<Aircraft> findAll() {
        return aircraftRepository.findAll();
    }
}

