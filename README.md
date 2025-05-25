# ✈️ Airport Control Tower System

A Java Swing-based desktop application that simulates a control tower interface for managing flights, runways, weather conditions, controllers, and emergency logs. The project utilizes **Java**, **JDBC**, and a **MySQL** database for full CRUD functionality and real-time data operations.

---

## 📌 Features

- **Flight Management:** Add, update, delete, and view flights.
- **Runway Assignment:** Assign available runways to waiting aircrafts with visual feedback (color-coded).
- **Real-Time Status Colors:**
  - 🟡 Yellow: Aircrafts waiting for assignment
  - 🟢 Green: Available runways
- **Weather Tracking:** Store and monitor weather conditions affecting flight schedules.
- **Emergency Logs:** Log and review emergency cases for historical reference.
- **Controller Records:** Add and manage air traffic controllers.
- **MySQL Integration:** Live connection with MySQL using JDBC connector.

---

## 📂 Project Structure

airport_control_tower/
│
├── src/
│ ├── gui/ # Java Swing GUI panels
│ │ ├── FlightsPanel.java
│ │ ├── RunwaysPanel.java
│ │ ├── WeatherPanel.java
│ │ ├── ControllersPanel.java
│ │ └── AssignmentsPanel.java
│
│ ├── dao/ # Data Access Objects for DB operations
│ │ ├── FlightDAO.java
│ │ ├── RunwayDAO.java
│ │ └── ...
│
│ ├── model/ # Plain Java classes (POJOs)
│ │ ├── Flight.java
│ │ ├── Runway.java
│ │ └── ...
│
│ └── db/
│ └── DBHandler.java # Centralized DB connection and query manager
│
├── resources/
│ └── airport.sql # SQL file for DB schema and sample data
└── README.md


---

## 🛠 Technologies Used

- Java (JDK 17+)
- Java Swing (GUI)
- JDBC Connector
- MySQL (8.0+)
- IntelliJ IDEA / Eclipse (Recommended IDE)

---

## ⚙️ Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/airport-control-tower.git
   cd airport-control-tower
   Set up the MySQL Database:

Import resources/airport.sql into your MySQL server

Update DBHandler.java with your MySQL credentials:
private static final String DB_URL = "jdbc:mysql://localhost:3306/airport_db";
private static final String DB_USER = "root";
private static final String DB_PASS = "your_password";

