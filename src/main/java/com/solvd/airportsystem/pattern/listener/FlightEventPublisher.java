package com.solvd.airportsystem.pattern.listener;

import com.solvd.airportsystem.domain.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightEventPublisher {
    private List<FlightStatusListener> listeners;

    public FlightEventPublisher() {
        this.listeners = new ArrayList<>();
    }

    public void subscribe(FlightStatusListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(FlightStatusListener listener) {
        listeners.remove(listener);
    }

    public void notifyFlightDelayed(Flight flight) {
        for (FlightStatusListener listener : listeners) {
            listener.onFlightDelayed(flight);
        }
    }

    public void notifyFlightOnTime(Flight flight) {
        for (FlightStatusListener listener : listeners) {
            listener.onFlightOnTime(flight);
        }
    }

    public void notifyFlightCancelled(Flight flight) {
        for (FlightStatusListener listener : listeners) {
            listener.onFlightCancelled(flight);
        }
    }
}

