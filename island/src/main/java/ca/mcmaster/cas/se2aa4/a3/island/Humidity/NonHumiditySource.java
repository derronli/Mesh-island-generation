package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

public class NonHumiditySource implements HumidityBehaviour{

    // maybe can have a moistureValue field here (ie moisture from other watersources)

    @Override
    public boolean isWaterSource() {
        return false;
    }

    @Override
    public int moistureProvided() {
        return -1;
    }
}
