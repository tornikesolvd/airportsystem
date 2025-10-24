package org.airportsys.parser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.airportsys.airport.Airport;

import java.io.File;

public class JacksonParser {

    public static void main(String[] args) {

        try {
            ObjectMapper mapper = new ObjectMapper()
                    .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
                    .findAndRegisterModules();
            Airport airport = mapper.readValue(new File("src/main/resources/airport.json"), Airport.class);

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

        } catch (Exception e) {
            throw new RuntimeException("Jackson parsing failed", e);
        }
    }
}

