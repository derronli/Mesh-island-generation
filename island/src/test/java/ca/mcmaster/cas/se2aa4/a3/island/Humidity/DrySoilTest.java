package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrySoilTest {

    @Test
    void calcMoisture() {
        SoilProfile soil = new DrySoil();
        assertEquals(0 , soil.calcMoisture(0,1));

        assertEquals(300 , soil.calcMoisture(10,5.0));

        assertEquals(15 , soil.calcMoisture(10,100));

        assertEquals(9 , soil.calcMoisture(10,150.5));
    }
}