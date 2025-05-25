package model;

public class Flight {
    private int id;
    private String flightCode;
    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private String status;

    public Flight(int id, String flightCode, String departureAirport, String arrivalAirport,
                  String departureTime, String arrivalTime, String status) {
        this.id = id;
        this.flightCode = flightCode;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = status;
    }

    // Getters and Setters (kısaltılmış)
    public int getId() { return id; }
    public String getFlightCode() { return flightCode; }
    public String getDepartureAirport() { return departureAirport; }
    public String getArrivalAirport() { return arrivalAirport; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public String getStatus() { return status; }
}