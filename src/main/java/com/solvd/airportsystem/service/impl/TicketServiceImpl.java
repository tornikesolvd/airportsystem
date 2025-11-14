package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.persistence.repository.TicketRepository;
import com.solvd.airportsystem.persistence.repository.impl.TicketRepositoryImpl;
import com.solvd.airportsystem.service.TicketService;
import com.solvd.airportsystem.domain.Ticket;

import java.util.List;
import java.util.Optional;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl() {
        this.ticketRepository = new TicketRepositoryImpl();
    }

    @Override
    public Ticket create(Ticket ticket) {
        ticket.setId(null);
        ticketRepository.create(ticket);
        return ticket;
    }

    @Override
    public Ticket update(Ticket ticket) {
        Ticket existingTicket = ticketRepository.findById(ticket.getId())
                .orElseThrow(() -> new RuntimeException("Ticket with id " + ticket.getId() + " cannot be found"));
        existingTicket.setPrice(ticket.getPrice());
        existingTicket.setCheckedIn(ticket.isCheckedIn());
        ticketRepository.update(existingTicket);
        return existingTicket;
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }
}

