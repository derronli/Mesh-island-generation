package ca.mcmaster.cas.se2aa4.a3.island.FreshWater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HasAquiferTest {

    @Test
    void getMoisture() {
        Aquifer hasAquifer = new HasAquifer();
        assertEquals(2, hasAquifer.getMoisture());
    }
}