package com.solvd.airportsystem.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Pilot {

    private Long id;
    private String pilotName;
    private String licenseNumber;
    private int flightHours;
    private String certification;

    public Pilot() {
    }

    public Pilot(Long id, String pilotName, String licenseNumber, int flightHours, String certification) {
        this.id = id;
        this.pilotName = pilotName;
        this.licenseNumber = licenseNumber;
        this.flightHours = flightHours;
        this.certification = certification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


