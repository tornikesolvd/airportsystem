package org.airportsys.identity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.airportsys.adapter.LocalDateAdapter;
import org.airportsys.ticket.Ticket;

import java.time.LocalDate;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Passenger {

    private String fullName;
    private String passportNumber;
    
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthDate;
    
    @XmlElementWrapper(name = "tickets")
    @XmlElement(name = "ticket")
    private List<Ticket> tickets;

    public Passenger() {
        // No-arg constructor for JAXB
    }

    public Passenger(String fullName, String passportNumber){
        this.fullName = fullName;
        this.passportNumber = passportNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName=fullName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getBirthDate() {
        return  birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Ticket> getTickets() {
        return  tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


}
