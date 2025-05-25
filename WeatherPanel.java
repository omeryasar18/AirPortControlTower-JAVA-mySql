package Panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import DAO.WeatherReportDAO;
import model.WeatherReport;

public class WeatherPanel extends JPanel {
    JTable table;
    DefaultTableModel model;

    public WeatherPanel() {
        setLayout(new BorderLayout());
        model = new DefaultTableModel();
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        model.addColumn("Report Time");
        model.addColumn("Temperature (°C)");
        model.addColumn("Visibility (km)");
        model.addColumn("Wind Speed (km/h)");
        model.addColumn("Condition");

        WeatherReportDAO dao = new WeatherReportDAO();
        List<WeatherReport> reports = dao.getAllReports();

        for (WeatherReport w : reports) {
            model.addRow(new Object[]{
                    w.getReportTime(),
                    w.getTemperature(),
                    w.getVisibility(),
                    w.getWindSpeed(),
                    w.getCondition()
            });
        }
    }
}