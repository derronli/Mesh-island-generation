package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.MyVertex;
import java.util.Comparator;
import java.awt.geom.Point2D;

import java.util.ArrayList;
import java.util.List;

//need getters and setters of elevation in the polygon and segments classes
//need to modify vertex constructor
public class VolcanoElevation implements BaseElevation {

    private List <MyPolygon> polygons;
    private List <MyVertex> vertices;
    private List <MySegment> segments;
    private List <Boolean> markedSegments;

    @Override
    public void generateElevation() {

    }

    @Override
    public void setElevation() {

    }
    private double[] getMiddle(MySegment segment){
        return new double[]{(segment.getV1X() + segment.getV2X()) / 2, (segment.getV1Y() + segment.getV2Y()) / 2};
    }
    private MyVertex calcCentroid(){
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

        return null; //NEED TO SET THE VERTEX ASK KYLE LATER

    }

    private List<MyVertex> findAllCentroids () {
        List<MyVertex> allCentroids = new ArrayList<>();
        for (int i = 0; i<polygons.size(); i++){
            MyVertex cent = calcCentroid();
            allCentroids.add(cent);
        }
        return allCentroids;
    }

    private MyVertex midCentroid (){
        double x = 0;
        double y = 0;
        List<MyVertex> allCentroids = findAllCentroids();
        for (MyVertex allCentroid : allCentroids) {
            x += allCentroid.getX();
            y += allCentroid.getY();
        }

        x = x/allCentroids.size();
        y = y/allCentroids.size();

        MyVertex centreOfMesh = null; //NEED TO SET THE X AND Y HERE ASK LATER
        return centreOfMesh;
    }

    

    private void findCentrePolygon (){
        
    }

}
