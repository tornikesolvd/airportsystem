package com.solvd.airportsystem.persistence;

import com.solvd.airportsystem.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {

    void create(Ticket ticket);

    void update(Ticket ticket);

    void deleteById(Long id);

    Optional<Ticket> findById(Long id);

    List<Ticket> findAll();
}

