package com.solvd.airportsystem.airline;

import com.solvd.airportsystem.flight.Flight;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Airline {

    private Long airlineId;
    private String airlineName;
    private String airlineCode;

    @XmlElementWrapper(name = "flights")
    @XmlElement(name = "flight")
    private List<Flight> flights;

    public Long getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Long airline_id) {
        this.airlineId = airlineId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

}
