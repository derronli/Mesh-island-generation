package ca.mcmaster.cas.se2aa4.a3.island.FreshWater;

import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;

import java.util.List;
import java.util.Random;

public class LakeGenerator {
    private final int numLakes;
    private final Random rand;

    public LakeGenerator(List<MyPolygon> myPolygons, int numLakes, Random rand) {
        this.numLakes = numLakes;
        this.rand = rand;
        generate(myPolygons);
    }
    private void generate(List<MyPolygon> myPolygons) {
        int count = 0;
        int index;
        MyPolygon p;

        while (count < numLakes) {
            index = rand.nextInt(myPolygons.size());
            p = myPolygons.get(index);
            count += (p.tryChangeTileToLake()) ? 1 : 0;
        }
    }
}
