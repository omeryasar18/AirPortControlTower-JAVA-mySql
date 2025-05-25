package model;

public class EmergencyLog {
    private int id;
    private int flightId;
    private String type;
    private String description;
    private String time;

    public EmergencyLog(int id, int flightId, String type, String description, String time) {
        this.id = id;
        this.flightId = flightId;
        this.type = type;
        this.description = description;
        this.time = time;
    }

    public int getId() { return id; }
    public int getFlightId() { return flightId; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public String getTime() { return time; }
}