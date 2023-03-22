package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.Humidity.WaterSource;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.*;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.Point;

public class MyPolygon implements MyShape{

    private static int totalIndex = 0;
    private final int index;
    private Polygon polygon;
    private org.locationtech.jts.geom.Polygon jtsPolygon;
    // Each polygon starts with an ocean tile.
    private Tile myTile;
    private List <MySegment> segments = new ArrayList<>();
    private List<Coordinate> coordinates = new ArrayList<>();
    private List <MyPolygon> neighbours = new ArrayList<>();

    public MyPolygon(Polygon p){
        polygon = p;
        index = totalIndex;
        totalIndex++;

        myTile = new OceanTile();
        Color tileColor = myTile.getColor();
        changeColor(tileColor.getRed() + "," + tileColor.getGreen() + "," + tileColor.getBlue());

    }

    // Orders the segments and sets the vertices list.
    private void orderSegments (){

        // Creates ordered segment list and adds first segment from segments to it.
        List <MySegment> orderedSegments = new ArrayList<>();
        MySegment firstSeg = segments.get(0);
        orderedSegments.add(firstSeg);

        // Clears old coordinates list and adds coordinates from first segment to it.
        coordinates.clear();
        Coordinate c1 = new Coordinate(firstSeg.getV1X(), firstSeg.getV1Y());
        Coordinate c2 = new Coordinate(firstSeg.getV2X(), firstSeg.getV2Y());
        coordinates.add(c1);
        coordinates.add(c2);

        // Returns if this is the only segment in the list so far.
        if (segments.size() == 1){
            return;
        }

        // Orders segments and adds vertices in order to vertices list.
        for (int count = 0; count < segments.size(); count++){
            for (MySegment segment : segments) {
                if (!orderedSegments.contains(segment)) {
                    MySegment last = orderedSegments.get(orderedSegments.size() - 1);

                    // Gets the comment vertex index between the two segments.
                    int commonVertex = last.isAdjacent(segment);
                    if (commonVertex != -1) {

                        // Adds the vertex not already in the list which is in the new segment.
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
        if (commonVertex != -1 && segments.size() > 2) {

            // Before creating the jts polygon, ensures a proper ring exists.
            Coordinate firstCoord = coordinates.get(0);
            Coordinate lastCoord = coordinates.get(coordinates.size() - 1);

            // Swaps the first and second elements if the first is not equal to the last, because the second will be.
            if (!firstCoord.equals2D(lastCoord)){
                coordinates.set(0, coordinates.get(1));
                coordinates.set(1, firstCoord);
            }

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

    /**
     * Changes the tile of this polygon to an input tile type, and appropriately changes its colour.
     * @param tile input tile type to be used
     */
    public void changeTile(Tile tile){
        myTile = tile;
        Color tileColor = myTile.getColor();
        changeColor(tileColor.getRed() + "," + tileColor.getGreen() + "," + tileColor.getBlue());
    }

    // Checks for a neighbour and sets it as a neighbour if the input polygon is a neighbour.
    public boolean checkForNeighbour(MyPolygon other){
        for (int i = 0; i < other.segments.size(); i++){
            for (int j = 0; j < this.segments.size(); j++){
                if (other.segments.get(i).getIndex() == this.segments.get(j).getIndex() &&
                        !neighbours.contains(other) && !(other.index == this.index)){
                    addNeighbour(other);
                    return true;
                }
            }
        }
        return false;
    }

    private void addNeighbour (MyPolygon other){
        neighbours.add(other);
    }

    public void checkNeighboursForBeach(){
        for (MyPolygon other : neighbours){
            if (other.isWaterTile() && !this.isWaterTile()){
                changeTile(new BeachTile());
            }
        }
    }

    public boolean isWaterTile(){
        return myTile instanceof WaterSource;
    }

    public boolean containsPoint(Point point){
        return jtsPolygon.contains(point);
    }

    // Sets elevation of tile if it is an island tile.
    // should we tell them something is wrong if they set elevation for an ocean tile????
    public void setElevation(int elevation){
        if (myTile instanceof AbstractIslandTile){
            ((AbstractIslandTile) myTile).setElevation(elevation);
        }
        // maybe print this if they try doing that on polygon without a land tile
        else{
            System.out.println("You just tried to change the elevation on an ocean tile.");
        }
    }

    // Gets elevation of tile if island tile, if not just returns 0.
    public int getElevation(){
        if (myTile instanceof AbstractIslandTile){
            return ((AbstractIslandTile) myTile).getElevation();
        }
        return 0;
    }

}
