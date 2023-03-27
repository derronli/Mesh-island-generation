package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WetSoilTest {

    @Test
    void calcMoisture() {

        SoilProfile soil = new WetSoil();
        assertEquals(0 , soil.calcMoisture(0,1));

        assertEquals(800 , soil.calcMoisture(10,5.0));

        assertEquals(40 , soil.calcMoisture(10,100));

        assertEquals(26 , soil.calcMoisture(10,150.5));

    }
}