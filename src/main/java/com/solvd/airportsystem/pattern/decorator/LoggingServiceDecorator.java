package com.solvd.airportsystem.pattern.decorator;

import com.solvd.airportsystem.domain.Flight;
import com.solvd.airportsystem.service.FlightService;

import java.util.List;
import java.util.Optional;

public class LoggingServiceDecorator extends ServiceDecorator {

    public LoggingServiceDecorator(FlightService flightService) {
        super(flightService);
    }

    @Override
    public Flight create(Flight flight) {
        System.out.println("Logging: Creating flight " + flight.getFlightNumber());
        Flight result = super.create(flight);
        System.out.println("Logging: Flight created with ID " + result.getId());
        return result;
    }

    @Override
    public Flight update(Flight flight) {
        System.out.println("Logging: Updating flight " + flight.getId());
        return super.update(flight);
    }

    @Override
    public void deleteById(Long id) {
        System.out.println("Logging: Deleting flight " + id);
        super.deleteById(id);
    }

    @Override
    public Optional<Flight> findById(Long id) {
        System.out.println("Logging: Finding flight by ID " + id);
        return super.findById(id);
    }

    @Override
    public List<Flight> findAll() {
        System.out.println("Logging: Finding all flights");
        return super.findAll();
    }
}

