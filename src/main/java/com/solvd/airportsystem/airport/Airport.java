package com.solvd.airportsystem.airport;

import com.solvd.airportsystem.airline.Airline;
import com.solvd.airportsystem.identity.Staff;
import com.solvd.airportsystem.terminal.Terminal;
import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "airport")
@XmlAccessorType(XmlAccessType.FIELD)
public class Airport {

    private Long airportId;
    private String airportName;
    private String location;

    @XmlElementWrapper(name = "terminals")
    @XmlElement(name = "terminal")
    private List<Terminal> terminals;

    private boolean international;

    @XmlElementWrapper(name = "staff")
    @XmlElement(name = "staffMember")
    private List<Staff> staffList;

    @XmlElementWrapper(name = "airlines")
    @XmlElement(name = "airline")
    private List<Airline> airlinesList;

    public Airport() {
        // No-arg constructor for JAXB
    }

    public Airport(Long airportId, String name, String location) {
        this.airportId = airportId;
        this.airportName = name;
        this.location = location;
    }

    public Long getAirportId() {
        return airportId;
    }

    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String name) {
        this.airportName = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<Terminal> terminals) {
        this.terminals = terminals;
    }

    public boolean isInternational() {
        return international;
    }

    public void setInternational(boolean international) {
        this.international = international;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public List<Airline> getAirlinesList() {
        return airlinesList;
    }

    public void setAirlinesList(List<Airline> airlinesList) {
        this.airlinesList = airlinesList;
    }

}
