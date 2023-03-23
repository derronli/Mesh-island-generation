package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.MyVertex;
import org.locationtech.jts.geom.Geometry;

import java.util.Collections;
import java.util.Comparator;
import java.awt.geom.Point2D;

import java.util.ArrayList;
import java.util.List;

//need getters and setters of elevation in the polygon and segments classes
//need to modify vertex constructor
//create a Point and use the jts library
public class VolcanoElevation extends GeneralElevationProperties {

    private List <MyVertex> vertices;
    private List <MySegment> segments;
    private List <Boolean> markedSegments;
    private List <Boolean> markedPolygons = new ArrayList<>();
    private MyPolygon centrePolygon;
    private int count = 0;

    public VolcanoElevation(IslandShape i, List<MyPolygon> polygons) {
        super(i, polygons);
        centrePolygon = getMiddlePolygon();
        for (int j = 0; j<polygons.size(); j++){
            markedPolygons.add(false);
        }
        Collections.fill(elevationValues, 0);

    }

    private void markCentre () {
        for (int i = 0; i<polygons.size(); i++){
            if (polygons.get(i) == centrePolygon) {
                //markedPolygons.set(i, true);
                elevationValues.add(i, 100);
            }
        }
    }

    private void iterateThroughCentreIsland (){
        for (int i = 0; i<polygons.size(); i++){
            if (polygons.get(i).checkForNeighbour(centrePolygon)){
                markedPolygons.set(i, true);
            }
        }
    }

    private void setIfTrue () {
        for (int i = 0; i<markedPolygons.size(); i++){
            if (markedPolygons.get(i)) {
                elevationValues.add(i, 100 - count*3);
            }
        }
    }

    private void loopThroughUntilAllAreSet (){

    }
    @Override
    protected void generateElevationProfile() {
        markCentre();
        iterateThroughCentreIsland();
        setIfTrue();
    }

}
