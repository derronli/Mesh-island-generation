package ca.mcmaster.cas.se2aa4.a3.island.Elevation;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;

import java.util.Random;


import java.util.List;

//plains can go here
//convert polygons to jts form using kyle's stuff in order to actually compare wow
public class PlainsElevation extends GeneralElevationProperties {
    private final Random rand = new Random();
    @Override
    protected void generateElevationProfile(IslandShape i, List <MyPolygon> polygons, List<Integer>elevationValues) {
        for (int j = 0; j<polygons.size(); j++){
            int assignElevation = rand.nextInt(0,2);
            if (assignElevation == 0){
                int value = rand.nextInt(11, maxElevation);
                elevationValues.set(j, value);
            //    System.out.println(value);
            }
        }
    }
}
