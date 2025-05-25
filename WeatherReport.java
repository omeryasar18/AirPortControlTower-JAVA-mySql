package model;

public class WeatherReport {
    private int id;
    private String reportTime;
    private double temperature;
    private double visibility;
    private double windSpeed;
    private String condition;

    public WeatherReport(int id, String reportTime, double temperature, double visibility, double windSpeed, String condition) {
        this.id = id;
        this.reportTime = reportTime;
        this.temperature = temperature;
        this.visibility = visibility;
        this.windSpeed = windSpeed;
        this.condition = condition;
    }

    public int getId() { return id; }
    public String getReportTime() { return reportTime; }
    public double getTemperature() { return temperature; }
    public double getVisibility() { return visibility; }
    public double getWindSpeed() { return windSpeed; }
    public String getCondition() { return condition; }
}