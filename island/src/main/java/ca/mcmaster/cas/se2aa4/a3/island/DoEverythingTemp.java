package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.LagoonInnerCircle;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.LagoonTile;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.LandTile;
import ca.mcmaster.cas.se2aa4.a3.island.Tiles.Tile;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.Set;

public class DoEverythingTemp {

    public Mesh makeMesh(Mesh aMesh){

        List<Vertex> vertices = aMesh.getVerticesList();
        List<Segment> segments = aMesh.getSegmentsList();
        List<Polygon> polygons = aMesh.getPolygonsList();
        List<MyVertex> myVertices = new ArrayList<>();
        List<MySegment> mySegments = new ArrayList<>();
        List<MyPolygon> myPolygons = new ArrayList<>();

        // Add all items to my structure.
        for (Vertex v : vertices){
            MyVertex myV = new MyVertex(v);
            myV.changeColor("0,0,0");
            myVertices.add(myV);
        }
        for (Segment s : segments){
            MySegment myS = new MySegment(s);
            myS.changeColor("0,0,0");
            mySegments.add(myS);
        }
        for (Polygon p : polygons){
            MyPolygon myP = new MyPolygon(p);
            myP.changeColor("255,255,255");
            myPolygons.add(myP);
        }

        addVerticesToSegments(myVertices, mySegments);
        addSegmentsToPolygons(mySegments, myPolygons);

        // Creates island shape.
        IslandShape island = new Circle();
        Geometry islandShape = island.getShape(500, 500);

        // Sets ocean tiles first and then land tiles.
        setOceanPolygons(myPolygons);
        setTileInsideShape(islandShape, myPolygons, new LandTile());

        IslandShape innerLagoon = new LagoonInnerCircle();
        Geometry innerCircle = innerLagoon.getShape(500, 500);
        setTileInsideShape(innerCircle, myPolygons, new LagoonTile());

        // Goes through all polygons and sets neighbours, which also changes tiles to beaches if necessary.
        setPolyNeighbours(myPolygons);

        vertices = extractVertices(myVertices);
        segments = extractSegments(mySegments);
        polygons = extractPolygons(myPolygons);
        return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build();

    }

    // Adds MyVertex instances to MySegment as a field.
    private void addVerticesToSegments(List<MyVertex> myVertices, List<MySegment> mySegments){
        for (MySegment s : mySegments){
            int v1Idx = s.getV1Index();
            int v2Idx = s.getV2Index();
            MyVertex v1 = myVertices.get(v1Idx);
            MyVertex v2 = myVertices.get(v2Idx);
            s.setV1(v1); s.setV2(v2);
        }
    }

    // Add MySegment instances to MyPolygon as a field.
    private void addSegmentsToPolygons(List<MySegment> mySegments, List<MyPolygon> myPolygons){
        for (MyPolygon p : myPolygons){
            for (Integer idx : p.getSegmentIdxsList()){
                MySegment s = mySegments.get(idx);
                p.addSegment(s);
            }
        }
    }

    private void setTileInsideShape(Geometry shape, List<MyPolygon> myPolygons, Tile tile){
        for (MyPolygon p : myPolygons){
            if (shape.contains(p.getJTSPolygon())){
                p.changeTile(tile);
            }
        }
    }

    // Goes through polygons and sets all to one .
    private void setOceanPolygons(List<MyPolygon> myPolygons){
        for (MyPolygon p : myPolygons){
            p.makeOceanTile();
        }
    }

    // Goes through PolygonClass list and returns list of all the polygons each one contains.
    private List<Polygon> extractPolygons(List<MyPolygon> myPolygons) {
        List<Polygon> oPolygons = new ArrayList<>();
        for (MyPolygon polygon : myPolygons) {
            oPolygons.add(polygon.getPolygon());
        }
        return oPolygons;
    }

    // Goes through MySegment list and returns list of all the segments each one contains.
    private List<Segment> extractSegments(List<MySegment> mySegments) {
        List<Segment> oSegments = new ArrayList<>();
        for (MySegment segment : mySegments) {
            oSegments.add(segment.getSegment());
        }
        return oSegments;
    }

    // Goes through MySegment list and returns list of all the segments each one contains.
    private List<Vertex> extractVertices(List<MyVertex> myVertices) {
        List<Vertex> oVertices = new ArrayList<>();
        for (MyVertex vertex : myVertices) {
            oVertices.add(vertex.getVertex());
        }
        return oVertices;
    }

    private void setPolyNeighbours(List<MyPolygon> myPolygons){
        for (MyPolygon p1 : myPolygons){
            for (MyPolygon p2: myPolygons){
                p1.checkForNeighbour(p2);
            }
        }
    }

}
