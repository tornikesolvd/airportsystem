package com.solvd.airportsystem.pattern.builder;

import com.solvd.airportsystem.domain.Aircraft;
import com.solvd.airportsystem.domain.Flight;
import com.solvd.airportsystem.domain.Gate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FlightBuilder {
    private Flight flight;

    public FlightBuilder() {
        this.flight = new Flight();
    }

    public FlightBuilder withId(Long id) {
        flight.setId(id);
        return this;
    }

    public FlightBuilder withFlightNumber(String flightNumber) {
        flight.setFlightNumber(flightNumber);
        return this;
    }

    public FlightBuilder withDestination(String destination) {
        flight.setDestination(destination);
        return this;
    }

    public FlightBuilder withDepartureDate(LocalDate departureDate) {
        flight.setDepartureDate(departureDate);
        return this;
    }

    public FlightBuilder withDepartureTime(LocalDateTime departureTime) {
        flight.setDepartureTime(departureTime);
        return this;
    }

    public FlightBuilder withDelayed(boolean delayed) {
        flight.setDelayed(delayed);
        return this;
    }

    public FlightBuilder withAircraft(Aircraft aircraft) {
        flight.setAircraft(aircraft);
        return this;
    }

    public FlightBuilder withGate(Gate gate) {
        flight.setGate(gate);
        return this;
    }

    public Flight build() {
        return flight;
    }
}

