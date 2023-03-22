package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor.StructsToAdtExtractor;
import ca.mcmaster.cas.se2aa4.a3.island.FreshWater.LakeGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.LagoonInnerCircle;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.LagoonTile;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.LandTile;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.Tile;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;

public class DoEverythingTemp {

    public Mesh makeMesh(Mesh aMesh){
        List<Vertex> vertices;
        List<Segment> segments;
        List<Polygon> polygons;

        // Extracting the Structs from the input mesh and converts to our ADT
        StructsToAdtExtractor extractor = new StructsToAdtExtractor(aMesh);
        List<MyVertex> myVertices = extractor.getMyVertices();
        List<MySegment> mySegments = extractor.getMySegments();
        List<MyPolygon> myPolygons = extractor.getMyPolygons();



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

        // Lake generator
        new LakeGenerator(myPolygons, 10);

        vertices = extractVertices(myVertices);
        segments = extractSegments(mySegments);
        polygons = extractPolygons(myPolygons);
        return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build();

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
