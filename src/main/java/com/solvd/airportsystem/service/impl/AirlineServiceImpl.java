package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Airline;
import com.solvd.airportsystem.persistence.repository.AirlineRepository;
import com.solvd.airportsystem.persistence.repository.impl.AirlineRepositoryImpl;
import com.solvd.airportsystem.service.AirlineService;

import java.util.List;
import java.util.Optional;

public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    public AirlineServiceImpl() {
        this.airlineRepository = new AirlineRepositoryImpl();
    }

    @Override
    public Airline create(Airline airline) {
        airline.setId(null);
        airlineRepository.create(airline);
        return airline;
    }

    @Override
    public Airline update(Airline airline) {
        Airline existingAirline = airlineRepository.findById(airline.getId())
                .orElseThrow(() -> new RuntimeException("Airline with id " + airline.getId() + " cannot be found"));
        existingAirline.setAirlineName(airline.getAirlineName());
        existingAirline.setAirlineCode(airline.getAirlineCode());
        airlineRepository.update(existingAirline);
        return existingAirline;
    }

    @Override
    public void deleteById(Long id) {
        airlineRepository.deleteById(id);
    }

    @Override
    public Optional<Airline> findById(Long id) {
        return airlineRepository.findById(id);
    }

    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }
}

