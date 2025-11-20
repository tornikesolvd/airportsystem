package com.solvd.airportsystem.pattern.proxy;

import com.solvd.airportsystem.persistence.FlightRepository;
import com.solvd.airportsystem.domain.Flight;
import com.solvd.airportsystem.pattern.abstractfactory.RepositoryFactory;

import java.util.List;
import java.util.Optional;

public class LazyFlightRepositoryProxy implements FlightRepository {
    private FlightRepository realRepository;
    private RepositoryFactory repositoryFactory;

    public LazyFlightRepositoryProxy(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    private FlightRepository getRealRepository() {
        if (realRepository == null) {
            realRepository = repositoryFactory.createFlightRepository();
        }
        return realRepository;
    }

    @Override
    public void create(Flight flight) {
        getRealRepository().create(flight);
    }

    @Override
    public void update(Flight flight) {
        getRealRepository().update(flight);
    }

    @Override
    public void deleteById(Long id) {
        getRealRepository().deleteById(id);
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return getRealRepository().findById(id);
    }

    @Override
    public List<Flight> findAll() {
        return getRealRepository().findAll();
    }

    @Override
    public List<Flight> findAllWithAllData() {
        return getRealRepository().findAllWithAllData();
    }
}

