package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Runway;

public class RunwayDAO {

    public List<Runway> getAllRunways() {
        List<Runway> runways = new ArrayList<>();

        String query = "SELECT runway_id, runway_code, is_available, last_maintenance FROM runways";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Runway runway = new Runway(
                        rs.getInt("runway_id"),
                        rs.getString("runway_code"),
                        rs.getBoolean("is_available"),
                        rs.getString("last_maintenance")
                );
                runways.add(runway);
            }

        } catch (SQLException e) {
            System.out.println("Runway verileri alınamadı: " + e.getMessage());
        }

        return runways;
    }
}