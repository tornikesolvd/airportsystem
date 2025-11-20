package com.solvd.airportsystem.pattern.listener;

import com.solvd.airportsystem.domain.Flight;

public class EmailNotificationListener implements FlightStatusListener {

    @Override
    public void onFlightDelayed(Flight flight) {
        System.out.println("Email: Flight " + flight.getFlightNumber() + " is delayed.");
    }

    @Override
    public void onFlightOnTime(Flight flight) {
        System.out.println("Email: Flight " + flight.getFlightNumber() + " is on time.");
    }

    @Override
    public void onFlightCancelled(Flight flight) {
        System.out.println("Email: Flight " + flight.getFlightNumber() + " has been cancelled.");
    }
}

