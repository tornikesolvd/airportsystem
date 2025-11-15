package com.solvd.airportsystem.persistence.repository.mybatis;

import com.solvd.airportsystem.domain.Airline;
import com.solvd.airportsystem.persistence.MyBatisSessionHolder;
import com.solvd.airportsystem.persistence.repository.AirlineRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AirlineMapperImpl implements AirlineRepository {

    @Override
    public void create(Airline airline) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AirlineRepository airlineRepository = session.getMapper(AirlineRepository.class);
            airlineRepository.create(airline);
        }
    }

    @Override
    public void update(Airline airline) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AirlineRepository airlineRepository = session.getMapper(AirlineRepository.class);
            airlineRepository.update(airline);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AirlineRepository airlineRepository = session.getMapper(AirlineRepository.class);
            airlineRepository.deleteById(id);
        }
    }

    @Override
    public Optional<Airline> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AirlineRepository airlineRepository = session.getMapper(AirlineRepository.class);
            return airlineRepository.findById(id);
        }
    }

    @Override
    public List<Airline> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AirlineRepository airlineRepository = session.getMapper(AirlineRepository.class);
            return airlineRepository.findAll();
        }
    }
}

