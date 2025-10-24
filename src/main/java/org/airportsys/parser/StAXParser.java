package org.airportsys.parser;

import org.airportsys.airline.Airline;
import org.airportsys.airport.Airport;
import org.airportsys.flight.Flight;
import org.airportsys.identity.Passenger;
import org.airportsys.identity.Staff;
import org.airportsys.terminal.Terminal;
import org.airportsys.ticket.Ticket;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * StAX Parser for Airport System
 *
 * <p>Finding elements:</p>
 * <ul>
 *   <li><b>/airport/airportName</b> Airport name </li>
 *   <li><b>/airport/terminals/terminal/terminalName</b>  Terminal name list</li>
 *   <li><b>/airport/airlines/airline/airlineName</b>  Airline name list</li>
 *   <li><b>/airport/staff/staffMember/role</b> Staff role list</li>
 *   <li><b>/airport/airlines/airline/flights/flight/flightNumber</b>  Flight number list</li>
 *   <li><b>/airport/airlines/airline/flights/flight/passengers/passenger/fullName</b>  Passenger full name list</li>
 *   <li><b>/airport/airlines/airline/flights/flight/passengers/passenger/passportNumber</b>  Passenger passport number list</li>
 *   <li><b>/airport/airlines/airline/flights/flight/passengers/passenger/tickets/ticket/ticketId</b>  Ticket ID list</li>
 *   <li><b>/airport/airlines/airline/flights/flight/passengers/passenger/tickets/ticket/price</b>  Ticket price list</li>
 *   <li><b>/airport/airlines/airline/flights/flight/aircraft/aircraftId</b>  Aircraft ID list </li>
 * </ul>
 *
 * @author Airport System Developer
 * @version 1.0
 */
public class StAXParser {

    public static void main(String[] args) {

        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream("src/main/resources/airportsystem.xml"));

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
                                    ticket.setTicketId(text);
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

            System.out.println("Airport: " + airport.getAirportName());
            System.out.println("Location: " + airport.getLocation());
            System.out.println("International: " + airport.isInternational());
            System.out.println("Terminals: " + airport.getTerminals().size());
            System.out.println("Staff: " + airport.getStaffList().size());
            System.out.println("Total airlines: " + airport.getAirlinesList().size());

            xmlStreamReader.close();

        } catch (Exception e) {
            throw new RuntimeException("StAX parsing failed", e);
        }
    }
}

