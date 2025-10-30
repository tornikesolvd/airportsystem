package com.solvd.airportsystem.terminal;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Terminal {

    private Long terminalId;
    private String terminalName;

    public Terminal() {
    }

    public Long getTerminalId() {
        return terminalId
                ;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }
}
