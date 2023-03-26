package ca.mcmaster.cas.se2aa4.a3.island.FreshWater;

import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import java.util.List;
import java.util.Random;

/*
go through input list of polygons (will input only polygons in island)
method should take that list as input and nothing else
randomly choose polygons from list to add aquifers to up to user input number of times (set as field for now)
will have a method when choosing a polygon to call polygon.hasAquifer or something to ensure aquifer has not been added already and then regen index
polygon will have a method polygon.addAquifer which takes in no input, and that's all that needs to be called (polygon will handle moisture on its own)

also make when this generator is made, takes in random as parameter in constructor and sets field to it
*/

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