package com.solvd.airportsystem.persistence.repository.impl;

import com.solvd.airportsystem.domain.Passenger;
import com.solvd.airportsystem.persistence.ConnectionPool;
import com.solvd.airportsystem.persistence.repository.PassengerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PassengerRepositoryImpl implements PassengerRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Passenger passenger) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "INSERT INTO passengers (name, passport_number, birth_date) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, passenger.getFullName());
            preparedStatement.setString(2, passenger.getPassportNumber());
            preparedStatement.setDate(3, Date.valueOf(passenger.getBirthDate()));
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                passenger.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create passenger", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Passenger passenger) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "UPDATE passengers SET name = ?, passport_number = ?, birth_date = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, passenger.getFullName());
            preparedStatement.setString(2, passenger.getPassportNumber());
            preparedStatement.setDate(3, Date.valueOf(passenger.getBirthDate()));
            preparedStatement.setLong(4, passenger.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update passenger", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "DELETE FROM passengers WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete passenger", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Passenger> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT id, name, passport_number, birth_date FROM passengers WHERE id = ?";
        Optional<Passenger> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getLong("id"));
                passenger.setFullName(resultSet.getString("name"));
                passenger.setPassportNumber(resultSet.getString("passport_number"));
                Date birthDate = resultSet.getDate("birth_date");
                if (birthDate != null) {
                    passenger.setBirthDate(birthDate.toLocalDate());
                }
                result = Optional.of(passenger);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find passenger with id " + id, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public List<Passenger> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT id, name, passport_number, birth_date FROM passengers";
        List<Passenger> passengers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getLong("id"));
                passenger.setFullName(resultSet.getString("name"));
                passenger.setPassportNumber(resultSet.getString("passport_number"));
                Date birthDate = resultSet.getDate("birth_date");
                if (birthDate != null) {
                    passenger.setBirthDate(birthDate.toLocalDate());
                }
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find passengers", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return passengers;
    }
}

