package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.FlightAssignment;
import Panels.FlightAssignmentsPanel;

public class FlightAssignmentDAO {

    public List<FlightAssignment> getAllAssignments() {
        List<FlightAssignment> assignments = new ArrayList<>();

        String query = "SELECT assignment_id, flight_id, controller_id, assignment_time FROM flight_assignments";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FlightAssignment assignment = new FlightAssignment(
                        rs.getInt("assignment_id"),
                        rs.getInt("flight_id"),
                        rs.getInt("controller_id"),
                        rs.getString("assignment_time")
                );
                assignments.add(assignment);
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Daha iyi: tam hata izini gösterir
        }

        return assignments;
    }
}
