package ca.mcmaster.cas.se2aa4.a3.island.Builders;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor.AdtToStructsExtractor;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor.StructsToAdtExtractor;
import ca.mcmaster.cas.se2aa4.a3.island.Heatmaps.HeatmapPainter;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.MyVertex;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBuilder implements MeshBuilder{

    protected AdtToStructsExtractor adtToStructs = new AdtToStructsExtractor();
    List<MyVertex> myVertices = new ArrayList<>();
    List<MySegment> mySegments = new ArrayList<>();
    List<MyPolygon> myPolygons = new ArrayList<>();

    // Goes through MyVertex list and returns list of all the segments each one contains.
    protected List<Vertex> extractVertices(List<MyVertex> myVertices) {
        return adtToStructs.extractVertices(myVertices);
    }

    // Goes through MySegment list and returns list of all the segments each one contains.
    protected List<Segment> extractSegments(List<MySegment> mySegments) {
        return adtToStructs.extractSegments(mySegments);
    }

    // Goes through MyPolygon list and returns list of all the polygons each one contains.
    protected List<Polygon> extractPolygons(List<MyPolygon> myPolygons) {
        return adtToStructs.extractPolygons(myPolygons);
    }

    protected void setTileInsideShape(Geometry shape, List<MyPolygon> myPolygons, Tile tile){
        for (MyPolygon p : myPolygons){
            if (shape.contains(p.getJTSPolygon())){
                p.changeTile(tile);
            }
        }
    }

    public Mesh getIsland(){
        List<Vertex> vertices = extractVertices(myVertices);
        List<Segment> segments = extractSegments(mySegments);
        List<Polygon> polygons = extractPolygons(myPolygons);
        return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build();
    }

    public void applyHeatmap(HeatmapPainter painter){
        painter.createHeatmap(myPolygons, myVertices, mySegments);
    }

    public List <MyPolygon> extractPolygonsFromBuilder() {
        return myPolygons;
    }

}
