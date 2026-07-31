# Employee Management System

A console-based Employee Management System developed in Java using JDBC and MySQL. The application follows a layered architecture (Repository – Service – UI), making the code modular, maintainable, and easy to extend.

## Technologies

- Java
- JDBC
- MySQL
- SQL
- Object-Oriented Programming (OOP)
- Repository Pattern
- Layered Architecture
- Git & GitHub

---

## Features

### User Management
- User authentication (Login)
- Role-based access (Admin / HR)

### Employee Management
- Add employee
- View all employees
- Search employee by ID
- Update employee salary
- Delete employee

### Department Management
- View all departments
- Assign employees to departments
- Store department information using foreign keys
- One-to-many relationship between Departments and Employees

### Validation
- Email validation
- Name validation
- Salary validation
- Age validation

### Exception Handling
- Custom exceptions
- EmployeeNotFoundException
- InvalidEmailException
- InvalidAgeException
- InvalidSalaryException
- InvalidNameException

---

## Database

The application uses a MySQL relational database.

### Tables

- employees
- departments
- users

### Database Relationships

- One Department → Many Employees (1:N)
- Many Employees → One Department (N:1)

The relationship is implemented using a foreign key:

```sql
department_id INT,
FOREIGN KEY (department_id) REFERENCES departments(id)
```

---

## Database Setup

1. Start MySQL (e.g. using XAMPP).
2. Execute the `employee_db.sql` script.
3. Update the database credentials inside `DatabaseConnection.java` if necessary.
4. Run the application.

---

## Default Login

| Username | Password | Role |
|----------|----------|------|
| admin | admin123 | ADMIN |
| hr | hr123 | HR |

---

## Project Structure

```
src
│
├── database
│   ├── DatabaseConnection
│   ├── JdbcEmployeeRepository
│   ├── JdbcDepartmentRepository
│   └── JdbcUserRepository
│
├── model
│   ├── Employee
│   ├── Department
│   └── User
│
├── repository
│   ├── EmployeeRepository
│   ├── DepartmentRepository
│   └── UserRepository
│
├── service
│   ├── EmployeeService
│   ├── DepartmentService
│   ├── UserService
│   └── ValidationService
│
├── ui
│   ├── LoginMenu
│   └── ConsoleMenu
│
└── exception
    ├── EmployeeNotFoundException
    ├── InvalidEmailException
    ├── InvalidAgeException
    ├── InvalidSalaryException
    └── InvalidNameException
```

---

## Design Patterns

This project follows the Repository Pattern.

The application is divided into three main layers:

- **UI Layer** – Handles user interaction.
- **Service Layer** – Contains the business logic.
- **Repository Layer** – Communicates with the database using JDBC.

This separation improves maintainability, scalability, and code readability.

---

## Skills Demonstrated

- Java
- JDBC
- MySQL
- SQL CRUD Operations
- Object-Oriented Programming
- Repository Pattern
- Layered Architecture
- Exception Handling
- Input Validation
- Git & GitHub
- Relational Database Design
- Foreign Keys & Table Relationships

---
