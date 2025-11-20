package com.solvd.airportsystem.persistence.impl;

import com.solvd.airportsystem.domain.Airline;
import com.solvd.airportsystem.persistence.ConnectionPool;
import com.solvd.airportsystem.persistence.AirlineRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirlineRepositoryImpl implements AirlineRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Airline airline) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "INSERT INTO airlines (name, code) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, airline.getAirlineName());
            preparedStatement.setString(2, airline.getAirlineCode());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                airline.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create airline", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Airline airline) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "UPDATE airlines SET name = ?, code = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, airline.getAirlineName());
            preparedStatement.setString(2, airline.getAirlineCode());
            preparedStatement.setLong(3, airline.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update airline", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "DELETE FROM airlines WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete airline", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Airline> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT id, name, code FROM airlines WHERE id = ?";
        Optional<Airline> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Airline airline = new Airline();
                airline.setId(resultSet.getLong("id"));
                airline.setAirlineName(resultSet.getString("name"));
                airline.setAirlineCode(resultSet.getString("code"));
                result = Optional.of(airline);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find airline with id " + id, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public List<Airline> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT id, name, code FROM airlines";
        List<Airline> airlines = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Airline airline = new Airline();
                airline.setId(resultSet.getLong("id"));
                airline.setAirlineName(resultSet.getString("name"));
                airline.setAirlineCode(resultSet.getString("code"));
                airlines.add(airline);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find airlines", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return airlines;
    }
}

