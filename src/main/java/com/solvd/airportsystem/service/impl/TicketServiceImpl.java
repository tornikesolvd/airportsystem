package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Ticket;
import com.solvd.airportsystem.persistence.TicketRepository;
import com.solvd.airportsystem.service.TicketService;
import com.solvd.airportsystem.service.updater.EntityUpdater;
import com.solvd.airportsystem.service.validator.EntityValidator;

import java.util.List;
import java.util.Optional;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket create(Ticket ticket) {
        ticket.setId(null);
        ticketRepository.create(ticket);
        return ticket;
    }

    @Override
    public Ticket update(Ticket ticket) {
        Ticket existingTicket = validateAndGet(ticket.getId());
        EntityUpdater.updateTicket(existingTicket, ticket);
        ticketRepository.update(existingTicket);
        return existingTicket;
    }

    private Ticket validateAndGet(Long id) {
        Optional<Ticket> optional = ticketRepository.findById(id);
        return EntityValidator.validateEntityExists(optional.orElse(null), "Ticket", id);
    }

    @Override
    public void deleteById(Long id) {
        EntityValidator.validateId(id, "Ticket");
        ticketRepository.deleteById(id);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        EntityValidator.validateId(id, "Ticket");
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }
}

