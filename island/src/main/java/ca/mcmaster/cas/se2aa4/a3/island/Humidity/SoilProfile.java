package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

public abstract class SoilProfile {

    protected double moistureLevel = 0;

    public abstract void addMoisture(int moisture, int distance);


    public double getMoisture() {
        return moistureLevel;
    }
}
