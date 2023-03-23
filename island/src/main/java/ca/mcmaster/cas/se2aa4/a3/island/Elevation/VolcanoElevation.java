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
    private MyPolygon centrePolygon;
    private int levelsOfElevationDecrease = 0;
    private final int elevationDecreaseFactor = 10;
    private void markCentre (List<MyPolygon>polygons, List<Integer> elevationValues) {
        for (int i = 0; i<polygons.size(); i++){
            if (polygons.get(i) == centrePolygon) {
                markedPolygons.set(i, true);
                elevationValues.add(i, maxElevation);
            }
        }
    }
    private void iterateThroughCentreIsland (List<MyPolygon>polygons){
        for (int i = 0; i<polygons.size(); i++){
            if (polygons.get(i).checkForNeighbour(centrePolygon)){
                markedPolygons.set(i, true);
            }
        }
    }
    private void setIfTrue (List<Integer>elevationValues) {
        levelsOfElevationDecrease++;
        for (int i = 0; i<markedPolygons.size(); i++){
            if (markedPolygons.get(i)) {
                elevationValues.set(i, maxElevation - levelsOfElevationDecrease * elevationDecreaseFactor);
            }
        }
    }
    private void iterateThroughUntilAllAreSet (List<MyPolygon>polygons, List <Integer> elevationValues){
        levelsOfElevationDecrease++;
        for (int i = 0; i<markedPolygons.size(); i++){
            if (markedPolygons.get(i)){
                for (int j = 0; j<polygons.size(); j++){
                    if (polygons.get(i).checkForNeighbour(polygons.get(j)) && elevationValues.get(j) == 0){
                        elevationValues.set(j, maxElevation - levelsOfElevationDecrease * elevationDecreaseFactor);
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
    protected void generateElevationProfile(IslandShape i, List <MyPolygon> polygons, List<Integer>elevationValues) {
        centrePolygon = getMiddlePolygon(polygons, i);
        Collections.fill(markedPolygons, false);

        boolean check;
        markCentre(polygons, elevationValues);
        iterateThroughCentreIsland(polygons);
        setIfTrue(elevationValues);
        do {
            iterateThroughUntilAllAreSet(polygons, elevationValues);
            check = checkIfAllFalse();
        } while (!check);
        for (int k = 0; k<elevationValues.size(); k++){
            System.out.println("elevation:" + k);
        }
    }
}