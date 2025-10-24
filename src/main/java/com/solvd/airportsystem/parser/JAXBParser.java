package com.solvd.airportsystem.parser;

import com.solvd.airportsystem.airport.Airport;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class JAXBParser {

    public static void main(String[] args) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Airport.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File xmlFile = new File("src/main/resources/airportsystem.xml");
            Airport airport = (Airport) jaxbUnmarshaller.unmarshal(xmlFile);

            if (airport.getTerminals() != null) {
                System.out.println("Terminals: " + airport.getTerminals().size());
                airport.getTerminals().forEach(terminal ->
                        System.out.println("  Terminal: " + terminal.getTerminalName())
                );
            }

            if (airport.getStaffList() != null) {
                System.out.println("Staff: " + airport.getStaffList().size());
                airport.getStaffList().forEach(staff ->
                        System.out.println("  Staff: " + staff.getStaffName() + " - " + staff.getRole())
                );
            }

            if (airport.getAirlinesList() != null) {
                System.out.println("Airlines: " + airport.getAirlinesList().size());
                airport.getAirlinesList().forEach(airline -> {
                    System.out.println("  Airline: " + airline.getAirlineName() + " (" + airline.getAirlineCode() + ")");
                    if (airline.getFlights() != null) {
                        airline.getFlights().forEach(flight -> {
                            System.out.println("    Flight: " + flight.getFlightNumber() + " to " + flight.getDestination());
                            if (flight.getPassengers() != null) {
                                flight.getPassengers().forEach(passenger -> {
                                    System.out.println("      Passenger: " + passenger.getFullName());
                                    if (passenger.getTickets() != null) {
                                        passenger.getTickets().forEach(ticket ->
                                                System.out.println("        Ticket: " + ticket.getTicketId() + " - $" + ticket.getPrice())
                                        );
                                    }
                                });
                            }
                        });
                    }
                });
            }

        } catch (JAXBException e) {
            throw new RuntimeException("JAXB parsing failed", e);
        }
    }
}


