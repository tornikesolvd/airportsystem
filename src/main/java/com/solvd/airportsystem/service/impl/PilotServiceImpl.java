package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Pilot;
import com.solvd.airportsystem.persistence.repository.PilotRepository;
import com.solvd.airportsystem.persistence.repository.mybatis.PilotMapperImpl;
import com.solvd.airportsystem.service.PilotService;

import java.util.List;
import java.util.Optional;

public class PilotServiceImpl implements PilotService {

    private final PilotRepository pilotRepository;

    public PilotServiceImpl() {
        this.pilotRepository = new PilotMapperImpl();
    }

    @Override
    public Pilot create(Pilot pilot) {
        pilot.setId(null);
        pilotRepository.create(pilot);
        return pilot;
    }

    @Override
    public Pilot update(Pilot pilot) {
        Pilot existingPilot = pilotRepository.findById(pilot.getId())
                .orElseThrow(() -> new RuntimeException("Pilot with id " + pilot.getId() + " cannot be found"));
        existingPilot.setPilotName(pilot.getPilotName());
        existingPilot.setLicenseNumber(pilot.getLicenseNumber());
        existingPilot.setFlightHours(pilot.getFlightHours());
        existingPilot.setCertification(pilot.getCertification());
        pilotRepository.update(existingPilot);
        return existingPilot;
    }

    @Override
    public void deleteById(Long id) {
        pilotRepository.deleteById(id);
    }

    @Override
    public Optional<Pilot> findById(Long id) {
        return pilotRepository.findById(id);
    }

    @Override
    public List<Pilot> findAll() {
        return pilotRepository.findAll();
    }
}

