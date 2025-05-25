package Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import DAO.RunwayDAO;
import model.Runway;

public class RunwaysPanel extends JPanel {
    JTable table;
    DefaultTableModel model;

    public RunwaysPanel() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel();
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        model.addColumn("Runway Code");
        model.addColumn("Available");
        model.addColumn("Last Maintenance");

        RunwayDAO dao = new RunwayDAO();
        List<Runway> runways = dao.getAllRunways();

        for (Runway r : runways) {
            model.addRow(new Object[]{
                    r.getCode(),
                    r.isAvailable() ? "Yes" : "No",
                    r.getLastMaintenance()
            });
        }
    }
}