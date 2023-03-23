package ca.mcmaster.cas.se2aa4.a3.island.Elevation;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;

import java.util.Random;


import java.util.List;

//plains can go here
//convert polygons to jts form using kyle's stuff in order to actually compare wow
public class PlainsElevation extends GeneralElevationProperties {
    private final Random rand = new Random();
    public PlainsElevation(IslandShape g, List <MyPolygon> polygons) {
        super(g, polygons);
    }
    @Override
    protected void generateElevationProfile() {
        for (int i = 0; i<polygons.size(); i++){
            int assignElevation = rand.nextInt(15);
            if (assignElevation == 0){
                int value = rand.nextInt(maxElevation);
                elevationValues.add(value);
            }
            else {
                elevationValues.add(0);
            }
        }
    }
}
