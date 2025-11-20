package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Passenger;
import com.solvd.airportsystem.domain.Ticket;
import com.solvd.airportsystem.persistence.PassengerRepository;
import com.solvd.airportsystem.service.PassengerService;
import com.solvd.airportsystem.service.TicketService;
import com.solvd.airportsystem.service.updater.EntityUpdater;
import com.solvd.airportsystem.service.validator.EntityValidator;

import java.util.List;
import java.util.Optional;

public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final TicketService ticketService;

    public PassengerServiceImpl(PassengerRepository passengerRepository, TicketService ticketService) {
        this.passengerRepository = passengerRepository;
        this.ticketService = ticketService;
    }

    @Override
    public Passenger create(Passenger passenger) {
        passenger.setId(null);
        passengerRepository.create(passenger);

        if (passenger.getTickets() != null && !passenger.getTickets().isEmpty()) {
            for (Ticket ticket : passenger.getTickets()) {
                if (ticket.getId() == null) {
                    ticket.setId(null);
                    ticketService.create(ticket);
                }
            }
        }

        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {
        Passenger existingPassenger = validateAndGet(passenger.getId());
        EntityUpdater.updatePassenger(existingPassenger, passenger);
        passengerRepository.update(existingPassenger);
        return existingPassenger;
    }

    private Passenger validateAndGet(Long id) {
        Optional<Passenger> optional = passengerRepository.findById(id);
        return EntityValidator.validateEntityExists(optional.orElse(null), "Passenger", id);
    }

    @Override
    public void deleteById(Long id) {
        EntityValidator.validateId(id, "Passenger");
        passengerRepository.deleteById(id);
    }

    @Override
    public Optional<Passenger> findById(Long id) {
        EntityValidator.validateId(id, "Passenger");
        return passengerRepository.findById(id);
    }

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }
}

