package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a3.island.Whittaker.Biomes.Arctic;
import ca.mcmaster.cas.se2aa4.a3.island.Whittaker.Biomes.WarmTemperate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BiomeFactoryTest {

    @Test
    void getBiome() {
        BiomeFactory factory = new BiomeFactory();
        assertEquals(Arctic.class, factory.getBiome("arctic").getClass());
        assertEquals(WarmTemperate.class, factory.getBiome("warmtemperate").getClass());
        assertNull(factory.getBiome("a"));
        assertNull(factory.getBiome(""));
        assertNull(factory.getBiome(null));
        assertNull(factory.getBiome("arcticy"));
    }
}