package ca.mcmaster.cas.se2aa4.a3.island.Elevation;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;

import java.util.Random;


import java.util.List;

public class PlainsElevation extends GeneralElevationProperties {

    public PlainsElevation(Random rand) {
        super(rand);
    }

    @Override
    protected void generateElevationProfile(IslandShape i, List <MyPolygon> polygons, List<Integer>elevationValues) {
        for (int j = 0; j<polygons.size(); j++){
            int assignElevation = rand.nextInt(0,2);
            if (assignElevation == 0){
                int value = rand.nextInt(11, maxElevation);
                elevationValues.set(j, value);
            }
        }
    }
}
