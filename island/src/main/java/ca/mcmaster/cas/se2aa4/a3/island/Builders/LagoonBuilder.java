package ca.mcmaster.cas.se2aa4.a3.island.Builders;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor.StructsToAdtExtractor;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.*;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.*;
import ca.mcmaster.cas.se2aa4.a3.island.*;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;

public class LagoonBuilder extends AbstractBuilder {

    public Mesh buildIsland(Mesh aMesh){

        // Extracting the Structs from the input mesh and converts to our ADT
        StructsToAdtExtractor extractor = new StructsToAdtExtractor(aMesh);
        List<MyVertex> myVertices = extractor.getMyVertices();
        List<MySegment> mySegments = extractor.getMySegments();
        List<MyPolygon> myPolygons = extractor.getMyPolygons();

        // Creates island shape.
        IslandShape island = new Circle();
        Geometry islandShape = island.getShape(500, 500);

        // Sets land tiles.
        setTileInsideShape(islandShape, myPolygons, new LandTile());

        IslandShape innerLagoon = new LagoonInnerCircle();
        Geometry innerCircle = innerLagoon.getShape(500, 500);
        setTileInsideShape(innerCircle, myPolygons, new LagoonTile());

        // Goes through all polygons and sets neighbours, which also changes tiles to beaches if necessary.
        setPolyNeighbours(myPolygons);

        List<Vertex> vertices = extractVertices(myVertices);
        List<Segment> segments = extractSegments(mySegments);
        List<Polygon> polygons = extractPolygons(myPolygons);
        return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build();

    }

    private void setPolyNeighbours(List<MyPolygon> myPolygons){
        for (MyPolygon p1 : myPolygons){
            for (MyPolygon p2: myPolygons){
                p1.checkForNeighbour(p2);
            }
            p1.checkNeighboursForBeach();
        }
    }

}
