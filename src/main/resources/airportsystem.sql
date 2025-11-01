use airportschema;

SET SQL_SAFE_UPDATES = 0;

INSERT INTO airports (name, location, international) VALUES
('Tbilisi International Airport', 'Tbilisi, Georgia', 1),
('JFK International Airport', 'New York, USA', 1),
('London Heathrow Airport', 'London, UK', 1),
('Dubai International Airport', 'Dubai, UAE', 1),
('Tokyo Narita Airport', 'Tokyo, Japan', 1);

INSERT INTO airlines (name, code) VALUES
('Georgian Airways', 'GQ'),
('American Airlines', 'AA'),
('British Airways', 'BA');

INSERT INTO aircraft_types (name, manufacturer, description) VALUES
('Boeing 737', 'Boeing', 'Medium-range narrow-body aircraft'),
('Airbus A320', 'Airbus', 'Short-to-medium-range aircraft'),
('Boeing 787', 'Boeing', 'Long-range wide-body airliner'),
('Embraer E190', 'Embraer', 'Regional jet');

INSERT INTO aircraft (type, type_id, capacity) VALUES
('Boeing 737', 1, 180),
('Boeing 737', 1, 160),
('Airbus A320', 2, 150),
('Boeing 787', 3, 300),
('Embraer E190', 4, 100);

INSERT INTO terminals (airport_id, name) VALUES
(1, 'Terminal 1'),
(1, 'Terminal 2'),
(2, 'Terminal 4'),
(2, 'Terminal 5'),
(3, 'Terminal 5');

INSERT INTO gates (number, type, available, terminal_id) VALUES
('A12', 'International', 1, 1),
('B05', 'Domestic', 0, 1),
('C20', 'International', 1, 3),
('D30', 'International', 1, 4),
('E01', 'International', 0, 5),
('A15', 'International', 1, 2),
('B10', 'Domestic', 1, 2),
('C25', 'International', 1, 4);

INSERT INTO flights (airline_id, aircraft_id, gate_id, flight_number, destination, departure_date, departure_time, is_delayed) VALUES
(1, 1, 1, 'GQ100', 'Istanbul', '2025-12-01', '2025-12-01 10:00:00', 0),
(1, 2, 2, 'GQ200', 'Moscow', '2025-12-01', '2025-12-01 14:30:00', 1),
(2, 3, 3, 'AA300', 'Los Angeles', '2025-12-02', '2025-12-02 08:00:00', 0),
(2, 4, 4, 'AA400', 'Chicago', '2025-12-02', '2025-12-02 18:00:00', 0),
(3, 5, 5, 'BA500', 'London', '2025-12-03', '2025-12-03 09:15:00', 0),
(1, 1, 6, 'GQ600', 'Frankfurt', '2025-12-03', '2025-12-03 16:45:00', 1),
(2, 2, 7, 'AA700', 'Miami', '2025-12-04', '2025-12-04 11:30:00', 0),
(3, 3, 8, 'BA800', 'Paris', '2025-12-04', '2025-12-04 14:00:00', 0),
(1, 4, 1, 'GQ900', 'Dubai', '2025-12-05', '2025-12-05 07:00:00', 0),
(2, 5, 3, 'AA1000', 'Boston', '2025-12-05', '2025-12-05 20:00:00', 1);

INSERT INTO pilots (name, license_number,flight_hours, certification) VALUES
('Captain John Smith', 'PIL001', 12000, 'ATP'),
('First Officer Jane Doe', 'PIL002', 5000, 'CPL'),
('Captain Mike Johnson', 'PIL003', 15000, 'ATP'),
('First Officer Sarah Brown', 'PIL004', 3500, 'CPL'),
('Captain Alex Williams', 'PIL005', 18000, 'ATP');

INSERT INTO passengers (name, passport_number, birth_date) VALUES
('Alice Wonderland', 'PP1234567', '1985-05-15'),
('Bob The Builder', 'PP7654321', '1990-08-20'),
('Charlie Chaplin', 'PP9876543', '1975-12-10'),
('Diana Prince', 'PP1122334', '1988-03-25'),
('Edward Norton', 'PP5566778', '1992-07-18'),
('Fiona Green', 'PP3344556', '1987-11-30'),
('George Miller', 'PP9988776', '1983-02-14'),
('Helen Parker', 'PP4455667', '1995-09-05'),
('Ian Morgan', 'PP7788990', '1981-04-22'),
('Julia Roberts', 'PP2233445', '1989-10-08');

INSERT INTO tickets (flight_id, passenger_id, identifier, price, checked_in) VALUES
(1, 1, 'TKT001', 350.00, 0),
(1, 2, 'TKT002', 350.00, 1),
(2, 3, 'TKT003', 400.00, 0),
(3, 4, 'TKT004', 600.00, 1),
(4, 5, 'TKT005', 450.00, 0),
(5, 6, 'TKT006', 550.00, 1),
(6, 7, 'TKT007', 320.00, 0),
(7, 8, 'TKT008', 380.00, 1),
(8, 9, 'TKT009', 420.00, 0),
(9, 10, 'TKT010', 500.00, 1);

