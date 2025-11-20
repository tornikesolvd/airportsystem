package com.solvd.airportsystem.pattern.abstractfactory;

import com.solvd.airportsystem.persistence.*;
import com.solvd.airportsystem.persistence.impl.*;

public class MyBatisRepositoryFactory implements RepositoryFactory {

    @Override
    public AircraftRepository createAircraftRepository() {
        return new AircraftMapperImpl();
    }

    @Override
    public AirlineRepository createAirlineRepository() {
        return new AirlineMapperImpl();
    }

    @Override
    public AirportRepository createAirportRepository() {
        return new AirportMapperImpl();
    }

    @Override
    public FlightRepository createFlightRepository() {
        return new FlightMapperImpl();
    }

    @Override
    public PassengerRepository createPassengerRepository() {
        return new PassengerMapperImpl();
    }

    @Override
    public PilotRepository createPilotRepository() {
        return new PilotMapperImpl();
    }

    @Override
    public TicketRepository createTicketRepository() {
        return new TicketMapperImpl();
    }
}

