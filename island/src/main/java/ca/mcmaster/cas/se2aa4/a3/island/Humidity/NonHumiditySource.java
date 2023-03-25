package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

public class NonHumiditySource implements HumidityBehaviour{

    @Override
    public boolean isWaterSource() {
        return false;
    }

    @Override
    public int moistureProvided() {
        return 0;
    }
}
