-- Δημιουργία βάσης
CREATE DATABASE IF NOT EXISTS employee_db;

USE employee_db;

-- ==========================
-- Πίνακας Departments
-- ==========================

CREATE TABLE IF NOT EXISTS departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

INSERT INTO departments(name)
VALUES
('HR'),
('IT'),
('Sales');

-- ==========================
-- Πίνακας Employees
-- ==========================

CREATE TABLE IF NOT EXISTS employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    age INT NOT NULL,
    department_id INT,

    CONSTRAINT fk_department
        FOREIGN KEY (department_id)
        REFERENCES departments(id)
);

-- ==========================
-- Πίνακας Users
-- ==========================

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

-- ==========================
-- Default Users
-- ==========================

INSERT INTO users (username, password, role)
VALUES
('admin', 'admin123', 'ADMIN'),
('hr', 'hr123', 'HR');