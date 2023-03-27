package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumiditySourceTest {

    @Test
    void isWaterSource() {
        assertTrue(new HumiditySource().isWaterSource());
    }

    @Test
    void moistureProvided() {
        assertEquals(2, new HumiditySource().moistureProvided());
    }
}