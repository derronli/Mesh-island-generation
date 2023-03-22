package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.MyVertex;
import org.locationtech.jts.geom.Geometry;

import java.util.Comparator;
import java.awt.geom.Point2D;

import java.util.ArrayList;
import java.util.List;

//need getters and setters of elevation in the polygon and segments classes
//need to modify vertex constructor
//create a Point and use the jts library
public class VolcanoElevation extends GeneralElevationProperties {

    private List <MyPolygon> polygons;
    private List <MyVertex> vertices;
    private List <MySegment> segments;
    private List <Boolean> markedSegments;

    public VolcanoElevation(IslandShape i, List<MyPolygon> polygons, List <MySegment> segments) {
        super(i, polygons, segments);
    }

    @Override
    public void generateElevation() {

    }

    @Override
    public void setElevation() {

    }





    private void findCentrePolygon (){
        
    }

}
