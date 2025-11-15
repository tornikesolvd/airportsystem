package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Airport;
import com.solvd.airportsystem.persistence.repository.AirportRepository;
import com.solvd.airportsystem.persistence.repository.mybatis.AirportMapperImpl;
import com.solvd.airportsystem.service.AirportService;

import java.util.List;
import java.util.Optional;

public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;

    public AirportServiceImpl() {
        this.airportRepository = new AirportMapperImpl();
    }

    @Override
    public Airport create(Airport airport) {
        airport.setId(null);
        airportRepository.create(airport);
        return airport;
    }

    @Override
    public Airport update(Airport airport) {
        Airport existingAirport = airportRepository.findById(airport.getId())
                .orElseThrow(() -> new RuntimeException("Airport with id " + airport.getId() + " cannot be found"));
        existingAirport.setAirportName(airport.getAirportName());
        existingAirport.setLocation(airport.getLocation());
        existingAirport.setInternational(airport.isInternational());
        airportRepository.update(existingAirport);
        return existingAirport;
    }

    @Override
    public void deleteById(Long id) {
        airportRepository.deleteById(id);
    }

    @Override
    public Optional<Airport> findById(Long id) {
        return airportRepository.findById(id);
    }

    @Override
    public List<Airport> findAll() {
        return airportRepository.findAll();
    }
}

