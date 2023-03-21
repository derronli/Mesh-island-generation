package ca.mcmaster.cas.se2aa4.a3.island.Elevation;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.MyVertex;
import ca.mcmaster.cas.se2aa4.a3.island.MySegment;
import org.locationtech.jts.geom.Geometry;
import java.util.Random;

import java.util.List;

//plains can go here
public class PlainsElevation extends GeneralElevationProperties {
    private Geometry geom;
    private List <MyPolygon> polygonList;
    private List <Boolean> hasElevation;
    private List <Integer> elevationValues;

    private Random rand = new Random();
    public PlainsElevation(Geometry g, List <MyPolygon> polygons) {
        super(g, polygons);
        this.geom = g;
        this.polygonList = polygons;
    }


    private void decideElevation (){
        for (int i = 0; i<polygonList.size(); i++){
            int assignElevation = rand.nextInt(15);
            if (assignElevation == 0){
                hasElevation.add(true);
            }
            else {
                hasElevation.add(false);
            }
        }
    }
    @Override
    public void generateElevation() {
        for (Boolean aBoolean : hasElevation) {
            if (aBoolean) {
                int value = rand.nextInt(maxElevation);
                elevationValues.add(value);
            } else {
                elevationValues.add(0);
            }
        }
    }

    @Override
    public void setElevation() {
        for (int i = 0; i<polygonList.size(); i++){
            //SET POLYGON ELEVATIONS 
        }
    }

}
