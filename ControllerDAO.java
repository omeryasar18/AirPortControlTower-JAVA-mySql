package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Controller;
import storage.storage;

public class ControllerDAO {
	List<Controller> controllers = new ArrayList<>();

	public List<Controller> getAllControllers() {

		String query = "SELECT controller_id, name, ranks, shift_start, shift_end FROM controllers";

		try (Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Controller controller = new Controller(rs.getInt("controller_id"), rs.getString("name"),
						rs.getString("ranks"), rs.getString("shift_start"), rs.getString("shift_end"));
				controllers.add(controller);
			}

		} catch (SQLException e) {
			System.out.println("Kontrolör verileri alınamadı: " + e.getMessage());
		}

		return controllers;
	}
}