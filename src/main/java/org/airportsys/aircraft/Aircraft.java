package org.airportsys.aircraft;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import org.airportsys.identity.Pilot;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Aircraft {

    private String aircraftId;
    private String aircraftType;
    private Integer capacity;

    @XmlElementWrapper(name = "pilots")
    @XmlElement(name = "pilot")
    private List<Pilot> pilots;

    public Aircraft() {
    }

    public Aircraft(String aircraftId, String aircraftType, Integer capacity) {
        this.aircraftId = aircraftId;
        this.aircraftType = aircraftType;
        this.capacity = capacity;
    }

    public String getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(String aircraftId) {
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

