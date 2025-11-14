package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Flight;
import com.solvd.airportsystem.persistence.repository.FlightRepository;
import com.solvd.airportsystem.persistence.repository.impl.FlightRepositoryImpl;
import com.solvd.airportsystem.service.FlightService;

import java.util.List;
import java.util.Optional;

public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl() {
        this.flightRepository = new FlightRepositoryImpl();
    }

    @Override
    public Flight create(Flight flight) {
        flight.setId(null);
        flightRepository.create(flight);
        return flight;
    }

    @Override
    public Flight update(Flight flight) {
        Flight existingFlight = flightRepository.findById(flight.getId())
                .orElseThrow(() -> new RuntimeException("Flight with id " + flight.getId() + " cannot be found"));
        existingFlight.setFlightNumber(flight.getFlightNumber());
        existingFlight.setDestination(flight.getDestination());
        existingFlight.setDepartureDate(flight.getDepartureDate());
        existingFlight.setDepartureTime(flight.getDepartureTime());
        existingFlight.setDelayed(flight.isDelayed());
        flightRepository.update(existingFlight);
        return existingFlight;
    }

    @Override
    public void deleteById(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return flightRepository.findById(id);
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> findAllWithDetails() {
        return flightRepository.findAllWithDetails();
    }
}

