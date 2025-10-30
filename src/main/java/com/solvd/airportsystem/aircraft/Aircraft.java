package com.solvd.airportsystem.aircraft;

import com.solvd.airportsystem.identity.Pilot;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Aircraft {

    private Long aircraftId;
    private String aircraftType;
    private Integer capacity;

    @XmlElementWrapper(name = "pilots")
    @XmlElement(name = "pilot")
    private List<Pilot> pilots;

    public Aircraft() {
    }

    public Aircraft(Long aircraftId, String aircraftType, Integer capacity) {
        this.aircraftId = aircraftId;
        this.aircraftType = aircraftType;
        this.capacity = capacity;
    }

    public Long getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
    }
}

