package com.solvd.airportsystem.domain;

import com.solvd.airportsystem.adapter.LocalDateAdapter;
import com.solvd.airportsystem.adapter.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Flight {

    private Long id;
    private String flightNumber;
    private String destination;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate departureDate;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime departureTime;

    private boolean delayed;

    @XmlElementWrapper(name = "passengers")
    @XmlElement(name = "passenger")
    private List<Passenger> passengers;

    private Aircraft aircraft;
    private Gate gate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDelayed() {
        return delayed;
    }

    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Gate getGate() {
        return gate;
    }

    public void setGate(Gate gate) {
        this.gate = gate;
    }
}




