package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Pilot;
import com.solvd.airportsystem.persistence.PilotRepository;
import com.solvd.airportsystem.service.PilotService;
import com.solvd.airportsystem.service.updater.EntityUpdater;
import com.solvd.airportsystem.service.validator.EntityValidator;

import java.util.List;
import java.util.Optional;

public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;

    public PilotServiceImpl(PilotRepository pilotRepository) {
        this.pilotRepository = pilotRepository;
    }

    @Override
    public Pilot create(Pilot pilot) {
        pilot.setId(null);
        pilotRepository.create(pilot);
        return pilot;
    }

    @Override
    public Pilot update(Pilot pilot) {
        Pilot existingPilot = validateAndGet(pilot.getId());
        EntityUpdater.updatePilot(existingPilot, pilot);
        pilotRepository.update(existingPilot);
        return existingPilot;
    }

    private Pilot validateAndGet(Long id) {
        Optional<Pilot> optional = pilotRepository.findById(id);
        return EntityValidator.validateEntityExists(optional.orElse(null), "Pilot", id);
    }

    @Override
    public void deleteById(Long id) {
        EntityValidator.validateId(id, "Pilot");
        pilotRepository.deleteById(id);
    }

    @Override
    public Optional<Pilot> findById(Long id) {
        EntityValidator.validateId(id, "Pilot");
        return pilotRepository.findById(id);
    }

    @Override
    public List<Pilot> findAll() {
        return pilotRepository.findAll();
    }
}

