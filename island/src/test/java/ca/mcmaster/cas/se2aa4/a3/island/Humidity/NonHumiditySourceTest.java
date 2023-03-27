package ca.mcmaster.cas.se2aa4.a3.island.Humidity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NonHumiditySourceTest {

    @Test
    void isWaterSource() {
        assertFalse(new NonHumiditySource().isWaterSource());
    }

    @Test
    void moistureProvided() {
        assertEquals(0, new NonHumiditySource().moistureProvided());
    }
}