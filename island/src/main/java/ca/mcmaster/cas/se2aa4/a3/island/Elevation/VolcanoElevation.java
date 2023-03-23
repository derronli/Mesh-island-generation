package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

//need getters and setters of elevation in the polygon and segments classes
//need to modify vertex constructor
//create a Point and use the jts library
public class VolcanoElevation extends GeneralElevationProperties {
    private final List <Boolean> markedPolygons = new ArrayList<>();
    private final MyPolygon centrePolygon;
    private int count = 0;
    public VolcanoElevation(IslandShape i, List<MyPolygon> polygons) {
        super(i, polygons);
        centrePolygon = getMiddlePolygon();
        Collections.fill(elevationValues, 0);
        Collections.fill(markedPolygons, false);
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
        count++;
        for (int i = 0; i<markedPolygons.size(); i++){
            if (markedPolygons.get(i)) {
                elevationValues.set(i, 100 - count*3);
            }
        }
    }
    private void iterateThroughUntilAllAreSet (){
        count++;
        for (int i = 0; i<markedPolygons.size(); i++){
            if (markedPolygons.get(i)){
                for (int j = 0; j<polygons.size(); j++){
                    if (polygons.get(i).checkForNeighbour(polygons.get(j)) && elevationValues.get(j) == 0){
                        elevationValues.set(j, 100 - count*3);
                        markedPolygons.set(j, true);
                    }
                }
            }
        }
    }
    private boolean checkIfAllFalse (){
        for (boolean markedPolygon : markedPolygons) {
            if (!markedPolygon) {
                return false;
            }
        }
        return true;
    }
    @Override
    protected void generateElevationProfile() {
        boolean check;
        markCentre();
        iterateThroughCentreIsland();
        setIfTrue();
        do {
            iterateThroughUntilAllAreSet();
            check = checkIfAllFalse();
        } while (!check);
    }
}