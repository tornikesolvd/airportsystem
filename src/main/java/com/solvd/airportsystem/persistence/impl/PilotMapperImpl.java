package com.solvd.airportsystem.persistence.impl;

import com.solvd.airportsystem.domain.Pilot;
import com.solvd.airportsystem.persistence.MyBatisSessionHolder;
import com.solvd.airportsystem.persistence.PilotRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Optional;

public class PilotMapperImpl implements PilotRepository {

    @Override
    public void create(Pilot pilot) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PilotRepository pilotRepository = session.getMapper(PilotRepository.class);
            pilotRepository.create(pilot);
        }
    }

    @Override
    public void update(Pilot pilot) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PilotRepository pilotRepository = session.getMapper(PilotRepository.class);
            pilotRepository.update(pilot);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PilotRepository pilotRepository = session.getMapper(PilotRepository.class);
            pilotRepository.deleteById(id);
        }
    }

    @Override
    public Optional<Pilot> findById(Long id) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PilotRepository pilotRepository = session.getMapper(PilotRepository.class);
            return pilotRepository.findById(id);
        }
    }

    @Override
    public List<Pilot> findAll() {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PilotRepository pilotRepository = session.getMapper(PilotRepository.class);
            return pilotRepository.findAll();
        }
    }
}

