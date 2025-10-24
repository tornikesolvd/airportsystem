package org.airportsys.terminal;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import org.airportsys.airport.Airport;

@XmlAccessorType(XmlAccessType.FIELD)
public class Terminal {
    private String terminalName;

    public Terminal() {
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }
}
