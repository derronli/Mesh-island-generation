package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

// Each tile contains a soil profile so it is open for extension and different tiles can have different profiles if wanted.
public abstract class SoilProfile {

    protected double moistureLevel = 0;

    public abstract void addMoisture(int moisture, int distance);


    public double getMoisture() {
        return moistureLevel;
    }
}
