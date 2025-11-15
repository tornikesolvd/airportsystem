package com.solvd.airportsystem.service;

import com.solvd.airportsystem.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    Ticket create(Ticket ticket);

    Ticket update(Ticket ticket);

    void deleteById(Long id);

    Optional<Ticket> findById(Long id);

    List<Ticket> findAll();
}

