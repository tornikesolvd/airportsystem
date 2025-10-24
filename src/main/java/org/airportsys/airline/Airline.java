package org.airportsys.airline;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import org.airportsys.flight.Flight;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Airline {

    private String airlineName;
    private String airlineCode;

    @XmlElementWrapper(name = "flights")
    @XmlElement(name = "flight")
    private List<Flight> flights;

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
