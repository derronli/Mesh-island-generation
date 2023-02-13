package ca.mcmaster.cas.se2aa4.a2.generator;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh generate() {
        /*
        Set<Vertex> vertices = new LinkedHashSet<>();
        Set<Segment> segments = new LinkedHashSet<>();

         */

        Set<MyVertex> myVertices = new LinkedHashSet<>();
        Set<MySegment> mySegments = new LinkedHashSet<>();

        // Create all vertices.
        for(int x = 0; x <= width; x += square_size) {
            for (int y = 0; y <= height; y += square_size) {
                myVertices.add(new MyVertex(x, y));
            }
        }

        // Creates segments connecting vertices as square shapes.
        for(int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {

                int x2 = x + square_size;
                int y2 = y + square_size;

                MyVertex v1 = findVertex(myVertices, x, y);
                MyVertex v2 = findVertex(myVertices, x2, y);
                MyVertex v3 = findVertex(myVertices, x, y2);
                MyVertex v4 = findVertex(myVertices, x2, y2);

                if (segmentDoesNotExist(mySegments, v1, v2)){
                    mySegments.add(new MySegment(v1, v2));
                }
                if (segmentDoesNotExist(mySegments, v1, v3)){
                    mySegments.add(new MySegment(v1, v3));
                }
                if (segmentDoesNotExist(mySegments, v2, v4)){
                    mySegments.add(new MySegment(v2, v4));
                }
                if (segmentDoesNotExist(mySegments, v3, v4)){
                    mySegments.add(new MySegment(v3, v4));
                }


            }
        }

        return Mesh.newBuilder().addAllVertices(extractVertices(myVertices)).addAllSegments(extractSegments(mySegments)).build();

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


    // Checks if a segment in the list already goes between the input vertices.
    private boolean segmentDoesNotExist(Set<MySegment> segments, MyVertex v1, MyVertex v2){
        for (MySegment segment : segments){
            if (segment.existsHere(v1.getIndex(), v2.getIndex())){
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

}
