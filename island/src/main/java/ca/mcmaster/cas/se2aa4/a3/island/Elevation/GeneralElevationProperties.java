package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;

import ca.mcmaster.cas.se2aa4.a3.island.MyVertex;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//take in geometry in the constructor
//set protected fields island.getCentre (Point object)
//pass in list of polygons, set protected my centre polygon in the field
//WHEN IMPLEMENTING LAKES AND RIVERS, NOT WATER TILE
public abstract class GeneralElevationProperties implements BaseElevation{
    protected int maxElevation = 80;
    protected Point islandCentre;

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
            //SET POLYGON ELEVATIONS
            polygons.get(i).setElevation(elevationValues.get(i));
        }
    }
    public void setVertexElevation (List<MyPolygon> polygons, List <MyVertex> vertices) {

    }

    protected abstract void generateElevationProfile (IslandShape i, List <MyPolygon> polygons, List<Integer>elevationValues);


    @Override
    public void generateElevation(IslandShape i, List <MyPolygon> polygons) {
        List <Integer> elevationValues = new ArrayList<>(Collections.nCopies(polygons.size(), 0));
        generateElevationProfile(i, polygons, elevationValues);
        setPolygonElevation(polygons, elevationValues);
    }
}
