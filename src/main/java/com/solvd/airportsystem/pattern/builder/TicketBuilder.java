package com.solvd.airportsystem.pattern.builder;

import com.solvd.airportsystem.domain.Ticket;

import java.math.BigDecimal;

public class TicketBuilder {
    private Ticket ticket;

    public TicketBuilder() {
        this.ticket = new Ticket();
    }

    public TicketBuilder withId(Long id) {
        ticket.setId(id);
        return this;
    }

    public TicketBuilder withPrice(BigDecimal price) {
        ticket.setPrice(price);
        return this;
    }

    public TicketBuilder withFlightNumber(String flightNumber) {
        ticket.setFlightNumber(flightNumber);
        return this;
    }

    public TicketBuilder withCheckedIn(boolean checkedIn) {
        ticket.setCheckedIn(checkedIn);
        return this;
    }

    public Ticket build() {
        return ticket;
    }
}

