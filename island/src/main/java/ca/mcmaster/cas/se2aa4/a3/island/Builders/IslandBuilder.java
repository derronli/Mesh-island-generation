package ca.mcmaster.cas.se2aa4.a3.island.Builders;


import ca.mcmaster.cas.se2aa4.a3.island.Extractor.StructsToAdtExtractor;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.*;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.*;
import ca.mcmaster.cas.se2aa4.a3.island.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;

public class IslandBuilder extends AbstractBuilder {

    private final IslandShape islandShape;

    public IslandBuilder(IslandShape shape){
        islandShape = shape;
    }

    @Override
    public Mesh buildIsland(Mesh aMesh){

        // Extracting the Structs from the input mesh and converts to our ADT
        StructsToAdtExtractor extractor = new StructsToAdtExtractor(aMesh);
        List<MyVertex> myVertices = extractor.getMyVertices();
        List<MySegment> mySegments = extractor.getMySegments();
        List<MyPolygon> myPolygons = extractor.getMyPolygons();

        // Creates island shape.
        Geometry shape = islandShape.getShape();

        // Sets land tiles.
        setTileInsideShape(shape, myPolygons, new LandTile());

        List<Vertex> vertices = extractVertices(myVertices);
        List<Segment> segments = extractSegments(mySegments);
        List<Polygon> polygons = extractPolygons(myPolygons);
        return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build();

    }

}
