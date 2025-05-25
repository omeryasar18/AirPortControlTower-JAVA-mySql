package Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import DAO.FlightDAO;
import model.Flight;

public class FlightsPanel extends JPanel {
    JTable table;
    DefaultTableModel model;

    public FlightsPanel() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel();
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        model.addColumn("Flight Code");
        model.addColumn("Departure");
        model.addColumn("Arrival");
        model.addColumn("Departure Time");
        model.addColumn("Arrival Time");
        model.addColumn("Status");

        FlightDAO dao = new FlightDAO();
        List<Flight> flights = dao.getAllFlights();

        for (Flight f : flights) {
            model.addRow(new Object[]{
                    f.getFlightCode(),
                    f.getDepartureAirport(),
                    f.getArrivalAirport(),
                    f.getDepartureTime(),
                    f.getArrivalTime(),
                    f.getStatus()
            });
        }
    }
}