package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Airline;
import com.solvd.airportsystem.domain.Flight;
import com.solvd.airportsystem.persistence.AirlineRepository;
import com.solvd.airportsystem.service.AirlineService;
import com.solvd.airportsystem.service.FlightService;
import com.solvd.airportsystem.service.updater.EntityUpdater;
import com.solvd.airportsystem.service.validator.EntityValidator;

import java.util.List;
import java.util.Optional;

public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;
    private final FlightService flightService;

    public AirlineServiceImpl(AirlineRepository airlineRepository, FlightService flightService) {
        this.airlineRepository = airlineRepository;
        this.flightService = flightService;
    }

    @Override
    public Airline create(Airline airline) {
        airline.setId(null);
        airlineRepository.create(airline);

        if (airline.getFlights() != null && !airline.getFlights().isEmpty()) {
            for (Flight flight : airline.getFlights()) {
                if (flight.getId() == null) {
                    flight.setId(null);
                    flightService.create(flight);
                }
            }
        }

        return airline;
    }

    @Override
    public Airline update(Airline airline) {
        Airline existingAirline = validateAndGet(airline.getId());
        EntityUpdater.updateAirline(existingAirline, airline);
        airlineRepository.update(existingAirline);
        return existingAirline;
    }

    private Airline validateAndGet(Long id) {
        Optional<Airline> optional = airlineRepository.findById(id);
        return EntityValidator.validateEntityExists(optional.orElse(null), "Airline", id);
    }

    @Override
    public void deleteById(Long id) {
        EntityValidator.validateId(id, "Airline");
        airlineRepository.deleteById(id);
    }

    @Override
    public Optional<Airline> findById(Long id) {
        EntityValidator.validateId(id, "Airline");
        return airlineRepository.findById(id);
    }

    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }
}

