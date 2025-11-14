package com.solvd.airportsystem.persistence.repository.impl;

import com.solvd.airportsystem.domain.Aircraft;
import com.solvd.airportsystem.persistence.ConnectionPool;
import com.solvd.airportsystem.persistence.repository.AircraftRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AircraftRepositoryImpl implements AircraftRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Aircraft aircraft) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "INSERT INTO aircraft (type, type_id, capacity) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, aircraft.getAircraftType());
            preparedStatement.setLong(2, 1L);
            preparedStatement.setInt(3, aircraft.getCapacity());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                aircraft.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create aircraft", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Aircraft aircraft) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "UPDATE aircraft SET type = ?, capacity = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, aircraft.getAircraftType());
            preparedStatement.setInt(2, aircraft.getCapacity());
            preparedStatement.setLong(3, aircraft.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update aircraft", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "DELETE FROM aircraft WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete aircraft", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Aircraft> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT id, type, capacity FROM aircraft WHERE id = ?";
        Optional<Aircraft> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Aircraft aircraft = new Aircraft();
                aircraft.setId(resultSet.getLong("id"));
                aircraft.setAircraftType(resultSet.getString("type"));
                aircraft.setCapacity(resultSet.getInt("capacity"));
                result = Optional.of(aircraft);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find aircraft with id " + id, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public List<Aircraft> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT id, type, capacity FROM aircraft";
        List<Aircraft> aircrafts = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Aircraft aircraft = new Aircraft();
                aircraft.setId(resultSet.getLong("id"));
                aircraft.setAircraftType(resultSet.getString("type"));
                aircraft.setCapacity(resultSet.getInt("capacity"));
                aircrafts.add(aircraft);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find aircrafts", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return aircrafts;
    }
}

