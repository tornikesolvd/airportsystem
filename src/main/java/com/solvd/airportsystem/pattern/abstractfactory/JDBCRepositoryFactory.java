package com.solvd.airportsystem.pattern.abstractfactory;

import com.solvd.airportsystem.persistence.*;
import com.solvd.airportsystem.persistence.impl.*;

public class JDBCRepositoryFactory implements RepositoryFactory {

    @Override
    public AircraftRepository createAircraftRepository() {
        return new AircraftRepositoryImpl();
    }

    @Override
    public AirlineRepository createAirlineRepository() {
        return new AirlineRepositoryImpl();
    }

    @Override
    public AirportRepository createAirportRepository() {
        return new AirportRepositoryImpl();
    }

    @Override
    public FlightRepository createFlightRepository() {
        return new FlightRepositoryImpl();
    }

    @Override
    public PassengerRepository createPassengerRepository() {
        return new PassengerRepositoryImpl();
    }

    @Override
    public PilotRepository createPilotRepository() {
        return new PilotRepositoryImpl();
    }

    @Override
    public TicketRepository createTicketRepository() {
        return new TicketRepositoryImpl();
    }
}

