package com.solvd.airportsystem;

import com.solvd.airportsystem.domain.Airport;
import com.solvd.airportsystem.domain.Airline;
import com.solvd.airportsystem.domain.Aircraft;
import com.solvd.airportsystem.domain.Flight;
import com.solvd.airportsystem.domain.Passenger;
import com.solvd.airportsystem.domain.Pilot;
import com.solvd.airportsystem.pattern.abstractfactory.MyBatisRepositoryFactory;
import com.solvd.airportsystem.pattern.abstractfactory.RepositoryFactory;
import com.solvd.airportsystem.service.*;
import com.solvd.airportsystem.service.factory.ServiceFactory;

import java.util.List;

public class DaoAPP {
    private static ServiceFactory serviceFactory;

    public static void main(String[] args) {
        RepositoryFactory repositoryFactory = new MyBatisRepositoryFactory();
        serviceFactory = new ServiceFactory(repositoryFactory);

        testAirportRepository();
        testAirlineRepository();
        testAircraftRepository();
        testPassengerRepository();
        testPilotRepository();
        testFlightRepository();

    }

    private static void testAirportRepository() {

        AirportService airportService = serviceFactory.createAirportService();

        List<Airport> airports = airportService.findAll();
        airports.forEach(airport -> 
            System.out.println("  ID: " + airport.getId() + 
                             ", Name: " + airport.getAirportName() + 
                             ", Location: " + airport.getLocation() + 
                             ", International: " + airport.isInternational())
        );

        airportService.findById(1L).ifPresentOrElse(
            airport -> System.out.println("  Found: " + airport.getAirportName() + " at " + airport.getLocation()),
            () -> System.out.println("  Airport not found")
        );

        Airport newAirport = new Airport();
        newAirport.setAirportName("New Airport");
        newAirport.setLocation("New City");
        newAirport.setInternational(true);
        airportService.create(newAirport);
        System.out.println("Created airport with ID: " + newAirport.getId());

        newAirport.setLocation("Bangkok City");
        airportService.update(newAirport);
        System.out.println("Updated airport location to: " + newAirport.getLocation());


        airportService.deleteById(newAirport.getId());
        System.out.println("Deleted airport with ID: " + newAirport.getId());
    }

    private static void testAirlineRepository() {

        AirlineService airlineService = serviceFactory.createAirlineService();

        List<Airline> airlines = airlineService.findAll();
        airlines.forEach(airline -> 
            System.out.println("  ID: " + airline.getId() + 
                             ", Name: " + airline.getAirlineName() + 
                             ", Code: " + airline.getAirlineCode())
        );

        airlineService.findById(1L).ifPresentOrElse(
            airline -> System.out.println("  Found: " + airline.getAirlineName() + " (" + airline.getAirlineCode() + ")"),
            () -> System.out.println("  Airline not found")
        );
    }

    private static void testAircraftRepository() {

        AircraftService aircraftService = serviceFactory.createAircraftService();

        List<Aircraft> aircrafts = aircraftService.findAll();
        aircrafts.forEach(aircraft -> 
            System.out.println("  ID: " + aircraft.getId() + 
                             ", Type: " + aircraft.getAircraftType() + 
                             ", Capacity: " + aircraft.getCapacity())
        );

        aircraftService.findById(1L).ifPresentOrElse(
            aircraft -> System.out.println("  Found: " + aircraft.getAircraftType() + " with capacity " + aircraft.getCapacity()),
            () -> System.out.println("  Aircraft not found")
        );
    }

    private static void testPassengerRepository() {

        PassengerService passengerService = serviceFactory.createPassengerService();

        List<Passenger> passengers = passengerService.findAll();
        passengers.forEach(passenger -> 
            System.out.println("  ID: " + passenger.getId() + 
                             ", Name: " + passenger.getFullName() + 
                             ", Passport: " + passenger.getPassportNumber() + 
                             ", Birth Date: " + passenger.getBirthDate())
        );

        passengerService.findById(1L).ifPresentOrElse(
            passenger -> System.out.println("  Found: " + passenger.getFullName() + " (Passport: " + passenger.getPassportNumber() + ")"),
            () -> System.out.println("  Passenger not found")
        );
    }

    private static void testPilotRepository() {

        PilotService pilotService = serviceFactory.createPilotService();

        List<Pilot> pilots = pilotService.findAll();
        pilots.forEach(pilot -> 
            System.out.println("  ID: " + pilot.getId() + 
                             ", Name: " + pilot.getPilotName() + 
                             ", License: " + pilot.getLicenseNumber() + 
                             ", Flight Hours: " + pilot.getFlightHours() + 
                             ", Certification: " + pilot.getCertification())
        );

        System.out.println("pilot by Id 1");
        pilotService.findById(1L).ifPresentOrElse(
            pilot -> System.out.println("  Found: " + pilot.getPilotName() + " (" + pilot.getCertification() + ")"),
            () -> System.out.println("  Pilot not found")
        );
    }

    private static void testFlightRepository() {

        FlightService flightService = serviceFactory.createFlightService();

        List<Flight> flights = flightService.findAll();
        flights.forEach(flight -> 
            System.out.println("  ID: " + flight.getId() + 
                             ", Number: " + flight.getFlightNumber() + 
                             ", Destination: " + flight.getDestination() + 
                             ", Delayed: " + flight.isDelayed())
        );


        flightService.findById(1L).ifPresentOrElse(
            flight -> System.out.println("  Found: " + flight.getFlightNumber() + " to " + flight.getDestination()),
            () -> System.out.println("  Flight not found")
        );

        List<Flight> flightsWithDetails = flightService.findAllWithAllData();
        flightsWithDetails.forEach(flight -> {
            System.out.println("  Flight: " + flight.getFlightNumber() + 
                             " to " + flight.getDestination());
            if (flight.getAircraft() != null) {
                System.out.println("    Aircraft: " + flight.getAircraft().getAircraftType() + 
                                 " (Capacity: " + flight.getAircraft().getCapacity() + ")");
            }
            if (flight.getGate() != null) {
                System.out.println("    Gate: " + flight.getGate().getGateNumber() + 
                                 " (" + flight.getGate().getGateType() + ")");
            }
        });
    }
}

