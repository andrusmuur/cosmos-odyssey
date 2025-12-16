CREATE TABLE IF NOT EXISTS Price_lists (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    valid_until TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS Routes (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    from_planet VARCHAR(255),
    to_planet VARCHAR(255),
    distance BIGINT,
    price_list_id int,
    FOREIGN KEY (price_list_id) REFERENCES Price_lists(id)
);

CREATE TABLE IF NOT EXISTS Providers (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    company_name VARCHAR(255),
    price DECIMAL(18, 2),
    flight_start TIMESTAMP WITH TIME ZONE,
    flight_end TIMESTAMP WITH TIME ZONE,
    route_id int,
    FOREIGN KEY (route_id) REFERENCES Routes(id)
);