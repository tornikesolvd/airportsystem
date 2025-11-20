package com.solvd.airportsystem.persistence.impl;

import com.solvd.airportsystem.domain.Ticket;
import com.solvd.airportsystem.persistence.MyBatisSessionHolder;
import com.solvd.airportsystem.persistence.TicketRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class TicketMapperImpl implements TicketRepository {

    @Override
    public void create(Ticket ticket) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            TicketRepository ticketRepository = session.getMapper(TicketRepository.class);
            ticketRepository.create(ticket);
        }
    }

    @Override
    public void update(Ticket ticket) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            TicketRepository ticketRepository = session.getMapper(TicketRepository.class);
            ticketRepository.update(ticket);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            TicketRepository ticketRepository = session.getMapper(TicketRepository.class);
            ticketRepository.deleteById(id);
        }
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            TicketRepository ticketRepository = session.getMapper(TicketRepository.class);
            return ticketRepository.findById(id);
        }
    }

    @Override
    public List<Ticket> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            TicketRepository ticketRepository = session.getMapper(TicketRepository.class);
            return ticketRepository.findAll();
        }
    }
}

