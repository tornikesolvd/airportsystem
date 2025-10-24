package org.airportsys;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.airportsys.airport.Airport;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

/**
 * Airport System parser/validator main class
 *
 * This class provides validator between xml and xsd, also parser/reader inside files. Its generally created for data processing
 * <p>javadoc with parser and mentioned list of elements  with XPath</p>
 * <ul>
 *   <li><b>/airportSystem/airport/airportName</b> - Airport name </li>
 *   <li><b>/airportSystem/airport/terminals/terminal/terminalName</b> - Terminal name list</li>
 *   <li><b>/airportSystem/airlines/airline/airlineName</b> - Airline name list</li>
 *   <li><b>/airportSystem/airport/staff/staffMember/role</b> - Staff role list</li>
 *   <li><b>/airportSystem/airport/passengers/passenger/fullName</b> - Passenger full name</li>
 *   <li><b>/airportSystem/passengers/passenger/passportNumber</b> - Passenger passport number list</li>
 *   <li><b>/airportSystem/passengers/passenger/tickets/ticket/ticketId</b> - Ticket ID list</li>
 *   <li><b>/airportSystem/passengers/passenger/tickets/ticket/price</b> - Ticket price list</li>
 * </ul>
 * 
 * @author Airport System Developer
 * @version 1.0
 */
public class AirportApp {
    public static void main(String[] args) {

        String xmlFilePath = "src/main/resources/airportSystem.xml";
        String xsdFilePath = "src/main/resources/airportSchema.xml";

        if (validateXMLSchema(xsdFilePath, xmlFilePath)) {
            System.out.println(xmlFilePath + " is valid against " + xsdFilePath);
        } else {
            System.out.println(xmlFilePath + " is not valid against " + xsdFilePath);
        }

        //StAX parsing
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream("src/main/resources/airportSystem.xml"));

            while (xmlStreamReader.hasNext()) {
                int event = xmlStreamReader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:

                        String elementName = xmlStreamReader.getLocalName();
                        if ("airportName".equals(elementName)) {
                            System.out.println("Airport Name: " + xmlStreamReader.getElementText());
                        } else if ("terminalName".equals(elementName)) {
                            System.out.println("Terminal Name: " + xmlStreamReader.getElementText());
                        } else if ("airlineName".equals(elementName)) {
                            System.out.println("Airline: " + xmlStreamReader.getElementText());
                        } else if ("role".equals(elementName)) {
                            System.out.println("Staff Role: " + xmlStreamReader.getElementText());
                        } else if ("passportNumber".equals(elementName)) {
                            System.out.println("Passenger with ID: " + xmlStreamReader.getElementText());
                        } else if ("ticketId".equals(elementName)) {
                            System.out.println("Ticket ID: " + xmlStreamReader.getElementText());
                        } else if ("price".equals(elementName)) {
                            System.out.println("Ticket Price: " + xmlStreamReader.getElementText());
                        }
                        break;
                }
            }

            xmlStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //JaXB parsing
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Airport.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            File xmlFile = new File("src/main/resources/airportSystem.xml");
            Airport airport = (Airport) jaxbUnmarshaller.unmarshal(xmlFile);

            System.out.println("Airport: " + airport.getAirportName() + " - " + airport.getLocation());
            if (airport.getTerminals() != null) {
                airport.getTerminals().forEach(terminal -> 
                    System.out.println("Terminal: " + terminal.getTerminalName())
                );
            }
            if (airport.getStaffList() != null) {
                airport.getStaffList().forEach(staff -> 
                    System.out.println("Staff: " + staff.getStaffName() + " " + staff.getRole())
                );
            }

            if (airport.getAirlinesList() != null) {
                airport.getAirlinesList().forEach(airline -> {
                    System.out.println("Airline: " + airline.getAirlineName() + " " +  airline.getAirlineCode());
                    if (airline.getFlights() != null) {
                        airline.getFlights().forEach(flight -> {
                            System.out.println("Flight " + flight.getFlightNumber() + " to " +
                                flight.getDestination() + " at " + flight.getDepartureTime());
                            if (flight.getPassengers() != null) {
                                flight.getPassengers().forEach(passenger -> {
                                    System.out.println("  Passenger: " + passenger.getFullName() + " " + passenger.getPassportNumber());
                                    if (passenger.getTickets() != null) {
                                        passenger.getTickets().forEach(ticket -> 
                                            System.out.println("    Ticket " + ticket.getTicketId() + ": $" +
                                                ticket.getPrice() + " " + "Flight " + " " + ticket.getFlightNumber())
                                        );
                                    }
                                });
                            }
                        });
                    }
                });
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        //Jackson Parsing
        try {
            ObjectMapper mapper = new ObjectMapper()
                    .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
                    .findAndRegisterModules();
            Airport airport= mapper.readValue(new File("src/main/resources/airport.json"), Airport.class);
            System.out.println("Airport: " + airport.getAirportName()) ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //JSONPath Parsing
        try {
            String jsonContent = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get("src/main/resources/airport.json")));

            System.out.println();
            System.out.println();
            
            // 1. Airport name
            String airportName = JsonPath.read(jsonContent, "$.airportName");
            System.out.println("Airport Name: " + airportName);
            
            // 2. Airport location
            String location = JsonPath.read(jsonContent, "$.location");
            System.out.println("Airport Location: " + location);
            
            // 3. List of all terminal names
            java.util.List<String> terminalNames = JsonPath.read(jsonContent, "$.terminals[*].terminalName");
            System.out.println("Terminal Names: " + terminalNames);
            
            // 4. List of all airline names
            java.util.List<String> airlineNames = JsonPath.read(jsonContent, "$.airlinesList[*].airlineName");
            System.out.println("Airline Names: " + airlineNames);
            
            // 6. List of passenger names
            java.util.List<String> passengerNames = JsonPath.read(jsonContent, "$.airlinesList[*].flights[*].passengers[*].fullName");
            System.out.println("Passenger Names: " + passengerNames);

            // 7. List of all ticket prices
            java.util.List<Double> ticketPrices = JsonPath.read(jsonContent, "$.airlinesList[*].flights[*].passengers[*].tickets[*].price");
            System.out.println("Ticket Prices: " + ticketPrices);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            return false;
        } catch (SAXException e) {
            System.out.println("SAXException: " + e.getMessage());
            return false;
        }
        return true;
    }
}