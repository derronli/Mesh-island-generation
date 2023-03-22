package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.MyVertex;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.Coordinate;

import java.util.ArrayList;
import java.util.List;

//take in geometry in the constructor
//set protected fields island.getCentre (Point object)
//pass in list of polygons, set protected my centre polygon in the field
//WHEN IMPLEMENTING LAKES AND RIVERS, NOT WATER TILE
public abstract class GeneralElevationProperties implements  BaseElevation{

    private IslandShape island;
    private List <MyPolygon> polygons;
    private List <MySegment> segments;

    private List <Coordinate> allCentroids;

    protected int maxElevation = 100;

    protected Point islandCentre;
    public GeneralElevationProperties (IslandShape i, List <MyPolygon> polygons){
        this.island = i;
        this.polygons = polygons;
    }

    private double[] getMiddle(MySegment segment){
        return new double[]{(segment.getV1X() + segment.getV2X()) / 2, (segment.getV1Y() + segment.getV2Y()) / 2};
    }
    private Coordinate calcCentroid(){
        double midX = 0;
        double midY = 0;
        double [] middle;
        for (MySegment segment : segments) {
            middle = getMiddle(segment);
            midX += middle[0];
            midY += middle[1];
        }
        double x = midX/segments.size();
        double y = midY/segments.size();
        Coordinate temp = new Coordinate();
        temp.setX(x);
        temp.setY(y);
        return temp; //NEED TO SET THE VERTEX ASK KYLE LATER

    }

    private List<Coordinate> findAllCentroids () {
        List<Coordinate> allCentroids = new ArrayList<>();
        for (int i = 0; i<polygons.size(); i++){
            Coordinate cent = calcCentroid();
            allCentroids.add(cent);
        }
        return allCentroids;
    }

    protected void getIslandCentre (){
       islandCentre = island.getCenter();
    }

    protected MyPolygon getMiddlePolygon (){
        for (MyPolygon polygon : polygons) {
            if (polygon.containsPoint(islandCentre)) {
                return polygon;
            }
        }
        return null; //CHECK LATER IF THIS IS FINE
    }

    protected List<MyPolygon> checkPolygonsWithinIsland (){
        List<MyPolygon> withinIsland = new ArrayList<>();
        for (MyPolygon polygon : polygons) {
            if (!polygon.isWaterTile()) {
                withinIsland.add(polygon);
            }
        }
        return withinIsland;
    }


}
