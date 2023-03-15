package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.Tiles.LandTile;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.OceanTile;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.Tile;
import org.locationtech.jts.geom.*;

public class MyPolygon implements MyShape{

    private static int totalIndex = 0;
    private final int index;
    private Polygon polygon;
    private org.locationtech.jts.geom.Polygon jtsPolygon;
    private Tile myTile;
    private List <MySegment> segments = new ArrayList<>();
    private Coordinate[] coordinates;

    public MyPolygon(Polygon p){
        polygon = p;
        index = totalIndex;
        totalIndex++;
    }

    // Orders the segments and sets the vertices list.
    private void orderSegments (){
        List <MySegment> orderedSegments = new ArrayList<>();
        MySegment firstSeg = segments.get(0);
        orderedSegments.add(firstSeg);

        coordinates = new Coordinate[segments.size()];
        int coordCounter = 0;

        Coordinate c1 = new Coordinate(firstSeg.getV1X(), firstSeg.getV1Y());
        Coordinate c2 = new Coordinate(firstSeg.getV2X(), firstSeg.getV2Y());
        coordinates[coordCounter] = c1; coordCounter++;
        coordinates[coordCounter] = c2; coordCounter++;

        // Orders segments and adds vertices in order to vertices list.
        for (int count = 0; count < segments.size(); count++){
            for (MySegment segment : segments) {
                if (!orderedSegments.contains(segment)) {
                    MySegment last = orderedSegments.get(orderedSegments.size() - 1);
                    int commonVertex = last.isAdjacent(segment);
                    if (commonVertex != 0) {

                        // If we are adding the second segment, checks the vertices on both to ensure proper ordering.
                        if (orderedSegments.size() == 1){
                            // First adds the vertex not in common, then the vertex in common.
                            Coordinate firstCoord = (commonVertex == last.getV1Index()) ? new Coordinate(last.getV2X(), last.getV2Y()) : new Coordinate(last.getV1X(), last.getV1Y());
                            Coordinate secondCoord = (commonVertex == last.getV1Index()) ? new Coordinate(last.getV1X(), last.getV1Y()) : new Coordinate(last.getV2X(), last.getV2Y());
                            coordinates[coordCounter] = firstCoord; coordCounter++;
                            coordinates[coordCounter] = secondCoord; coordCounter++;
                        }

                        // Next adds the vertex not already in the list which is in the new segment.
                        commonVertex = segment.isAdjacent(last);
                        Coordinate newCoord = (commonVertex == segment.getV1Index()) ? new Coordinate(segment.getV2X(), segment.getV2Y()) : new Coordinate(segment.getV1X(), segment.getV1Y());
                        if (coordCounter < coordinates.length) {
                            coordinates[coordCounter] = newCoord;
                            coordCounter++;
                        }

                        orderedSegments.add(segment);
                    }
                }
            }
        }
        segments = orderedSegments;
    }

    // Creates a new JTS polygon based on the current segments.
    private void setJTSPoly(){
        jtsPolygon = new GeometryFactory().createPolygon(coordinates);
    }


    public void changeColor(String colorCode){
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        polygon = Polygon.newBuilder(polygon).setProperties(0, color).build();
    }

    // Adds segment to segment list.
    public void addSegment(MySegment s){
        segments.add(s);
        orderSegments();
        setJTSPoly();
    }

    // Getters
    public int getIndex(){ return index; }
    public Polygon getPolygon() {
        return polygon;
    }
    public org.locationtech.jts.geom.Polygon getJTSPolygon() {
        return jtsPolygon;
    }
    public List<Integer> getSegmentIdxsList(){ return polygon.getSegmentIdxsList(); }

    public void makeLandTile(){
        myTile = new LandTile();
        Color tileColor = myTile.getColor();
        changeColor(String.valueOf(tileColor.getRGB()));
    }

    public void makeOceanTile(){
        myTile = new OceanTile();
        Color tileColor = myTile.getColor();
        changeColor(String.valueOf(tileColor.getRGB()));
    }

}
