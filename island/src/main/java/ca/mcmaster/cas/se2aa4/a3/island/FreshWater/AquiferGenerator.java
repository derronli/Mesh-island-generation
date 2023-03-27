package ca.mcmaster.cas.se2aa4.a3.island.FreshWater;

import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import java.util.List;
import java.util.Random;

public class AquiferGenerator {

    private final int numAquifers;
    private final Random rand;
    public AquiferGenerator(List<MyPolygon> myPolygons, int numAquifers, Random random) {
        this.numAquifers = numAquifers;
        this.rand = random;
        createAquifers(myPolygons);
    }

    private void createAquifers (List <MyPolygon> polygons){
        int counter = 0;
        int index;
        MyPolygon p;

        while (counter < numAquifers){
            index = rand.nextInt(polygons.size());
            p = polygons.get(index);
            counter += (p.setAquifer()) ? 1 : 0;
        }
    }
}