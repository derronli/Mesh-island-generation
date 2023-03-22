package ca.mcmaster.cas.se2aa4.a3.island.FreshWater;

import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.LakeTile;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;

import java.util.List;
import java.util.Random;

public class LakeGenerator {
    private final int numLakes;
    private final Random rand = new Random();

    public LakeGenerator(List<MyPolygon> myPolygons, int numLakes) {
        this.numLakes = numLakes;
        generate(myPolygons);
    }
    private void generate(List<MyPolygon> myPolygons) {
        int count = 0;
        int index;
        MyPolygon p;

        while (count < numLakes) {
            index = rand.nextInt(myPolygons.size());
            p = myPolygons.get(index);
            count += (p.attemptChange(new LakeTile())) ? 1 : 0;
        }
    }
    // Args specify loop bounds
    // while (args)
    // Get Random Number within bounds of the myPolygons array list length (access random index)
    // Check if the polygon is valid (is it land?) -> set to lake "type" + save this polygon into a lake's array?
    // decrement args counter if it is valid, otherwise continue
}
