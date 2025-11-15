package com.solvd.airportsystem.service.impl;

import com.solvd.airportsystem.domain.Passenger;
import com.solvd.airportsystem.domain.Ticket;
import com.solvd.airportsystem.persistence.repository.PassengerRepository;
import com.solvd.airportsystem.persistence.repository.TicketRepository;
import com.solvd.airportsystem.persistence.repository.impl.PassengerRepositoryImpl;
import com.solvd.airportsystem.persistence.repository.impl.TicketRepositoryImpl;
import com.solvd.airportsystem.service.PassengerService;

import java.util.List;
import java.util.Optional;

public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final TicketRepository ticketRepository;

    public PassengerServiceImpl() {
        this.passengerRepository = new PassengerRepositoryImpl();
        this.ticketRepository = new TicketRepositoryImpl();
    }

    @Override
    public Passenger create(Passenger passenger) {
        passenger.setId(null);
        passengerRepository.create(passenger);

        // Create nested Tickets if they exist
        if (passenger.getTickets() != null && !passenger.getTickets().isEmpty()) {
            for (Ticket ticket : passenger.getTickets()) {
                if (ticket.getId() == null) {
                    ticket.setId(null);
                    ticketRepository.create(ticket);
                }
            }
        }

        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {
        Passenger existingPassenger = passengerRepository.findById(passenger.getId())
                .orElseThrow(() -> new RuntimeException("Passenger with id " + passenger.getId() + " cannot be found"));
        existingPassenger.setFullName(passenger.getFullName());
        existingPassenger.setPassportNumber(passenger.getPassportNumber());
        existingPassenger.setBirthDate(passenger.getBirthDate());
        passengerRepository.update(existingPassenger);
        return existingPassenger;
    }

    @Override
    public void deleteById(Long id) {
        passengerRepository.deleteById(id);
    }

    @Override
    public Optional<Passenger> findById(Long id) {
        return passengerRepository.findById(id);
    }

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }
}

