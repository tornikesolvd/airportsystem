package org.airportsys.airport;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.airportsys.airline.Airline;
import org.airportsys.identity.Staff;
import org.airportsys.terminal.Terminal;

import java.util.List;

@XmlRootElement(name = "airport")
@XmlAccessorType(XmlAccessType.FIELD)
public class Airport {

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

    public Airport(String name, String location){
        this.airportName = name;
        this.location = location;
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
