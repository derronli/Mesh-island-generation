package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

public class WetSoil implements SoilProfile {
    @Override
    public int calcMoisture(int moisture, double distance) {
        return (int)(moisture * (600 / Math.sqrt(distance)));
    }
}
