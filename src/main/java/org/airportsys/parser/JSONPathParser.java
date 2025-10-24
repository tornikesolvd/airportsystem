package org.airportsys.parser;

import com.jayway.jsonpath.JsonPath;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JSONPathParser {

    public static void main(String[] args) {

        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get("src/main/resources/airport.json")));

            String airportName = JsonPath.read(jsonContent, "$.airportName");
            System.out.println("Airport Name: " + airportName);

            String location = JsonPath.read(jsonContent, "$.location");
            System.out.println("Airport Location: " + location);

            List<String> terminalNames = JsonPath.read(jsonContent, "$.terminals[*].terminalName");
            System.out.println("Terminal Names: " + terminalNames);

            List<String> airlineNames = JsonPath.read(jsonContent, "$.airlinesList[*].airlineName");
            System.out.println("Airline Names: " + airlineNames);

            List<String> passengerNames = JsonPath.read(jsonContent, "$.airlinesList[*].flights[*].passengers[*].fullName");
            System.out.println("Passenger Names: " + passengerNames);

            List<String> ticketIds = JsonPath.read(jsonContent, "$.airlinesList[*].flights[*].passengers[*].tickets[*].ticketId");
            System.out.println("Ticket IDs: " + ticketIds);

            List<Double> ticketPrices = JsonPath.read(jsonContent, "$.airlinesList[*].flights[*].passengers[*].tickets[*].price");
            System.out.println("Ticket Prices: " + ticketPrices);

        } catch (Exception e) {
            throw new RuntimeException("JSONPath parsing failed", e);
        }
    }
}

