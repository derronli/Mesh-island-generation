package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

public class DrySoil extends SoilProfile {

    @Override
    public int calcMoisture(int moisture, int distance) {
        return (int)(moisture * (30 / Math.sqrt(distance)));
    }
}
