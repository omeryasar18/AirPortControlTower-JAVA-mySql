package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.EmergencyLog;

public class EmergencyLogDAO {

    public List<EmergencyLog> getAllLogs() {
        List<EmergencyLog> logs = new ArrayList<>();

        String query = "SELECT emergency_id, flight_id, emergency_type, description, emergency_time FROM emergency_logs";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmergencyLog log = new EmergencyLog(
                        rs.getInt("emergency_id"),
                        rs.getInt("flight_id"),
                        rs.getString("emergency_type"),
                        rs.getString("description"),
                        rs.getString("emergency_time")
                );
                logs.add(log);
            }

        } catch (SQLException e) {
            System.out.println("Acil durum verileri alınamadı: " + e.getMessage());
        }

        return logs;
    }
}