package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

public class HumiditySource implements HumidityBehaviour {

    private int moisture = 0;

    // For initializing the amount of moisture
//    FreshWaterHumidity(int moisture) {
//        this.moisture = moisture;
//    }

    @Override
    public boolean isWaterSource() {
        return true;
    }

    @Override
    public int moistureProvided() {
        return moisture;
    }
}
