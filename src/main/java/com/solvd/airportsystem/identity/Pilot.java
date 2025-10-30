package com.solvd.airportsystem.identity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Pilot {

    private Long pilotId;
    private String pilotName;
    private String licenseNumber;
    private int flightHours;
    private String certification;

    public Pilot() {
    }

    public Pilot(Long pilotId, String pilotName, String licenseNumber, int flightHours, String certification) {
        this.pilotId = pilotId;
        this.pilotName = pilotName;
        this.licenseNumber = licenseNumber;
        this.flightHours = flightHours;
        this.certification = certification;
    }

    public Long getPilotId() {
        return pilotId;
    }

    public void setPilotId(Long pilotId) {
        this.pilotId = pilotId;
    }

    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public int getFlightHours() {
        return flightHours;
    }

    public void setFlightHours(int flightHours) {
        this.flightHours = flightHours;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }
}


