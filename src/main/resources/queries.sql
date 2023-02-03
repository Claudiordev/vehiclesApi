#All the queries important are registered here

#Creation of database
CREATE DATABASE vehicles;

#Creation of tables
CREATE TABLE IF NOT EXISTS vehicles.engine_status (
    id int(5) AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS vehicles.fleet (
    id int(5) AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS vehicles.chassis_series (
    id int(5) AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS vehicles.country (
    id int(5) AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS vehicles.brand (
    id int(5) AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS vehicles.vehicles(
    id VARCHAR(36) NOT NULL,
    name VARCHAR(50) NULL,
    msidn VARCHAR(50) NOT NULL,
    engine_status_id int(5) NOT NULL,
    fleet_id int(5) NOT NULL,
    brand_id int(5) NOT NULL,
    country_id int(5) NOT NULL,
    chassis_number VARCHAR(17) NOT NULL UNIQUE,
    chassis_series_id int(5) NOT NULL,
    communication_status BOOLEAN,
    PRIMARY KEY (id),
    FOREIGN KEY (engine_status_id) REFERENCES engine_status(id) ON DELETE CASCADE,
    FOREIGN KEY (fleet_id) REFERENCES fleet(id) ON DELETE CASCADE,
    FOREIGN KEY (brand_id) REFERENCES brand(id) ON DELETE CASCADE,
    FOREIGN KEY (country_id) REFERENCES country(id) ON DELETE CASCADE,
    FOREIGN KEY (chassis_series_id) REFERENCES chassis_series(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS vehicles.service(
    id int(5) AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS vehicles.services_status(
    id int(5) AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS vehicles.services_vehicles(
    services_id int(5) NOT NULL,
    vehicles_id VARCHAR(36) NOT NULL,
    service_status_id int(5) NOT NULL,
    reason VARCHAR(255) NULL,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (services_id) REFERENCES service(id) ON DELETE CASCADE,
    FOREIGN KEY (vehicles_id) REFERENCES vehicles(id) ON DELETE CASCADE,
    FOREIGN KEY (service_status_id) REFERENCES services_status(id) ON DELETE CASCADE
);
