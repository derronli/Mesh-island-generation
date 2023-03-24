package ca.mcmaster.cas.se2aa4.a3.island.Seed;

import java.util.Random;

public class GenerateSeed {

    private long seed;

    public Random getRandom (long seed) {
        this.seed = seed;
        return new Random(seed);
    }

    public Random getRandom () {
        Random rand = new Random();
        long seed = rand.nextLong();
        rand.setSeed(seed);
        this.seed = seed;
        return rand;
    }

    public long getSeed () {
        return this.seed;
    }
}
