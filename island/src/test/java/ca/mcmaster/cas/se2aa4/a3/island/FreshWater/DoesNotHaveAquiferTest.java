package ca.mcmaster.cas.se2aa4.a3.island.FreshWater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoesNotHaveAquiferTest {

    @Test
    void getMoisture() {
        Aquifer noAquifer = new DoesNotHaveAquifer();
        assertEquals(0, noAquifer.getMoisture());
    }
}