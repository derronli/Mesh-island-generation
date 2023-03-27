package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a3.island.Elevation.PlainsElevation;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.VolcanoElevation;
import ca.mcmaster.cas.se2aa4.a3.island.Whittaker.Biomes.Arctic;
import ca.mcmaster.cas.se2aa4.a3.island.Whittaker.Biomes.WarmTemperate;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ElevationFactoryTest {

    @Test
    void getElevation() {
        ElevationFactory factory = new ElevationFactory(new Random());
        assertEquals(PlainsElevation.class, factory.getElevation("plains").getClass());
        assertEquals(VolcanoElevation.class, factory.getElevation("volcano").getClass());
        assertEquals(PlainsElevation.class, factory.getElevation("p").getClass());
        assertEquals(PlainsElevation.class, factory.getElevation("").getClass());
        assertEquals(PlainsElevation.class, factory.getElevation(null).getClass());
        assertEquals(PlainsElevation.class, factory.getElevation("plain").getClass());
    }
}