package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class GridMesh extends MyMesh{

    private final int square_size = 20;

    public Mesh buildMesh(int polyTrans, int segTrans, int vertexTrans, float polyThick, float segThick, float vertexThick) {

        Set<MyVertex> myVertices = new LinkedHashSet<>();
        Set<MySegment> mySegments = new LinkedHashSet<>();
        Set<PolygonClass> myPolygons = new LinkedHashSet<>();

        createVertices(myVertices);
        createSegNPoly(mySegments, myPolygons, myVertices);
        setAllNeighbours(myPolygons);

        setShapeTrans(myPolygons, polyTrans);
        setShapeTrans(mySegments, segTrans);
        setShapeTrans(myVertices, vertexTrans);
        setShapeThick(myPolygons, polyThick);
        setShapeThick(mySegments, segThick);
        setShapeThick(myVertices, vertexThick);

        Set<Vertex> vertices = extractVertices(myVertices);
        Set<Segment> segments = extractSegments(mySegments);
        Set<Polygon> polygons = extractPolygons(myPolygons);
        return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build();

    }

    // Create all vertices.
    private void createVertices(Set<MyVertex> myVertices) {

        for (int x = 0; x <= width; x += square_size) {
            for (int y = 0; y <= height; y += square_size) {
                MyVertex vertex = new MyVertex(x, y);
                myVertices.add(vertex);
            }
        }
    }

    // Creates segments connecting vertices as square shapes and polygons for these segments.
    private void createSegNPoly(Set<MySegment> mySegments, Set<PolygonClass> myPolygons, Set<MyVertex> myVertices) {
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {

                int x2 = x + square_size;
                int y2 = y + square_size;

                // Creates vertices in a square.
                MyVertex v1 = findVertex(myVertices, x, y);
                MyVertex v2 = findVertex(myVertices, x2, y);
                MyVertex v3 = findVertex(myVertices, x2, y2);
                MyVertex v4 = findVertex(myVertices, x, y2);

                // Creates/finds the segments which connect the 4 vertices in a square shape.
                MySegment s1 = findSegment(mySegments, v1, v2);
                MySegment s2 = findSegment(mySegments, v2, v3);
                MySegment s3 = findSegment(mySegments, v3, v4);
                MySegment s4 = findSegment(mySegments, v4, v1);

                // Adds all segments to set, if they already exist and were found, it will just not add.
                mySegments.add(s1);
                mySegments.add(s2);
                mySegments.add(s3);
                mySegments.add(s4);

                ArrayList<MySegment> segments = new ArrayList<>();
                segments.add(s1);
                segments.add(s2);
                segments.add(s3);
                segments.add(s4);
                if (polygonDoesNotExist(myPolygons, segments)) {
                    PolygonClass polygon = new PolygonClass(segments);
                    myPolygons.add(polygon);
                    myVertices.add(polygon.getCentroid());
                }

            }
        }
    }

}
