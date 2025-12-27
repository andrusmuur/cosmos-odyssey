CREATE TABLE IF NOT EXISTS Price_lists (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    valid_until TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS Routes (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    from_planet VARCHAR(255),
    to_planet VARCHAR(255),
    distance BIGINT,
    price_list_id INT,
    FOREIGN KEY (price_list_id) REFERENCES Price_lists(id)
);

CREATE TABLE IF NOT EXISTS Providers (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    company_name VARCHAR(255),
    price DECIMAL(18, 2),
    flight_start TIMESTAMP WITH TIME ZONE,
    flight_end TIMESTAMP WITH TIME ZONE,
    route_id INT,
    FOREIGN KEY (route_id) REFERENCES Routes(id)
);

CREATE TABLE IF NOT EXISTS Reservations (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    total_price DECIMAL(18, 2),
    total_travel_time_in_seconds INT
);

CREATE TABLE IF NOT EXISTS Reservation_routes (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    route_id INT,
    reservation_id INT,
    FOREIGN KEY (route_id) REFERENCES Routes(id),
    FOREIGN KEY (reservation_id) REFERENCES Reservations(id)
);

CREATE TABLE IF NOT EXISTS Reservation_companies (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    company_name VARCHAR(255),
    reservation_id INT,
    FOREIGN KEY (reservation_id) REFERENCES Reservations(id)
);