package com.solvd.airportsystem.persistence.repository.mybatis;

import com.solvd.airportsystem.domain.Aircraft;
import com.solvd.airportsystem.persistence.MyBatisSessionHolder;
import com.solvd.airportsystem.persistence.repository.AircraftRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class AircraftMapperImpl implements AircraftRepository {

    @Override
    public void create(Aircraft aircraft) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AircraftRepository aircraftRepository = session.getMapper(AircraftRepository.class);
            aircraftRepository.create(aircraft);
        }
    }

    @Override
    public void update(Aircraft aircraft) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AircraftRepository aircraftRepository = session.getMapper(AircraftRepository.class);
            aircraftRepository.update(aircraft);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AircraftRepository aircraftRepository = session.getMapper(AircraftRepository.class);
            aircraftRepository.deleteById(id);
        }
    }

    @Override
    public Optional<Aircraft> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AircraftRepository aircraftRepository = session.getMapper(AircraftRepository.class);
            return aircraftRepository.findById(id);
        }
    }

    @Override
    public List<Aircraft> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            AircraftRepository aircraftRepository = session.getMapper(AircraftRepository.class);
            return aircraftRepository.findAll();
        }
    }
}

