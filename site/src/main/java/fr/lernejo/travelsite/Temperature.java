package fr.lernejo.travelsite;

public class Temperature {
    private final String date;
    private final double temperature;

    public Temperature(String date, double temperature) {
        this.date = date;
        this.temperature = temperature;
    }

    public String getDate() {
        return date;
    }

    public double getTemperature() {
        return temperature;
    }
}
