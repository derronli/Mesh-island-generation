package ca.mcmaster.cas.se2aa4.a3.island.Builders;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor.*;
import ca.mcmaster.cas.se2aa4.a3.island.Heatmaps.HeatmapPainter;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.AbstractIslandTile;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.*;
import org.locationtech.jts.geom.Geometry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBuilder implements MeshBuilder{

    protected AdtToStructsExtractor adtToStructs = new AdtToStructsExtractor();
    List<MyVertex> myVertices = new ArrayList<>();
    List<MySegment> mySegments = new ArrayList<>();
    List<MyPolygon> myPolygons = new ArrayList<>();

    protected void extractFromMesh(Mesh aMesh){
        // Extracting the Structs from the input mesh and converts to our ADT
        StructsToAdtExtractor extractor = new StructsToAdtExtractor(aMesh);
        myVertices = extractor.getMyVertices();
        mySegments = extractor.getMySegments();
        myPolygons = extractor.getMyPolygons();
    }

    protected void setTileInsideShape(Geometry shape, List<MyPolygon> myPolygons, Class<? extends Tile> tile) {
        for (MyPolygon p : myPolygons){
            if (shape.contains(p.getJTSPolygon())){
                try {
                    p.changeTile(tile.getDeclaredConstructor().newInstance());
                }
                catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException |
                       InvocationTargetException exp){
                    System.out.println("Error in changing tile for polygon " + p.getIndex() + ": " + exp.getMessage());
                }
            }
        }
    }

    public Mesh getIsland(){
        List<Vertex> vertices = adtToStructs.extractVertices(myVertices);
        List<Segment> segments = adtToStructs.extractSegments(mySegments);
        List<Polygon> polygons = adtToStructs.extractPolygons(myPolygons);
        return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build();
    }

    public void applyHeatmap(HeatmapPainter painter){
        painter.createHeatmap(myPolygons, myVertices, mySegments);
    }

    protected abstract void setPolyNeighbours(List<MyPolygon> myPolygons);

}
