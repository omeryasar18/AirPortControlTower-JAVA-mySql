
package Gui_part;

import javax.swing.*;

import Panels.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Airport Control Tower System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);

            JTabbedPane tabbedPane = new JTabbedPane();

         
            try {
                tabbedPane.addTab("Assignments", new FlightAssignmentsPanel());
            } catch (Exception e) {
                e.printStackTrace(); // Konsola yaz
                JOptionPane.showMessageDialog(null, "Assignments sekmesi yüklenemedi: " + e.getMessage());
            }

            
            tabbedPane.addTab("Flights", new SimpleTablePanel("SELECT * FROM flights"));
            tabbedPane.addTab("Runways", new SimpleTablePanel("SELECT * FROM runways"));
            tabbedPane.addTab("Weather", new SimpleTablePanel("SELECT * FROM weather_reports"));
            tabbedPane.addTab("Emergency Logs", new SimpleTablePanel("SELECT * FROM emergency_logs"));
            tabbedPane.addTab("Controllers", new SimpleTablePanel("SELECT * FROM controllers"));

            frame.add(tabbedPane);
            frame.setVisible(true);
        });
    }
}

