package com.solvd.airportsystem.pattern.facade;

import com.solvd.airportsystem.domain.Flight;
import com.solvd.airportsystem.domain.Passenger;
import com.solvd.airportsystem.domain.Ticket;
import com.solvd.airportsystem.pattern.builder.TicketBuilder;
import com.solvd.airportsystem.service.FlightService;
import com.solvd.airportsystem.service.PassengerService;
import com.solvd.airportsystem.service.TicketService;

import java.math.BigDecimal;

public class BookingFacade {
    private FlightService flightService;
    private PassengerService passengerService;
    private TicketService ticketService;

    public BookingFacade(FlightService flightService, PassengerService passengerService, TicketService ticketService) {
        this.flightService = flightService;
        this.passengerService = passengerService;
        this.ticketService = ticketService;
    }

    public Ticket bookFlight(Long flightId, Passenger passenger, BigDecimal price) {
        Flight flight = flightService.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        passenger.setId(null);
        Passenger savedPassenger = passengerService.create(passenger);

        Ticket ticket = new TicketBuilder()
                .withFlightNumber(flight.getFlightNumber())
                .withPrice(price)
                .withCheckedIn(false)
                .build();

        return ticketService.create(ticket);
    }
}

