package model;

public class Runway {
    private int id;
    private String code;
    private boolean isAvailable;
    private String lastMaintenance;

    public Runway(int id, String code, boolean isAvailable, String lastMaintenance) {
        this.id = id;
        this.code = code;
        this.isAvailable = isAvailable;
        this.lastMaintenance = lastMaintenance;
    }

    public int getId() { return id; }
    public String getCode() { return code; }
    public boolean isAvailable() { return isAvailable; }
    public String getLastMaintenance() { return lastMaintenance; }
}