INSERT INTO aircraft_pilots (aircraft_id, pilot_id) VALUES
(1, 1), (1, 2),
(2, 3), (2, 4),
(3, 5), (3, 1),
(4, 2), (4, 3),
(5, 4), (5, 5);

INSERT INTO staff (name, role) VALUES
('Manager David', 'Airport Manager'),
('Agent Sarah', 'Check-in Agent'),
('Security Officer Mark', 'Security'),
('Ground Crew Lisa', 'Ground Crew'),
('Baggage Handler Tom', 'Baggage Handler');

INSERT INTO flight_crew (flight_id, pilot_id, staff_id) VALUES
(1, 1, 2), (2, 2, 3), (3, 3, 4),
(4, 4, 5), (5, 5, 1), (6, 1, 2),
(7, 2, 3), (8, 3, 4), (9, 4, 5),
(10, 5, 1);

update gates set available =1
where number = 'B05';

UPDATE flights 
SET is_delayed = 1 
WHERE flight_number = 'GQ100';

UPDATE passengers 
SET passport_number = 'PP9999999' 
WHERE name = 'Alice Wonderland';

UPDATE pilots 
SET flight_hours = 13000 
WHERE name = 'Captain John Smith';

UPDATE tickets 
SET checked_in = 1 
WHERE identifier = 'TKT001';

UPDATE airlines 
SET name = 'Georgian Airways Ltd' 
WHERE code = 'GQ';

UPDATE aircraft 
SET capacity = 200 
WHERE capacity = 180;

UPDATE flights 
SET destination = 'New York JFK' 
WHERE flight_number = 'AA300';

UPDATE tickets 
SET price = 375.00 
WHERE flight_id = 1 AND passenger_id = 1;

UPDATE aircraft_types 
SET description = 'Medium-range narrow-body aircraft with updated capacity' 
WHERE name = 'Boeing 737';

ALTER TABLE airports 
ADD COLUMN contact_email VARCHAR(100);

ALTER TABLE flights 
ADD INDEX idx_flight_number (flight_number);

ALTER TABLE aircraft 
MODIFY COLUMN capacity INT UNSIGNED NOT NULL;

ALTER TABLE pilots 
CHANGE hours flight_hours INT;

ALTER TABLE tickets 
ADD COLUMN seat_number VARCHAR(10) DEFAULT 'A1';

SELECT 
    ap.id AS airport_id,
    ap.name AS airport_name,
    ap.location AS airport_location,
    ap.international AS airport_international,
    t.id AS terminal_id,
    t.name AS terminal_name,
    g.id AS gate_id,
    g.number AS gate_number,
    g.type AS gate_type,
    g.available AS gate_available,
    al.id AS airline_id,
    al.name AS airline_name,
    al.code AS airline_code,
    f.id AS flight_id,
    f.flight_number,
    f.destination,
    f.departure_date,
    f.departure_time,
    f.is_delayed,
    act.id AS aircraft_id,
    act.type AS aircraft_type_name,
    act.capacity AS aircraft_capacity,
    actt.id AS aircraft_type_id,
    actt.name AS aircraft_type_model,
    actt.manufacturer,
    actt.description AS aircraft_description,
    p.id AS pilot_id,
    p.name AS pilot_name,
    p.license_number,
    p.flight_hours,
    p.certification,
    psg.id AS passenger_id,
    psg.name AS passenger_name,
    psg.passport_number,
    psg.birth_date,
    tk.id AS ticket_id,
    tk.identifier AS ticket_identifier,
    tk.price AS ticket_price,
    tk.checked_in,
    s.id AS staff_id,
    s.name AS staff_name,
    s.role AS staff_role,
    ap_junc.id AS aircraft_pilot_junction_id,
    fc.id AS flight_crew_id
FROM airports ap
LEFT JOIN terminals t ON t.airport_id = ap.id
LEFT JOIN gates g ON g.terminal_id = t.id
LEFT JOIN flights f ON f.gate_id = g.id
LEFT JOIN airlines al ON f.airline_id = al.id
LEFT JOIN aircraft act ON f.aircraft_id = act.id
LEFT JOIN aircraft_types actt ON act.type_id = actt.id
LEFT JOIN aircraft_pilots ap_junc ON ap_junc.aircraft_id = act.id
LEFT JOIN pilots p ON ap_junc.pilot_id = p.id
LEFT JOIN tickets tk ON tk.flight_id = f.id
LEFT JOIN passengers psg ON tk.passenger_id = psg.id
LEFT JOIN flight_crew fc ON fc.flight_id = f.id
LEFT JOIN staff s ON fc.staff_id = s.id
ORDER BY ap.id, t.id, g.id, f.id;



