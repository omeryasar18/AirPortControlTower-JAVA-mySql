package model;

public class Controller {
    private int id;
    private String name;
    private String ranks;
    private String shiftStart;
    private String shiftEnd;

    public Controller(int id, String name, String ranks, String shiftStart, String shiftEnd) {
        this.id = id;
        this.name = name;
        this.ranks = ranks;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getRank() { return ranks; }
    public String getShiftStart() { return shiftStart; }
    public String getShiftEnd() { return shiftEnd; }
}