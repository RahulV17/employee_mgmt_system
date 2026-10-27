# Employee Management System (JDBC)

Console-based Java application for managing employees in MySQL using JDBC.

## Features
- Add / view / search / update / delete employees
- View employees department-wise using SQL JOINs
- Add / view departments

## Tech
- Java 17, JDBC, MySQL 8
- Maven

## Setup
1. Run `mysql -u root -p < src/main/resources/employee_setup.sql`
2. Copy `src/main/resources/db.properties.example` to `src/main/resources/db.properties` and fill in your DB credentials.
3. Build: `mvn compile`
4. Run: `mvn exec:java -Dexec.mainClass=employee_management.EmployeeApp`
   or `java -cp target/classes;mysql-connector-j-8.0.33.jar employee_management.EmployeeApp`

## Architecture
- `model/Employee` — entity
- `dao/EmployeeDAO` — SQL operations (PreparedStatement/Statement)
- `service/EmployeeService` — business logic + error handling
- `util/DBConnection` — connection factory (reads db.properties)
- `EmployeeApp` — menu / main

## Package layout
```
employee_management/
├── EmployeeApp.java
├── model/Employee.java
├── dao/EmployeeDAO.java
├── service/EmployeeService.java
└── util/DBConnection.java
```