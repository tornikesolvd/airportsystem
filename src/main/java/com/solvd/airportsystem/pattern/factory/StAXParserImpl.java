package com.solvd.airportsystem.pattern.factory;

import com.solvd.airportsystem.domain.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StAXParserImpl implements Parser {

    @Override
    public Airport parse(File file) {
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(file));

            Airport airport = new Airport();
            List<Terminal> terminals = new ArrayList<>();
            List<Staff> staffList = new ArrayList<>();
            List<Airline> airlinesList = new ArrayList<>();
            List<Flight> flights = new ArrayList<>();
            List<Passenger> passengers = new ArrayList<>();
            List<Ticket> tickets = new ArrayList<>();

            String currentElement = "";
            String currentAirline = "";
            String currentFlight = "";

            while (xmlStreamReader.hasNext()) {
                int next = xmlStreamReader.next();

                switch (next) {
                    case XMLStreamConstants.START_ELEMENT:
                        currentElement = xmlStreamReader.getLocalName();
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        String text = xmlStreamReader.getText().trim();
                        if (!text.isEmpty()) {
                            switch (currentElement) {
                                case "airportName":
                                    airport.setAirportName(text);
                                    break;
                                case "location":
                                    airport.setLocation(text);
                                    break;
                                case "international":
                                    airport.setInternational(Boolean.parseBoolean(text));
                                    break;
                                case "terminalName":
                                    Terminal terminal = new Terminal();
                                    terminal.setTerminalName(text);
                                    terminals.add(terminal);
                                    break;
                                case "staffName":
                                    Staff staff = new Staff();
                                    staff.setStaffName(text);
                                    staffList.add(staff);
                                    break;
                                case "role":
                                    if (!staffList.isEmpty()) {
                                        staffList.get(staffList.size() - 1).setRole(text);
                                    }
                                    break;
                                case "airlineName":
                                    Airline airline = new Airline();
                                    airline.setAirlineName(text);
                                    airlinesList.add(airline);
                                    currentAirline = text;
                                    break;
                                case "airlineCode":
                                    if (!airlinesList.isEmpty()) {
                                        airlinesList.get(airlinesList.size() - 1).setAirlineCode(text);
                                    }
                                    break;
                                case "flightNumber":
                                    Flight flight = new Flight();
                                    flight.setFlightNumber(text);
                                    flights.add(flight);
                                    currentFlight = text;
                                    break;
                                case "destination":
                                    if (!flights.isEmpty()) {
                                        flights.get(flights.size() - 1).setDestination(text);
                                    }
                                    break;
                                case "departureDate":
                                    if (!flights.isEmpty()) {
                                        flights.get(flights.size() - 1).setDepartureDate(LocalDate.parse(text));
                                    }
                                    break;
                                case "departureTime":
                                    if (!flights.isEmpty()) {
                                        flights.get(flights.size() - 1).setDepartureTime(LocalDateTime.parse(text));
                                    }
                                    break;
                                case "delayed":
                                    if (!flights.isEmpty()) {
                                        flights.get(flights.size() - 1).setDelayed(Boolean.parseBoolean(text));
                                    }
                                    break;
                                case "fullName":
                                    Passenger passenger = new Passenger();
                                    passenger.setFullName(text);
                                    passengers.add(passenger);
                                    break;
                                case "passportNumber":
                                    if (!passengers.isEmpty()) {
                                        passengers.get(passengers.size() - 1).setPassportNumber(text);
                                    }
                                    break;
                                case "birthDate":
                                    if (!passengers.isEmpty()) {
                                        passengers.get(passengers.size() - 1).setBirthDate(LocalDate.parse(text));
                                    }
                                    break;
                                case "ticketId":
                                    Ticket ticket = new Ticket();
                                    ticket.setFlightNumber(text);
                                    tickets.add(ticket);
                                    break;
                                case "price":
                                    if (!tickets.isEmpty()) {
                                        tickets.get(tickets.size() - 1).setPrice(new BigDecimal(text));
                                    }
                                    break;
                                case "checkedIn":
                                    if (!tickets.isEmpty()) {
                                        tickets.get(tickets.size() - 1).setCheckedIn(Boolean.parseBoolean(text));
                                    }
                                    break;
                            }
                        }
                        break;
                }
            }

            airport.setTerminals(terminals);
            airport.setStaffList(staffList);

            for (int i = 0; i < airlinesList.size(); i++) {
                Airline airline = airlinesList.get(i);
                List<Flight> airlineFlights = new ArrayList<>();
                for (Flight flight : flights) {
                    airlineFlights.add(flight);
                }
                airline.setFlights(airlineFlights);
            }

            airport.setAirlinesList(airlinesList);

            xmlStreamReader.close();
            return airport;

        } catch (Exception e) {
            throw new RuntimeException("StAX parsing failed", e);
        }
    }
}