SELECT f.flight_number, f.destination, al.name AS airline_name
FROM flights f
LEFT JOIN airlines al ON f.airline_id = al.id;

SELECT al.name AS airline_name, f.flight_number, f.destination
FROM flights f
RIGHT JOIN airlines al ON f.airline_id = al.id;

SELECT f.flight_number, f.destination, g.number AS gate_number, al.name AS airline_name
FROM flights f
INNER JOIN gates g ON f.gate_id = g.id
INNER JOIN airlines al ON f.airline_id = al.id;

SELECT f.flight_number, psg.name AS passenger_name
FROM flights f
LEFT JOIN tickets tk ON f.id = tk.flight_id
LEFT JOIN passengers psg ON tk.passenger_id = psg.id
UNION
SELECT f.flight_number, psg.name AS passenger_name
FROM flights f
RIGHT JOIN tickets tk ON f.id = tk.flight_id
RIGHT JOIN passengers psg ON tk.passenger_id = psg.id;

SELECT p1.name AS pilot1_name, p2.name AS pilot2_name, p1.certification
FROM pilots p1
INNER JOIN pilots p2 ON p1.certification = p2.certification
WHERE p1.id < p2.id;

SELECT al.name AS airline_name, COUNT(f.id) AS flight_count
FROM airlines al
LEFT JOIN flights f ON al.id = f.airline_id
GROUP BY al.id, al.name;

SELECT f.flight_number, AVG(tk.price) AS avg_ticket_price
FROM flights f
LEFT JOIN tickets tk ON f.id = tk.flight_id
GROUP BY f.id, f.flight_number;

SELECT f.flight_number, COUNT(DISTINCT tk.passenger_id) AS total_passengers
FROM flights f
LEFT JOIN tickets tk ON f.id = tk.flight_id
GROUP BY f.id, f.flight_number;

SELECT certification, MIN(flight_hours) AS min_hours, MAX(flight_hours) AS max_hours
FROM pilots
GROUP BY certification;

SELECT al.name AS airline_name, SUM(act.capacity) AS total_capacity
FROM airlines al
LEFT JOIN flights f ON al.id = f.airline_id
LEFT JOIN aircraft act ON f.aircraft_id = act.id
GROUP BY al.id, al.name;

SELECT t.name AS terminal_name, COUNT(g.id) AS gate_count
FROM terminals t
LEFT JOIN gates g ON t.id = g.terminal_id
GROUP BY t.id, t.name;

SELECT actt.name AS aircraft_type, AVG(act.capacity) AS avg_capacity
FROM aircraft_types actt
LEFT JOIN aircraft act ON actt.id = act.type_id
GROUP BY actt.id, actt.name;

SELECT f.flight_number, SUM(tk.price) AS total_revenue
FROM flights f
LEFT JOIN tickets tk ON f.id = tk.flight_id
GROUP BY f.id, f.flight_number;

SELECT al.name AS airline_name, COUNT(f.id) AS flight_count
FROM airlines al
LEFT JOIN flights f ON al.id = f.airline_id
GROUP BY al.id, al.name
HAVING COUNT(f.id) > 2;

SELECT f.flight_number, AVG(tk.price) AS avg_ticket_price
FROM flights f
LEFT JOIN tickets tk ON f.id = tk.flight_id
GROUP BY f.id, f.flight_number
HAVING AVG(tk.price) > 400;

SELECT name, flight_hours
FROM pilots
GROUP BY id, name, flight_hours
HAVING flight_hours > (SELECT AVG(flight_hours) FROM pilots);

SELECT t.name AS terminal_name, COUNT(g.id) AS gate_count
FROM terminals t
LEFT JOIN gates g ON t.id = g.terminal_id
GROUP BY t.id, t.name
HAVING COUNT(g.id) > 1;

SELECT actt.name AS aircraft_type, SUM(act.capacity) AS total_capacity
FROM aircraft_types actt
LEFT JOIN aircraft act ON actt.id = act.type_id
GROUP BY actt.id, actt.name
HAVING SUM(act.capacity) > 400;

SELECT f.flight_number, COUNT(tk.passenger_id) AS passenger_count
FROM flights f
LEFT JOIN tickets tk ON f.id = tk.flight_id
GROUP BY f.id, f.flight_number
HAVING COUNT(tk.passenger_id) > 1;

SELECT al.name AS airline_name, SUM(tk.price) AS total_revenue
FROM airlines al
LEFT JOIN flights f ON al.id = f.airline_id
LEFT JOIN tickets tk ON f.id = tk.flight_id
GROUP BY al.id, al.name
HAVING SUM(tk.price) > 1000;

SELECT name, flight_hours, certification
FROM pilots
GROUP BY id, name, flight_hours, certification
HAVING flight_hours > 10000;


