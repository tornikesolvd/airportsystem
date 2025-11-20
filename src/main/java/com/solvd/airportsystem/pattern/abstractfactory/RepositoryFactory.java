package com.solvd.airportsystem.pattern.abstractfactory;

import com.solvd.airportsystem.persistence.*;

public interface RepositoryFactory {
    AircraftRepository createAircraftRepository();
    AirlineRepository createAirlineRepository();
    AirportRepository createAirportRepository();
    FlightRepository createFlightRepository();
    PassengerRepository createPassengerRepository();
    PilotRepository createPilotRepository();
    TicketRepository createTicketRepository();
}

