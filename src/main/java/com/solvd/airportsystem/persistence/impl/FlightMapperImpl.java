package com.solvd.airportsystem.persistence.impl;

import com.solvd.airportsystem.domain.Flight;
import com.solvd.airportsystem.persistence.MyBatisSessionHolder;
import com.solvd.airportsystem.persistence.FlightRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class FlightMapperImpl implements FlightRepository {

    @Override
    public void create(Flight flight) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            FlightRepository flightRepository = session.getMapper(FlightRepository.class);
            flightRepository.create(flight);
        }
    }

    @Override
    public void update(Flight flight) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            FlightRepository flightRepository = session.getMapper(FlightRepository.class);
            flightRepository.update(flight);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            FlightRepository flightRepository = session.getMapper(FlightRepository.class);
            flightRepository.deleteById(id);
        }
    }

    @Override
    public Optional<Flight> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            FlightRepository flightRepository = session.getMapper(FlightRepository.class);
            return flightRepository.findById(id);
        }
    }

    @Override
    public List<Flight> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            FlightRepository flightRepository = session.getMapper(FlightRepository.class);
            return flightRepository.findAll();
        }
    }

    @Override
    public List<Flight> findAllWithAllData() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            FlightRepository flightRepository = session.getMapper(FlightRepository.class);
            return flightRepository.findAllWithAllData();
        }
    }
}

