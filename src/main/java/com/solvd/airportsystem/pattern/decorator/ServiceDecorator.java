package com.solvd.airportsystem.pattern.decorator;

import com.solvd.airportsystem.service.FlightService;
import com.solvd.airportsystem.domain.Flight;

import java.util.List;
import java.util.Optional;

public abstract class ServiceDecorator implements FlightService {
    protected FlightService flightService;

    public ServiceDecorator(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public Flight create(Flight flight) {
        return flightService.create(flight);
    }

    @Override
    public Flight update(Flight flight) {
        return flightService.update(flight);
    }

    @Override
    public void deleteById(Long id) {
        flightService.deleteById(id);
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return flightService.findById(id);
    }

    @Override
    public List<Flight> findAll() {
        return flightService.findAll();
    }

    @Override
    public List<Flight> findAllWithAllData() {
        return flightService.findAllWithAllData();
    }
}

