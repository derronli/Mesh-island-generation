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

        /*
        // Create all the vertices
        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                // Adds vertices.
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
//                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y).build());
//                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y+square_size).build());
//                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y+square_size).build());

                // Adds segments.
                String posProperty = (double) x + "," + (double) y + "," + ((double) x+square_size) + "," + (double) y;
                Property positions = Property.newBuilder().setKey("position").setValue(posProperty).build();
                segments.add(Segment.newBuilder().addProperties(positions).build());
//                posProperty = (double) x + "," + ((double) y+square_size) + "," + ((double) x+square_size) + "," + ((double) y+square_size);
//                positions = Property.newBuilder().setKey("position").setValue(posProperty).build();
//                segments.add(Segment.newBuilder().addProperties(positions).build());
                posProperty = ((double) x+square_size) + "," + (double) y + "," + ((double) x+square_size) + "," + ((double) y+square_size);
                positions = Property.newBuilder().setKey("position").setValue(posProperty).build();
                segments.add(Segment.newBuilder().addProperties(positions).build());
                posProperty = (double) x + "," + (double) y + "," + (double) x + "," + ((double) y+square_size);
                positions = Property.newBuilder().setKey("position").setValue(posProperty).build();
                segments.add(Segment.newBuilder().addProperties(positions).build());
            }
        }
        // Distribute colors randomly. Vertices are immutable, need to enrich them
        Set<Vertex> verticesWithColors = new LinkedHashSet<>();
        Random bag = new Random();
        for(Vertex v: vertices){
            int red = bag.nextInt(255);
            int green = bag.nextInt(255);
            int blue = bag.nextInt(255);
            String colorCode = red + "," + green + "," + blue;
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Vertex colored = Vertex.newBuilder(v).addProperties(color).build();
            verticesWithColors.add(colored);
        }
        Set<Segment> segmentsWithColors = new LinkedHashSet<>();
        for(Segment s: segments){
            double[] positions = extractPosition(s.getPropertiesList());
            int[] col1 = findVertexColour(verticesWithColors, new double[] {positions[0], positions[1]});
            int[] col2 = findVertexColour(verticesWithColors, new double[] {positions[2], positions[3]});
            int red = (col1[0] + col2[0]) / 2;
            int green = (col1[1] + col2[1]) / 2;
            int blue = (col1[2] + col2[2]) / 2;
            String colorCode = red + "," + green + "," + blue;
            Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
            Segment colored = Segment.newBuilder(s).addProperties(color).build();
            segmentsWithColors.add(colored);
        }

        return Mesh.newBuilder().addAllVertices(verticesWithColors).addAllSegments(segmentsWithColors).build();

         */
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

    /*
    private int[] findVertexColour(Set<Vertex> vertices, double[] pos){
        for (Vertex v : vertices){
            if (Double.compare(v.getX(), pos[0]) == 0 && Double.compare(v.getY(), pos[1]) == 0){
                return extractColor(v.getPropertiesList());
            }
        }
        return new int[] {0, 0, 0};
    }

     */

    /*
    private int[] extractColor(java.util.List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
                val = p.getValue();
            }
        }
        if (val == null)
            return new int[] {0, 0, 0};
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return new int[]{red, green, blue};
    }

    private double[] extractPosition(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("position")) {
                val = p.getValue();
            }
        }
        if (val == null)
            return new double[0];
        String[] raw = val.split(",");
        double x1 = Double.parseDouble(raw[0]);
        double y1 = Double.parseDouble(raw[1]);
        double x2 = Double.parseDouble(raw[2]);
        double y2 = Double.parseDouble(raw[3]);
        return new double[] {x1, y1, x2, y2};
    }

     */

}
