package Panels;

import DAO.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class FlightAssignmentsPanel extends JPanel {
    public FlightAssignmentsPanel() {
        setLayout(new BorderLayout());

        // Başlangıçta tüm uçakları 'waiting' durumuna çek (geliştirme amaçlı)
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("UPDATE aircrafts SET status = 'waiting', runway_id = NULL WHERE status <> 'waiting'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JComboBox<String> towerComboBox = new JComboBox<>();
        DefaultListModel<String> aircraftListModel = new DefaultListModel<>();
        DefaultListModel<String> runwayListModel = new DefaultListModel<>();
        JList<String> aircraftList = new JList<>(aircraftListModel);
        JList<String> runwayList = new JList<>(runwayListModel);
        JButton assignButton = new JButton("Assign Aircraft to Runway");

        Map<String, Integer> aircraftMap = new HashMap<>();
        Map<String, Integer> runwayMap = new HashMap<>();

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(new JLabel("Select Tower:"), BorderLayout.NORTH);
        leftPanel.add(towerComboBox, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new JLabel("Aircrafts"), BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(aircraftList), BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(new JLabel("Runways"), BorderLayout.NORTH);
        rightPanel.add(new JScrollPane(runwayList), BorderLayout.CENTER);

        JPanel contentPanel = new JPanel(new GridLayout(1, 3));
        contentPanel.add(leftPanel);
        contentPanel.add(centerPanel);
        contentPanel.add(rightPanel);

        add(contentPanel, BorderLayout.CENTER);
        add(assignButton, BorderLayout.SOUTH);

        aircraftList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setBackground(isSelected ? Color.ORANGE : Color.YELLOW);
                return c;
            }
        });

        runwayList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setBackground(isSelected ? Color.GREEN.darker() : Color.GREEN);
                return c;
            }
        });

        assignButton.addActionListener(e -> {
            String selectedAircraft = aircraftList.getSelectedValue();
            String selectedRunway = runwayList.getSelectedValue();

            if (selectedAircraft == null || selectedRunway == null) {
                JOptionPane.showMessageDialog(this, "Please select both an aircraft and a runway.");
                return;
            }

            if (!runwayMap.containsKey(selectedRunway)) {
                JOptionPane.showMessageDialog(this, "This runway is not available right now", "Clashing", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int aircraftId = aircraftMap.get(selectedAircraft);
            int runwayId = runwayMap.get(selectedRunway);

            try (Connection conn = DBConnection.getConnection()) {
                conn.setAutoCommit(false);

                PreparedStatement updateAircraft = conn.prepareStatement(
                        "UPDATE aircrafts SET runway_id = ?, status = 'on runway' WHERE aircraft_id = ?");
                updateAircraft.setInt(1, runwayId);
                updateAircraft.setInt(2, aircraftId);
                updateAircraft.executeUpdate();

                PreparedStatement updateRunway = conn.prepareStatement(
                        "UPDATE runways SET is_available = 0 WHERE runway_id = ?");
                updateRunway.setInt(1, runwayId);
                updateRunway.executeUpdate();

                conn.commit();
                JOptionPane.showMessageDialog(this,
                        "\u2705 Assignment completed succesfully!",
                        "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                towerComboBox.setSelectedItem(towerComboBox.getSelectedItem());

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to assign aircraft.");
            }
        });

        towerComboBox.addActionListener(e -> {
            String selectedTower = (String) towerComboBox.getSelectedItem();
            if (selectedTower == null) return;

            aircraftListModel.clear();
            aircraftMap.clear();
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "SELECT aircraft_id, registration_code FROM aircrafts " +
                                 "WHERE tower_id IN (SELECT id FROM control_towers WHERE name = ?) AND status = 'waiting'")) {
                stmt.setString(1, selectedTower);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String reg = rs.getString("registration_code");
                    int id = rs.getInt("aircraft_id");
                    aircraftListModel.addElement(reg);
                    aircraftMap.put(reg, id);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            runwayListModel.clear();
            runwayMap.clear();
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                         "SELECT runway_id, runway_code FROM runways " +
                                 "WHERE tower_id IN (SELECT id FROM control_towers WHERE name = ?) AND is_available = 1")) {
                stmt.setString(1, selectedTower);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String code = rs.getString("runway_code");
                    int id = rs.getInt("runway_id");
                    runwayListModel.addElement(code);
                    runwayMap.put(code, id);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT DISTINCT name FROM control_towers WHERE name IN " +
                             "('Main Tower', 'South Tower', 'West Tower', 'East Tower', 'North Tower', 'Tower A')")) {

            towerComboBox.removeAllItems();

            ActionListener[] listeners = towerComboBox.getActionListeners();
            for (ActionListener al : listeners) {
                towerComboBox.removeActionListener(al);
            }

            while (rs.next()) {
                String name = rs.getString("name");
                towerComboBox.addItem(name);
            }

            for (ActionListener al : listeners) {
                towerComboBox.addActionListener(al);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Timer flightDepartureTimer = new Timer(15000, e -> {
            try (Connection conn = DBConnection.getConnection()) {
                conn.setAutoCommit(false);

                PreparedStatement updateAircraft = conn.prepareStatement(
                    "UPDATE aircrafts SET status = 'departed', runway_id = NULL WHERE status = 'on runway'");
                updateAircraft.executeUpdate();

                PreparedStatement updateRunway = conn.prepareStatement(
                    "UPDATE runways SET is_available = 1 WHERE is_available = 0");
                updateRunway.executeUpdate();

                conn.commit();

                SwingUtilities.invokeLater(() -> {
                    towerComboBox.setSelectedItem(towerComboBox.getSelectedItem());
                });

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        flightDepartureTimer.start();
    }
}