package Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import DAO.ControllerDAO;
import model.Controller;

public class ControllersPanel extends JPanel {
    JTable table;
    DefaultTableModel model;

    public ControllersPanel() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel();
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        model.addColumn("Name");
        model.addColumn("Ranks");
        model.addColumn("Shift Start");
        model.addColumn("Shift End");

        ControllerDAO dao = new ControllerDAO();
        List<Controller> controllers = dao.getAllControllers();

        for (Controller c : controllers) {
            model.addRow(new Object[]{
                    c.getName(),
                    c.getRank(),
                    c.getShiftStart(),
                    c.getShiftEnd()
            });
        }
    }
}