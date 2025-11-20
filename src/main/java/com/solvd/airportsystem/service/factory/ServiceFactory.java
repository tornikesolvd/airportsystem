package com.solvd.airportsystem.service.factory;

import com.solvd.airportsystem.pattern.abstractfactory.RepositoryFactory;
import com.solvd.airportsystem.service.*;

public class ServiceFactory {
    private final RepositoryFactory repositoryFactory;
    private PilotService pilotService;
    private TicketService ticketService;
    private AircraftService aircraftService;
    private FlightService flightService;

    public ServiceFactory(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    public AirportService createAirportService() {
        return new com.solvd.airportsystem.service.impl.AirportServiceImpl(
                repositoryFactory.createAirportRepository()
        );
    }

    public AirlineService createAirlineService() {
        return new com.solvd.airportsystem.service.impl.AirlineServiceImpl(
                repositoryFactory.createAirlineRepository(),
                getFlightService()
        );
    }

    public AircraftService createAircraftService() {
        if (aircraftService == null) {
            aircraftService = new com.solvd.airportsystem.service.impl.AircraftServiceImpl(
                    repositoryFactory.createAircraftRepository(),
                    getPilotService()
            );
        }
        return aircraftService;
    }

    public PassengerService createPassengerService() {
        return new com.solvd.airportsystem.service.impl.PassengerServiceImpl(
                repositoryFactory.createPassengerRepository(),
                getTicketService()
        );
    }

    public PilotService createPilotService() {
        return getPilotService();
    }

    private PilotService getPilotService() {
        if (pilotService == null) {
            pilotService = new com.solvd.airportsystem.service.impl.PilotServiceImpl(
                    repositoryFactory.createPilotRepository()
            );
        }
        return pilotService;
    }

    public FlightService createFlightService() {
        return getFlightService();
    }

    private FlightService getFlightService() {
        if (flightService == null) {
            flightService = new com.solvd.airportsystem.service.impl.FlightServiceImpl(
                    repositoryFactory.createFlightRepository(),
                    createAircraftService()
            );
        }
        return flightService;
    }

    public TicketService createTicketService() {
        return getTicketService();
    }

    private TicketService getTicketService() {
        if (ticketService == null) {
            ticketService = new com.solvd.airportsystem.service.impl.TicketServiceImpl(
                    repositoryFactory.createTicketRepository()
            );
        }
        return ticketService;
    }
}

