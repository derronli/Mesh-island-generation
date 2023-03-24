package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

public class WetSoil extends SoilProfile {
    @Override
    public void addMoisture(int moisture, int distance) {
        moistureLevel += moisture * (40 / Math.sqrt(distance));
    }
}
