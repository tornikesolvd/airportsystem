package com.solvd.airportsystem.service.updater;

import com.solvd.airportsystem.domain.Aircraft;
import com.solvd.airportsystem.domain.Airline;
import com.solvd.airportsystem.domain.Airport;
import com.solvd.airportsystem.domain.Flight;
import com.solvd.airportsystem.domain.Passenger;
import com.solvd.airportsystem.domain.Pilot;
import com.solvd.airportsystem.domain.Ticket;

public class EntityUpdater {

    public static void updateFlight(Flight existing, Flight updated) {
        existing.setFlightNumber(updated.getFlightNumber());
        existing.setDestination(updated.getDestination());
        existing.setDepartureDate(updated.getDepartureDate());
        existing.setDepartureTime(updated.getDepartureTime());
        existing.setDelayed(updated.isDelayed());
    }

    public static void updateAircraft(Aircraft existing, Aircraft updated) {
        existing.setAircraftType(updated.getAircraftType());
        existing.setCapacity(updated.getCapacity());
    }

    public static void updateAirline(Airline existing, Airline updated) {
        existing.setAirlineName(updated.getAirlineName());
        existing.setAirlineCode(updated.getAirlineCode());
    }

    public static void updateAirport(Airport existing, Airport updated) {
        existing.setAirportName(updated.getAirportName());
        existing.setLocation(updated.getLocation());
        existing.setInternational(updated.isInternational());
    }

    public static void updatePassenger(Passenger existing, Passenger updated) {
        existing.setFullName(updated.getFullName());
        existing.setPassportNumber(updated.getPassportNumber());
        existing.setBirthDate(updated.getBirthDate());
    }

    public static void updatePilot(Pilot existing, Pilot updated) {
        existing.setPilotName(updated.getPilotName());
        existing.setLicenseNumber(updated.getLicenseNumber());
        existing.setFlightHours(updated.getFlightHours());
        existing.setCertification(updated.getCertification());
    }

    public static void updateTicket(Ticket existing, Ticket updated) {
        existing.setPrice(updated.getPrice());
        existing.setCheckedIn(updated.isCheckedIn());
    }
}

