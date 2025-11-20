package com.solvd.airportsystem.persistence.impl;

import com.solvd.airportsystem.domain.Passenger;
import com.solvd.airportsystem.persistence.MyBatisSessionHolder;
import com.solvd.airportsystem.persistence.PassengerRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class PassengerMapperImpl implements PassengerRepository {

    @Override
    public void create(Passenger passenger) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PassengerRepository passengerRepository = session.getMapper(PassengerRepository.class);
            passengerRepository.create(passenger);
        }
    }

    @Override
    public void update(Passenger passenger) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PassengerRepository passengerRepository = session.getMapper(PassengerRepository.class);
            passengerRepository.update(passenger);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PassengerRepository passengerRepository = session.getMapper(PassengerRepository.class);
            passengerRepository.deleteById(id);
        }
    }

    @Override
    public Optional<Passenger> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PassengerRepository passengerRepository = session.getMapper(PassengerRepository.class);
            return passengerRepository.findById(id);
        }
    }

    @Override
    public List<Passenger> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PassengerRepository passengerRepository = session.getMapper(PassengerRepository.class);
            return passengerRepository.findAll();
        }
    }
}

