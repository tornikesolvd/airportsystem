package com.solvd.airportsystem.gate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Gate {

    private Long gateId;
    private String gateNumber;
    private String gateType;
    private boolean available;
    private String terminal;

    public Gate() {
    }

    public Gate(Long gateId, String gateNumber, String gateType, boolean isAvailable, String terminal) {
        this.gateId = gateId;
        this.gateNumber = gateNumber;
        this.gateType = gateType;
        this.available = isAvailable;
        this.terminal = terminal;
    }

    public Long getGateId() {
        return gateId;
    }

    public void setGateId(Long gateId) {
        this.gateId = gateId;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public String getGateType() {
        return gateType;
    }

    public void setGateType(String gateType) {
        this.gateType = gateType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        available = available;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
}


