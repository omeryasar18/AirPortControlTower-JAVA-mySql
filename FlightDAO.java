	package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Flight;

public class FlightDAO {

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();

        String query = "SELECT flight_id, flightcode, departure_airport, arrival_airport, departure_time, arrival_time, status FROM flights";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Flight flight = new Flight(
                        rs.getInt("flight_id"),
                        rs.getString("flightcode"),
                        rs.getString("departure_airport"),
                        rs.getString("arrival_airport"),
                        rs.getString("departure_time"),
                        rs.getString("arrival_time"),
                        rs.getString("status")
                );
                flights.add(flight);
            }

        } catch (SQLException e) {
            System.out.println("Veritabanından uçuş verileri alınamadı: " + e.getMessage());
        }

        return flights;
    }
}