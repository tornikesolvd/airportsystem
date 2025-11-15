package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Aircraft;
import com.solvd.airportsystem.domain.Flight;
import com.solvd.airportsystem.persistence.repository.AircraftRepository;
import com.solvd.airportsystem.persistence.repository.AirlineRepository;
import com.solvd.airportsystem.persistence.repository.FlightRepository;
import com.solvd.airportsystem.persistence.repository.mybatis.AircraftMapperImpl;
import com.solvd.airportsystem.persistence.repository.mybatis.AirlineMapperImpl;
import com.solvd.airportsystem.persistence.repository.mybatis.FlightMapperImpl;
import com.solvd.airportsystem.service.FlightService;

import java.util.List;
import java.util.Optional;

public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final AircraftRepository aircraftRepository;
    private final AirlineRepository airlineRepository;

    public FlightServiceImpl() {
        this.flightRepository = new FlightMapperImpl();
        this.aircraftRepository = new AircraftMapperImpl();
        this.airlineRepository = new AirlineMapperImpl();
    }

    @Override
    public Flight create(Flight flight) {
        flight.setId(null);

        if (flight.getAircraft() != null && flight.getAircraft().getId() == null) {
            Aircraft aircraft = flight.getAircraft();
            aircraft.setId(null);
            aircraftRepository.create(aircraft);
        }

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
    public List<Flight> findAllWithAllData() {
        return flightRepository.findAllWithAllData();
    }
}

