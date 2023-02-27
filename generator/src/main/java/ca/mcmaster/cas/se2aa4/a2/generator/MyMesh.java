package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import org.locationtech.jts.geom.Geometry;


public abstract class MyMesh {

    protected final int width = 500;
    protected final int height = 500;

    protected final int PRECISION = 1;


    public abstract Mesh buildMesh(int polyTrans, int segTrans, int vertexTrans, float polyThick, float segThick, float vertexThick);

    // Sets input polygon's neighbours by checking it against all other polygons in set
    protected void setNeighbours(Set<PolygonClass> myPolygons, PolygonClass polygon) {
        for (PolygonClass p : myPolygons) {
            if (polygon.isNeighbour(p)) {
                // add p index to the polygon.neighbours instance var
                polygon.addNeighbour(p.getIndex());
            }
        }
    }

    // Set's neighbours for all created polygons in the hashset
    protected void setAllNeighbours(Set<PolygonClass> myPolygons) {
        for (PolygonClass p : myPolygons) {
            setNeighbours(myPolygons, p);
            p.setNeighbourIndices();
        }
    }

    // Goes through PolygonClass list and returns list of all the polygons each one contains.
    protected Set<Polygon> extractPolygons(Set<PolygonClass> myPolygons) {
        Set<Polygon> oPolygons = new LinkedHashSet<>();
        for (PolygonClass polygon : myPolygons) {
            oPolygons.add(polygon.getPolygon());
        }
        return oPolygons;
    }

    // Goes through MySegment list and returns list of all the segments each one contains.
    protected Set<Segment> extractSegments(Set<MySegment> mySegments) {
        Set<Segment> oSegments = new LinkedHashSet<>();
        for (MySegment segment : mySegments) {
            oSegments.add(segment.getSegment());
        }
        return oSegments;
    }

    // Goes through MySegment list and returns list of all the segments each one contains.
    protected Set<Vertex> extractVertices(Set<MyVertex> myVertices) {
        Set<Vertex> oVertices = new LinkedHashSet<>();
        for (MyVertex vertex : myVertices) {
            oVertices.add(vertex.getVertex());
        }
        return oVertices;
    }

    // Checks if a polygon in the list already represents the specified connection of segments.
    protected boolean polygonDoesNotExist(Set<PolygonClass> polygons, List<MySegment> segments) {
        for (PolygonClass polygon : polygons) {
            if (polygon.equals(segments)) {
                return false;
            }
        }
        return true;
    }

    // Finds if there is a vertex at a certain point, creating a new one if there is not.
    protected MyVertex findVertex(Set<MyVertex> vertices, double x, double y) {
        for (MyVertex vertex : vertices) {
            if (vertex.existsAtPoint(x, y, PRECISION)) {
                return vertex;
            }
        }
        return new MyVertex(x, y);
    }

    // Finds if there is a segment connecting two points, creating a new one if there is not.
    protected MySegment findSegment(Set<MySegment> segments, MyVertex v1, MyVertex v2) {
        for (MySegment segment : segments) {
            if (segment.equals(v1.getIndex(), v2.getIndex())) {
                return segment;
            }
        }
        return new MySegment(v1, v2);
    }

    // Setters to use command line arguments for all values.
    protected void setShapeThick(Set<? extends MyShape> myShapes, float thickness){
        for (MyShape shape : myShapes){
            shape.setThick(thickness);
        }
    }
    protected void setShapeTrans(Set<? extends MyShape> myShapes, int trans){
        for (MyShape shape : myShapes){
            shape.setTrans(trans);
        }
    }
}

