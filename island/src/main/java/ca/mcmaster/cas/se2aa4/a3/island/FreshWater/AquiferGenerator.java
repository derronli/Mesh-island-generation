package ca.mcmaster.cas.se2aa4.a3.island.FreshWater;

import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.LakeTile;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.LakeTile;

import java.util.List;
import java.util.Random;

public class AquiferGenerator {

    private final int numAquifers;
    private final Random rand = new Random();

    public AquiferGenerator(List<MyPolygon> myPolygons, int numLakes) {
        this.numAquifers = numLakes;
        generate(myPolygons);
    }
    private void generate(List<MyPolygon> myPolygons) {
        int count = 0;
        int index;
        MyPolygon p;

        while (count < numAquifers) {
            index = rand.nextInt(myPolygons.size());
            p = myPolygons.get(index);
            count += (p.attemptChange(new LakeTile())) ? 1 : 0;
        }
    }

}
