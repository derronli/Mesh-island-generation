package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

// Each tile contains a soil profile so it is open for extension and different tiles can have different profiles if wanted.
public interface SoilProfile {

    int calcMoisture(int moisture, double distance);

}
