package com.solvd.airportsystem.persistence.impl;

import com.solvd.airportsystem.domain.Airport;
import com.solvd.airportsystem.persistence.MyBatisSessionHolder;
import com.solvd.airportsystem.persistence.AirportRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AirportMapperImpl implements AirportRepository {

    @Override
    public void create(Airport airport) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AirportRepository airportRepository = session.getMapper(AirportRepository.class);
            airportRepository.create(airport);
        }
    }

    @Override
    public void update(Airport airport) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AirportRepository airportRepository = session.getMapper(AirportRepository.class);
            airportRepository.update(airport);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AirportRepository airportRepository = session.getMapper(AirportRepository.class);
            airportRepository.deleteById(id);
        }
    }

    @Override
    public Optional<Airport> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AirportRepository airportRepository = session.getMapper(AirportRepository.class);
            return airportRepository.findById(id);
        }
    }

    @Override
    public List<Airport> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AirportRepository airportRepository = session.getMapper(AirportRepository.class);
            return airportRepository.findAll();
        }
    }
}

