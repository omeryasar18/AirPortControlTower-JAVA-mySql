package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // JDBC sürücüsünü bir kez yükle
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver yüklenemedi: " + e.getMessage());
        }
    }

    // Veritabanı bağlantısı döndürür
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/airport_control_tower_db?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "Besiktas3418";

        return DriverManager.getConnection(url, user, password);
    }

    // Test amaçlı bağlantı kontrolü
    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Bağlantı başarılı!");
        } catch (SQLException e) {
            System.err.println("Bağlantı hatası: " + e.getMessage());
        }
    }
}
	
	
	
	
	