package com.solvd.airportsystem.persistence.impl;

import com.solvd.airportsystem.domain.Ticket;
import com.solvd.airportsystem.persistence.ConnectionPool;
import com.solvd.airportsystem.persistence.TicketRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TicketRepositoryImpl implements TicketRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Ticket ticket) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "INSERT INTO tickets (flight_id, passenger_id, identifier, price, checked_in) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, 1L);
            preparedStatement.setLong(2, 1L);
            preparedStatement.setString(3, ticket.getFlightNumber());
            preparedStatement.setBigDecimal(4, ticket.getPrice());
            preparedStatement.setBoolean(5, ticket.isCheckedIn());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                ticket.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create ticket", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Ticket ticket) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "UPDATE tickets SET price = ?, checked_in = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBigDecimal(1, ticket.getPrice());
            preparedStatement.setBoolean(2, ticket.isCheckedIn());
            preparedStatement.setLong(3, ticket.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update ticket", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "DELETE FROM tickets WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete ticket", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT id, identifier, price, checked_in FROM tickets WHERE id = ?";
        Optional<Ticket> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getLong("id"));
                ticket.setFlightNumber(resultSet.getString("identifier"));
                BigDecimal price = resultSet.getBigDecimal("price");
                if (price != null) {
                    ticket.setPrice(price);
                }
                ticket.setCheckedIn(resultSet.getBoolean("checked_in"));
                result = Optional.of(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find ticket with id " + id, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public List<Ticket> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT id, identifier, price, checked_in FROM tickets";
        List<Ticket> tickets = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getLong("id"));
                ticket.setFlightNumber(resultSet.getString("identifier"));
                BigDecimal price = resultSet.getBigDecimal("price");
                if (price != null) {
                    ticket.setPrice(price);
                }
                ticket.setCheckedIn(resultSet.getBoolean("checked_in"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find tickets", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return tickets;
    }
}

