package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Airport;
import com.solvd.airportsystem.persistence.AirportRepository;
import com.solvd.airportsystem.service.AirportService;
import com.solvd.airportsystem.service.updater.EntityUpdater;
import com.solvd.airportsystem.service.validator.EntityValidator;

import java.util.List;
import java.util.Optional;

public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    public AirportServiceImpl(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @Override
    public Airport create(Airport airport) {
        airport.setId(null);
        airportRepository.create(airport);
        return airport;
    }

    @Override
    public Airport update(Airport airport) {
        Airport existingAirport = validateAndGet(airport.getId());
        EntityUpdater.updateAirport(existingAirport, airport);
        airportRepository.update(existingAirport);
        return existingAirport;
    }

    private Airport validateAndGet(Long id) {
        Optional<Airport> optional = airportRepository.findById(id);
        return EntityValidator.validateEntityExists(optional.orElse(null), "Airport", id);
    }

    @Override
    public void deleteById(Long id) {
        EntityValidator.validateId(id, "Airport");
        airportRepository.deleteById(id);
    }

    @Override
    public Optional<Airport> findById(Long id) {
        EntityValidator.validateId(id, "Airport");
        return airportRepository.findById(id);
    }

    @Override
    public List<Airport> findAll() {
        return airportRepository.findAll();
    }
}

