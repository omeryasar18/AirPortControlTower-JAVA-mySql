package Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import DAO.EmergencyLogDAO;
import model.EmergencyLog;

public class EmergencyLogsPanel extends JPanel {
    JTable table;
    DefaultTableModel model;

    public EmergencyLogsPanel() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel();
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        model.addColumn("Flight ID");
        model.addColumn("Emergency Type");
        model.addColumn("Description");
        model.addColumn("Time");

        EmergencyLogDAO dao = new EmergencyLogDAO();
        List<EmergencyLog> logs = dao.getAllLogs();

        for (EmergencyLog log : logs) {
            model.addRow(new Object[]{
                    log.getFlightId(),
                    log.getType(),
                    log.getDescription(),
                    log.getTime()
            });
        }
    }
}