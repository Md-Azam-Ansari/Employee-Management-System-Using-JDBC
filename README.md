# Employee-Management-System-Using JDBC

This is a simple Java-based Employee Management System that allows you to manage employee records within a company. It uses a MySQL database for data storage and retrieval. The system is implemented in Java, making use of JDBC for database connectivity.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Concepts Covered](#concepts-covered)
- [Usage](#usage)

## Features

- **Add Employees:** Easily add new employees to the company database.
- **Count Employees:** Get the total count of employees currently employed.
- **Search by ID**: Search for an employee by their ID.
- **Search by Designation**: Search for employees based on their designation.
- **Remove Employee**: Remove an employee record based on their ID.
- **Update Employee**: Update employee details, including their name, salary, designation, and phone number, based on their ID.
- **View All Employee Details:** Display a complete list of all employees.
- **Sort Employees:** Sort and display employee details based on various criteria in ascending or descending order.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- MySQL Database
- MySQL Connector/J (JDBC Driver)

### Installation

1. Clone the repository:

   ```sh
   git clone https://github.com/Md-Azam-Ansari/employee-management-system.git
   ```

2. Compile the Java files:

   ```sh
   javac Company.java CompanyApp.java AppLauncher.java
   ```

3. Run the application:

   ```sh
   java AppLauncher
   ```

## Concepts Covered

- **Database Connectivity:** The code connects to a MySQL database using JDBC.
- **CRUD Operations:** The code performs Create, Read, Update, and Delete operations on an employee database.
- **SQL Queries:** SQL queries are used to interact with the database.
- **User Input:** The program accepts user input to add, search, update, or delete employee records.
- **Menu System:** The code provides a menu-driven system to navigate through different options.
- **Error Handling:** Exception handling is used to manage database-related exceptions.
- **Looping:** The application runs in a loop until the user chooses to exit.
- **Database Operations:** The code performs various operations like adding, searching, updating, and deleting records in a database.
- **Object-Oriented Programming:** The code uses classes and methods to encapsulate and organize functionality.
- **Separation of Concerns:** The Company class handles database operations, while the CompanyApp class manages user interaction.

## Usage

1. Launch the application using the instructions provided in the "Getting Started" section.
2. Choose an option from the menu to perform specific employee management tasks.
