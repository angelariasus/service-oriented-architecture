# Service-Oriented Architecture (SOA) 

This project implements a **Hospital Management System** following a **modular service-oriented architecture**.  
It was developed in **Java** with **Oracle Database** as the persistence engine.

The system was designed as part of a **Information Systems Design** course, with the goal of applying principles of **modularity, separation of concerns, and object-oriented design**, producing scalable, maintainable, and robust software.

---

## Objectives

- Apply **modular service-oriented architecture** in a real-world hospital management scenario.
- Separate **data persistence, business logic, and user interface** into independent modules.
- Implement a complete hospital workflow including:  
  - **Patients management**  
  - **Doctors management**  
  - **Appointments scheduling**  
  - **Medication inventory**  
  - **Billing and invoices**  
  - **Hospitalizations**
- Strengthen understanding of **conceptual, logical, and physical models** in database-driven systems.

---

## System Architecture

The system follows a **layered modular architecture**, where each module has a clear responsibility:

- **Model** → Entities: `Paciente`, `Medico`, `Cita`, `Medicamento`, `Factura`, `Hospitalizacion`.
- **DAO** → Handles data access using JDBC and Oracle Database.
- **Services** → Implements core business rules and operations.
- **GUI** → Swing-based interfaces for each entity to **create, list, update, and delete/manage records**.
- **App (Main Menu)** → Provides a unified access point to all entity modules.

---

## Setup

### Clone the repository

```bash
git clone https://github.com/angelariasus/service-oriented-architecture
cd service-oriented-architecture
```
### Configure Oracle connection
Edit the Conexion.java file:
```java
private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
private static final String USER = "user";
private static final String PASSWORD = "pass";
```
### Build and run
Compile the project with Maven:
```bash
mvn clean compile
```
Run the main application:
```bash
mvn exec:java -Dexec.mainClass="com.system.App"
```
Use the Main Menu to navigate to each module (Patients, Doctors, Appointments, Medications, Billing, Hospitalizations).

Each module provides full CRUD operations via a GUI interface.

## Key Learning Outcomes
- Practical implementation of modular, service-oriented Java applications.
- Integration of Java Swing GUI with Oracle Database via JDBC.
- Application of CRUD operations across multiple entities in a hospital context.
- Understanding the mapping between conceptual models, relational schemas, and application logic.

Project developed by **angelariasus**


