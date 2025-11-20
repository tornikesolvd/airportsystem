package com.solvd.airportsystem.persistence.impl;

import com.solvd.airportsystem.domain.*;
import com.solvd.airportsystem.persistence.ConnectionPool;
import com.solvd.airportsystem.persistence.FlightRepository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class FlightRepositoryImpl implements FlightRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    @Override
    public void create(Flight flight) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "INSERT INTO flights (airline_id, aircraft_id, gate_id, flight_number, destination, departure_date, departure_time, is_delayed) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, flight.getAircraft() != null && flight.getAircraft().getId() != null ? 1L : 1L);
            preparedStatement.setLong(2, flight.getAircraft() != null && flight.getAircraft().getId() != null ? flight.getAircraft().getId() : 1L);
            preparedStatement.setLong(3, flight.getGate() != null && flight.getGate().getId() != null ? flight.getGate().getId() : 1L);
            preparedStatement.setString(4, flight.getFlightNumber());
            preparedStatement.setString(5, flight.getDestination());
            preparedStatement.setDate(6, Date.valueOf(flight.getDepartureDate()));
            preparedStatement.setTimestamp(7, Timestamp.valueOf(flight.getDepartureTime()));
            preparedStatement.setBoolean(8, flight.isDelayed());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                flight.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create flight", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Flight flight) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "UPDATE flights SET flight_number = ?, destination = ?, departure_date = ?, departure_time = ?, is_delayed = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, flight.getFlightNumber());
            preparedStatement.setString(2, flight.getDestination());
            preparedStatement.setDate(3, Date.valueOf(flight.getDepartureDate()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(flight.getDepartureTime()));
            preparedStatement.setBoolean(5, flight.isDelayed());
            preparedStatement.setLong(6, flight.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update flight", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "DELETE FROM flights WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete flight", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Flight> findById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT id, flight_number, destination, departure_date, departure_time, is_delayed FROM flights WHERE id = ?";
        Optional<Flight> result = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Flight flight = new Flight();
                flight.setId(resultSet.getLong("id"));
                flight.setFlightNumber(resultSet.getString("flight_number"));
                flight.setDestination(resultSet.getString("destination"));
                Date departureDate = resultSet.getDate("departure_date");
                if (departureDate != null) {
                    flight.setDepartureDate(departureDate.toLocalDate());
                }
                Timestamp departureTime = resultSet.getTimestamp("departure_time");
                if (departureTime != null) {
                    flight.setDepartureTime(departureTime.toLocalDateTime());
                }
                flight.setDelayed(resultSet.getBoolean("is_delayed"));
                result = Optional.of(flight);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find flight with id " + id, e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public List<Flight> findAll() {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT id, flight_number, destination, departure_date, departure_time, is_delayed FROM flights";
        List<Flight> flights = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Flight flight = new Flight();
                flight.setId(resultSet.getLong("id"));
                flight.setFlightNumber(resultSet.getString("flight_number"));
                flight.setDestination(resultSet.getString("destination"));
                Date departureDate = resultSet.getDate("departure_date");
                if (departureDate != null) {
                    flight.setDepartureDate(departureDate.toLocalDate());
                }
                Timestamp departureTime = resultSet.getTimestamp("departure_time");
                if (departureTime != null) {
                    flight.setDepartureTime(departureTime.toLocalDateTime());
                }
                flight.setDelayed(resultSet.getBoolean("is_delayed"));
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find flights", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return flights;
    }

    @Override
    public List<Flight> findAllWithAllData() {
        Connection connection = CONNECTION_POOL.getConnection();
        String sql = "SELECT " +
                "f.id AS flight_id, f.flight_number, f.destination, f.departure_date, f.departure_time, f.is_delayed, " +
                "al.id AS airline_id, al.name AS airline_name, al.code AS airline_code, " +
                "act.id AS aircraft_id, act.type AS aircraft_type, act.capacity AS aircraft_capacity, " +
                "g.id AS gate_id, g.number AS gate_number, g.type AS gate_type, g.available AS gate_available, " +
                "t.id AS terminal_id, t.name AS terminal_name, " +
                "ap.id AS airport_id, ap.name AS airport_name, ap.location AS airport_location, " +
                "psg.id AS passenger_id, psg.name AS passenger_name, psg.passport_number, psg.birth_date " +
                "FROM flights f " +
                "LEFT JOIN airlines al ON f.airline_id = al.id " +
                "LEFT JOIN aircraft act ON f.aircraft_id = act.id " +
                "LEFT JOIN gates g ON f.gate_id = g.id " +
                "LEFT JOIN terminals t ON g.terminal_id = t.id " +
                "LEFT JOIN airports ap ON t.airport_id = ap.id " +
                "LEFT JOIN tickets tk ON tk.flight_id = f.id " +
                "LEFT JOIN passengers psg ON tk.passenger_id = psg.id " +
                "ORDER BY f.id, psg.id";
        Map<Long, Flight> flightMap = new LinkedHashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long flightId = resultSet.getLong("flight_id");
                Flight flight = flightMap.get(flightId);

                if (flight == null) {
                    flight = new Flight();
                    flight.setId(flightId);
                    flight.setFlightNumber(resultSet.getString("flight_number"));
                    flight.setDestination(resultSet.getString("destination"));
                    Date departureDate = resultSet.getDate("departure_date");
                    if (departureDate != null) {
                        flight.setDepartureDate(departureDate.toLocalDate());
                    }
                    Timestamp departureTime = resultSet.getTimestamp("departure_time");
                    if (departureTime != null) {
                        flight.setDepartureTime(departureTime.toLocalDateTime());
                    }
                    flight.setDelayed(resultSet.getBoolean("is_delayed"));

                    Airline airline = new Airline();
                    airline.setId(resultSet.getLong("airline_id"));
                    airline.setAirlineName(resultSet.getString("airline_name"));
                    airline.setAirlineCode(resultSet.getString("airline_code"));

                    Aircraft aircraft = new Aircraft();
                    aircraft.setId(resultSet.getLong("aircraft_id"));
                    aircraft.setAircraftType(resultSet.getString("aircraft_type"));
                    aircraft.setCapacity(resultSet.getInt("aircraft_capacity"));
                    flight.setAircraft(aircraft);

                    Gate gate = new Gate();
                    gate.setId(resultSet.getLong("gate_id"));
                    gate.setGateNumber(resultSet.getString("gate_number"));
                    gate.setGateType(resultSet.getString("gate_type"));
                    gate.setAvailable(resultSet.getBoolean("gate_available"));
                    flight.setGate(gate);

                    flight.setPassengers(new ArrayList<>());
                    flightMap.put(flightId, flight);
                }

                Long passengerId = resultSet.getLong("passenger_id");
                if (passengerId > 0 && flight.getPassengers() != null) {
                    boolean passengerExists = flight.getPassengers().stream()
                            .anyMatch(p -> p.getId() != null && p.getId().equals(passengerId));
                    if (!passengerExists) {
                        Passenger passenger = new Passenger();
                        passenger.setId(passengerId);
                        passenger.setFullName(resultSet.getString("passenger_name"));
                        passenger.setPassportNumber(resultSet.getString("passport_number"));
                        Date birthDate = resultSet.getDate("birth_date");
                        if (birthDate != null) {
                            passenger.setBirthDate(birthDate.toLocalDate());
                        }
                        flight.getPassengers().add(passenger);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to find flights with details", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return new ArrayList<>(flightMap.values());
    }
}

