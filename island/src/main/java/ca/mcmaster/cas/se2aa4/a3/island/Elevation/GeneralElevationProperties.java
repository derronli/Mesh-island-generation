package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;

import ca.mcmaster.cas.se2aa4.a3.island.MyVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Seed.GenerateSeed;
import org.locationtech.jts.geom.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//take in geometry in the constructor
//set protected fields island.getCentre (Point object)
//pass in list of polygons, set protected my centre polygon in the field
//WHEN IMPLEMENTING LAKES AND RIVERS, NOT WATER TILE
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
            //SET POLYGON ELEVATIONS
            polygons.get(i).setElevation(elevationValues.get(i));
        }
    }

    private Point convertVertexToPoint (MyVertex v){
        GeometryFactory geom = new GeometryFactory();
        double x = v.getX();
        double y = v.getY();

        Coordinate bruh = new Coordinate(x,y);

        Point goodPoint = new Point((CoordinateSequence) bruh, geom);
        return goodPoint;
    }
    public void setVertexElevation (List<MyPolygon> polygons, List <MyVertex> vertices) {
        for (MyVertex vertex : vertices) {
            Point check = convertVertexToPoint(vertex);
            for (int j = 0; j < polygons.size(); j++) {
                for (MyPolygon polygon : polygons) {
                    if ((polygons.get(j).containsPoint(check) && polygon.containsPoint(check)) && polygons.get(j) != polygon) {
                        int elevationOne = polygons.get(j).getElevation();
                        int elevationTwo = polygon.getElevation();
                        float average = (elevationOne + elevationTwo) / 2;
                        //set vertex here to average elevation
                    }
                }
            }
        }
    }

    protected abstract void generateElevationProfile (IslandShape i, List <MyPolygon> polygons, List<Integer>elevationValues);


    @Override
    public void generateElevation(IslandShape i, List <MyPolygon> polygons) {
        List <Integer> elevationValues = new ArrayList<>(Collections.nCopies(polygons.size(), 0));
        generateElevationProfile(i, polygons, elevationValues);
        setPolygonElevation(polygons, elevationValues);
    }
}
