package ca.mcmaster.cas.se2aa4.a2.generator;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh generate() {

        Set<MyVertex> myVertices = new LinkedHashSet<>();
        Set<MySegment> mySegments = new LinkedHashSet<>();
        Set<PolygonClass> myPolygons = new LinkedHashSet<>();

        createVertices(myVertices);
        createSegNPoly(mySegments, myPolygons, myVertices);

        System.out.println(mySegments.size());

        return Mesh.newBuilder().addAllVertices(extractVertices(myVertices)).addAllSegments(extractSegments(mySegments)).build();

    }

    // Create all vertices.
    private void createVertices(Set<MyVertex> myVertices){

        for(int x = 0; x <= width; x += square_size) {
            for (int y = 0; y <= height; y += square_size) {
                MyVertex vertex = new MyVertex(x, y, 250);

                // testing thickness
                if (x < 150 && y < 150 || x > 350 && y > 350){
                    vertex.setThickness(10);
                }
                else{
                    vertex.setThickness(3);
                }

                // testing transparency
                if (x < 100 && y < 100 || x > 350 && y < 100){
                    vertex.setTrans(100);
                }

                myVertices.add(vertex);
            }
        }
    }

    // Creates segments connecting vertices as square shapes and polygons for these segments.
    private void createSegNPoly(Set<MySegment> mySegments, Set<PolygonClass> myPolygons, Set<MyVertex> myVertices){
        for(int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {

                int x2 = x + square_size;
                int y2 = y + square_size;

                // Creates vertices in a square.
                MyVertex v1 = findVertex(myVertices, x, y);
                MyVertex v2 = findVertex(myVertices, x2, y);
                MyVertex v3 = findVertex(myVertices, x, y2);
                MyVertex v4 = findVertex(myVertices, x2, y2);

                // Creates/finds the segments which connect the 4 vertices in a square shape.
                MySegment s1 = findSegment(mySegments, v1, v2);
                MySegment s2 = findSegment(mySegments, v1, v3);
                MySegment s3 = findSegment(mySegments, v2, v4);
                MySegment s4 = findSegment(mySegments, v3, v4);

                // testing thickness
                if (x >= 100 && x <= 400 && y >= 100 && y <= 400){
                    s1.setThickness(2);
                    s2.setThickness(2);
                    s3.setThickness(2);
                    s4.setThickness(2);
                }

                // testing transparency
                if (x <= 300 && x >= 200) {
                    s1.setTrans(150);
                    s2.setTrans(150);
                    s3.setTrans(150);
                    s4.setTrans(150);
                }

                // Adds all segments to set, if they already exist and were found, it will just not add.
                mySegments.add(s1);
                mySegments.add(s2);
                mySegments.add(s3);
                mySegments.add(s4);

            }
        }
    }

    // Goes through MySegment list and returns list of all the segments each one contains.
    private Set<Segment> extractSegments(Set<MySegment> mySegments){
        Set<Segment> oSegments = new LinkedHashSet<>();
        for (MySegment segment : mySegments){
            oSegments.add(segment.getSegment());
        }
        return oSegments;
    }

    // Goes through MySegment list and returns list of all the segments each one contains.
    private Set<Vertex> extractVertices(Set<MyVertex> myVertices){
        Set<Vertex> oVertices = new LinkedHashSet<>();
        for (MyVertex vertex : myVertices){
            oVertices.add(vertex.getVertex());
        }
        return oVertices;
    }

    // Checks if a polygon in the list already represents the specified connection of segments.
    private boolean polygonDoesNotExist(Set<PolygonClass> polygons, List<MySegment> segments){
        for (PolygonClass polygon : polygons){
            if (polygon.equals(segments)){
                return false;
            }
        }
        return true;
    }

    // Finds if there is a vertex at a certain point, creating a new one if there is not.
    private MyVertex findVertex(Set<MyVertex> vertices, double x, double y){
        for (MyVertex vertex : vertices){
            if (vertex.existsAtPoint(x, y)){
                return vertex;
            }
        }
        return new MyVertex(x, y);
    }

    // Finds if there is a segment connecting two points, creating a new one if there is not.
    private MySegment findSegment(Set<MySegment> segments, MyVertex v1, MyVertex v2){
        for (MySegment segment : segments){
            if (segment.equals(v1.getIndex(), v2.getIndex())){
                return segment;
            }
        }
        return new MySegment(v1, v2);
    }

}
