package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Aircraft;
import com.solvd.airportsystem.domain.Flight;
import com.solvd.airportsystem.persistence.FlightRepository;
import com.solvd.airportsystem.service.AircraftService;
import com.solvd.airportsystem.service.FlightService;
import com.solvd.airportsystem.service.updater.EntityUpdater;
import com.solvd.airportsystem.service.validator.EntityValidator;

import java.util.List;
import java.util.Optional;

public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AircraftService aircraftService;

    public FlightServiceImpl(FlightRepository flightRepository, AircraftService aircraftService) {
        this.flightRepository = flightRepository;
        this.aircraftService = aircraftService;
    }

    @Override
    public Flight create(Flight flight) {
        flight.setId(null);

        if (flight.getAircraft() != null && flight.getAircraft().getId() == null) {
            Aircraft aircraft = flight.getAircraft();
            aircraft.setId(null);
            aircraftService.create(aircraft);
        }

        flightRepository.create(flight);
        return flight;
    }

    @Override
    public Flight update(Flight flight) {
        Flight existingFlight = validateAndGet(flight.getId());
        EntityUpdater.updateFlight(existingFlight, flight);
        flightRepository.update(existingFlight);
        return existingFlight;
    }

    private Flight validateAndGet(Long id) {
        Optional<Flight> optional = flightRepository.findById(id);
        return EntityValidator.validateEntityExists(optional.orElse(null), "Flight", id);
    }

    @Override
    public void deleteById(Long id) {
        EntityValidator.validateId(id, "Flight");
        flightRepository.deleteById(id);
    }

    @Override
    public Optional<Flight> findById(Long id) {
        EntityValidator.validateId(id, "Flight");
        return flightRepository.findById(id);
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> findAllWithAllData() {
        return flightRepository.findAllWithAllData();
    }
}

