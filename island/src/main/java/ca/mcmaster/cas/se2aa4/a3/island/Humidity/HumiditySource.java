package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

public class HumiditySource implements HumidityBehaviour {

    @Override
    public boolean isWaterSource() {
        return true;
    }

    // Basic humidity sources return a value of 1.
    @Override
    public int moistureProvided() {
        return 1;
    }
}
