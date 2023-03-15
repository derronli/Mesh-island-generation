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
    private List<Coordinate> coordinates = new ArrayList<>();

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

        coordinates.clear();

        Coordinate c1 = new Coordinate(firstSeg.getV1X(), firstSeg.getV1Y());
        Coordinate c2 = new Coordinate(firstSeg.getV2X(), firstSeg.getV2Y());
        coordinates.add(c1);
        coordinates.add(c2);

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
                            coordinates.add(firstCoord);
                            coordinates.add(secondCoord);
                        }

                        // Next adds the vertex not already in the list which is in the new segment.
                        commonVertex = segment.isAdjacent(last);
                        Coordinate newCoord = (commonVertex == segment.getV1Index()) ? new Coordinate(segment.getV2X(), segment.getV2Y()) : new Coordinate(segment.getV1X(), segment.getV1Y());
                        coordinates.add(newCoord);

                        orderedSegments.add(segment);
                    }
                }
            }
        }

        segments = orderedSegments;

        // Adds the final coordinate to the coordinate array to make a closed loop if possible and makes the polygon if so.
        MySegment first = segments.get(0);
        MySegment last = segments.get(segments.size() - 1);
        int commonVertex = first.isAdjacent(last);
        if (commonVertex != 0) {
            Coordinate lastCoord = (commonVertex == first.getV1Index()) ? new Coordinate(first.getV1X(), first.getV1Y()) : new Coordinate(first.getV2X(), first.getV2Y());
            coordinates.add(lastCoord);
            setJTSPoly();
        }

    }

    // Creates a new JTS polygon based on the current segments.
    private void setJTSPoly(){
        Coordinate[] newCoords = new Coordinate[coordinates.size()];
        jtsPolygon = new GeometryFactory().createPolygon(coordinates.toArray(newCoords));
    }


    public void changeColor(String colorCode){
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        polygon = Polygon.newBuilder(polygon).setProperties(0, color).build();
    }

    // Adds segment to segment list.
    public void addSegment(MySegment s){
        segments.add(s);
        orderSegments();
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
