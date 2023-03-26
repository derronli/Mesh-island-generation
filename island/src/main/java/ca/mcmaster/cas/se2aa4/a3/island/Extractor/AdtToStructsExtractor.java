package ca.mcmaster.cas.se2aa4.a3.island.Extractor;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;

import java.util.ArrayList;
import java.util.List;

public class AdtToStructsExtractor {

    // Goes through PolygonClass list and returns list of all the polygons each one contains.
    public List<Polygon> extractPolygons(List<MyPolygon> myPolygons) {
        List<Polygon> oPolygons = new ArrayList<>();
        for (MyPolygon polygon : myPolygons) {
            oPolygons.add(polygon.getPolygon());
        }
        return oPolygons;
    }

    // Goes through MySegment list and returns list of all the segments each one contains.
    public List<Segment> extractSegments(List<MySegment> mySegments) {
        List<Segment> oSegments = new ArrayList<>();
        for (MySegment segment : mySegments) {
            oSegments.add(segment.getSegment());
        }
        return oSegments;
    }

    // Goes through MyVertex list and returns list of all the vertices each one contains.
    public List<Vertex> extractVertices(List<MyVertex> myVertices) {
        List<Vertex> oVertices = new ArrayList<>();
        for (MyVertex vertex : myVertices) {
            oVertices.add(vertex.getVertex());
        }
        return oVertices;
    }

}
