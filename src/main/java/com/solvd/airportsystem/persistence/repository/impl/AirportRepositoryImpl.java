package com.solvd.airportsystem.persistence.repository.impl;

import com.solvd.airportsystem.domain.Airport;
import com.solvd.airportsystem.persistence.ConnectionPool;
import com.solvd.airportsystem.persistence.repository.AirportRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirportRepositoryImpl implements AirportRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Airport airport) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "INSERT INTO airports (name, location, international) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, airport.getAirportName());
            preparedStatement.setString(2, airport.getLocation());
            preparedStatement.setBoolean(3, airport.isInternational());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                airport.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create airport", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Airport airport) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "UPDATE airports SET name = ?, location = ?, international = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, airport.getAirportName());
            preparedStatement.setString(2, airport.getLocation());
            preparedStatement.setBoolean(3, airport.isInternational());
            preparedStatement.setLong(4, airport.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update airport", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "DELETE FROM airports WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete airport", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Airport> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT id, name, location, international FROM airports WHERE id = ?";
        Optional<Airport> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Airport airport = new Airport();
                airport.setId(resultSet.getLong("id"));
                airport.setAirportName(resultSet.getString("name"));
                airport.setLocation(resultSet.getString("location"));
                airport.setInternational(resultSet.getBoolean("international"));
                result = Optional.of(airport);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find airport with id " + id, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public List<Airport> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT id, name, location, international FROM airports";
        List<Airport> airports = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Airport airport = new Airport();
                airport.setId(resultSet.getLong("id"));
                airport.setAirportName(resultSet.getString("name"));
                airport.setLocation(resultSet.getString("location"));
                airport.setInternational(resultSet.getBoolean("international"));
                airports.add(airport);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find airports", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return airports;
    }
}

