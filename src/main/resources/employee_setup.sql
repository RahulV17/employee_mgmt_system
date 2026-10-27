-- Employee Management System - Database Setup Script
-- Run: mysql -u root -p < employee_setup.sql

CREATE DATABASE IF NOT EXISTS employee_db;
USE employee_db;

CREATE TABLE IF NOT EXISTS departments (
    dept_id   INT PRIMARY KEY AUTO_INCREMENT,
    dept_name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS employees (
    emp_id   INT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(100),
    salary   DOUBLE,
    dept_id  INT,
    FOREIGN KEY (dept_id) REFERENCES departments(dept_id)
);

-- (No sample data inserted; tables start empty.)
