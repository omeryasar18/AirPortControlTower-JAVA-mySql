package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.WeatherReport;

public class WeatherReportDAO {

    public List<WeatherReport> getAllReports() {
        List<WeatherReport> reports = new ArrayList<>();

        String query = "SELECT report_id, report_time, temperature, visibility, wind_speed, conditions FROM weather_reports";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                WeatherReport report = new WeatherReport(
                        rs.getInt("report_id"),
                        rs.getString("report_time"),
                        rs.getDouble("temperature"),
                        rs.getDouble("visibility"),
                        rs.getDouble("wind_speed"),
                        rs.getString("conditions")
                );
                reports.add(report);
            }

        } catch (SQLException e) {
            System.out.println("Hava durumu verileri alınamadı: " + e.getMessage());
        }

        return reports;
    }
}