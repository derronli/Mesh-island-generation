package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;

import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import org.locationtech.jts.geom.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class GeneralElevationProperties implements BaseElevation{
    protected int maxElevation = 80;
    protected Point islandCentre;

    protected Random rand;

    public GeneralElevationProperties (Random rand){
        this.rand = rand;
    }

    protected Point getIslandCentre (IslandShape island){
        islandCentre = island.getCenter();
        return islandCentre;
    }

    protected MyPolygon getMiddlePolygon (List <MyPolygon> polygons, IslandShape islandShape){
        for (MyPolygon polygon : polygons) {
            if (polygon.containsPoint(getIslandCentre(islandShape))) {
                return polygon;
            }
        }
        return polygons.get(0);
    }

    private void setPolygonElevation(List<MyPolygon> polygons, List<Integer> elevationValues) {
        for (int i = 0; i<polygons.size(); i++){
            polygons.get(i).setElevation(elevationValues.get(i));
        }
    }

    private Coordinate convertVertexToCoordinate (MyVertex v){
        int x = (int) v.getX();
        int y = (int) v.getY();

        return new Coordinate(x,y);
    }

    private void setVertexElevation (List<MyPolygon> polygons, List <MyVertex> vertices) {
        int sum;
        int average;
        int counter;
        for (MyVertex vertex : vertices) {
            sum = 0;
            counter = 0;
            Coordinate coord = convertVertexToCoordinate(vertex);
            for (MyPolygon polygon : polygons) {
                if (polygon.containsCoordinate(coord)) {
                    sum += polygon.getElevation();
                    counter++;
                }
            }
            average = (counter == 0) ? -1 : sum/counter;
            vertex.setElevation(average);
        }
    }
    protected abstract void generateElevationProfile (IslandShape i, List <MyPolygon> polygons, List<Integer>elevationValues);
    @Override
    public void generateElevation(IslandShape i, List <MyPolygon> polygons, List <MyVertex> vertices) {
        List <Integer> elevationValues = new ArrayList<>(Collections.nCopies(polygons.size(), 0));
        generateElevationProfile(i, polygons, elevationValues);
        setPolygonElevation(polygons, elevationValues);
        setVertexElevation(polygons, vertices);
    }
}
