package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ControlTower;

public class ControlTowerDAO {
	List<ControlTower> towers = new ArrayList<>();

	public List<ControlTower> getAllTowers() throws SQLException {
		String sql = "SELECT * FROM control_towers";

		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				ControlTower tower = new ControlTower();
				tower.setId(rs.getInt("id"));
				tower.setName(rs.getString("name"));
				tower.setLocation(rs.getString("location"));
				towers.add(tower);
			}
		}

		return towers;
	}
}
