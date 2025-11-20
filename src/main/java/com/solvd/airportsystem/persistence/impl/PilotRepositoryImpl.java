package com.solvd.airportsystem.persistence.impl;

import com.solvd.airportsystem.domain.Pilot;
import com.solvd.airportsystem.persistence.ConnectionPool;
import com.solvd.airportsystem.persistence.PilotRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PilotRepositoryImpl implements PilotRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Pilot pilot) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "INSERT INTO pilots (name, license_number, flight_hours, certification) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, pilot.getPilotName());
            preparedStatement.setString(2, pilot.getLicenseNumber());
            preparedStatement.setInt(3, pilot.getFlightHours());
            preparedStatement.setString(4, pilot.getCertification());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                pilot.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create pilot", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Pilot pilot) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "UPDATE pilots SET name = ?, license_number = ?, flight_hours = ?, certification = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, pilot.getPilotName());
            preparedStatement.setString(2, pilot.getLicenseNumber());
            preparedStatement.setInt(3, pilot.getFlightHours());
            preparedStatement.setString(4, pilot.getCertification());
            preparedStatement.setLong(5, pilot.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update pilot", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "DELETE FROM pilots WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete pilot", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Pilot> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT id, name, license_number, flight_hours, certification FROM pilots WHERE id = ?";
        Optional<Pilot> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Pilot pilot = new Pilot();
                pilot.setId(resultSet.getLong("id"));
                pilot.setPilotName(resultSet.getString("name"));
                pilot.setLicenseNumber(resultSet.getString("license_number"));
                pilot.setFlightHours(resultSet.getInt("flight_hours"));
                pilot.setCertification(resultSet.getString("certification"));
                result = Optional.of(pilot);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find pilot with id " + id, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public List<Pilot> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT id, name, license_number, flight_hours, certification FROM pilots";
        List<Pilot> pilots = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Pilot pilot = new Pilot();
                pilot.setId(resultSet.getLong("id"));
                pilot.setPilotName(resultSet.getString("name"));
                pilot.setLicenseNumber(resultSet.getString("license_number"));
                pilot.setFlightHours(resultSet.getInt("flight_hours"));
                pilot.setCertification(resultSet.getString("certification"));
                pilots.add(pilot);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find pilots", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return pilots;
    }
}

