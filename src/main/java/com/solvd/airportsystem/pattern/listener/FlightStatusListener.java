package com.solvd.airportsystem.pattern.listener;

import com.solvd.airportsystem.domain.Flight;

public interface FlightStatusListener {
    void onFlightDelayed(Flight flight);
    void onFlightOnTime(Flight flight);
    void onFlightCancelled(Flight flight);
}

