package ca.mcmaster.cas.se2aa4.a2.generator;

import java.awt.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

// make segment gen class, : addAllSegments()

public class DotGen {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    public Mesh generate() {
        Set<Vertex> vertices = new HashSet<>();
        Set<Segment> segments = new HashSet<>();
        // Create all the vertices
        for(int x = 0; x < width; x += square_size) {
            for(int y = 0; y < height; y += square_size) {
                // Adds vertices.
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y).build());
                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y).build());
                vertices.add(Vertex.newBuilder().setX((double) x).setY((double) y+square_size).build());
                vertices.add(Vertex.newBuilder().setX((double) x+square_size).setY((double) y+square_size).build());

                // Adds segments.
                String posProperty = (double) x + "," + (double) y + "," + ((double) x+square_size) + "," + (double) y;
                Property positions = Property.newBuilder().setKey("position").setValue(posProperty).build();
                segments.add(Segment.newBuilder().addProperties(positions).build());
                posProperty = (double) x + "," + ((double) y+square_size) + "," + ((double) x+square_size) + "," + ((double) y+square_size);
                positions = Property.newBuilder().setKey("position").setValue(posProperty).build();
                segments.add(Segment.newBuilder().addProperties(positions).build());
                posProperty = ((double) x+square_size) + "," + (double) y + "," + ((double) x+square_size) + "," + ((double) y+square_size);
                positions = Property.newBuilder().setKey("position").setValue(posProperty).build();
                segments.add(Segment.newBuilder().addProperties(positions).build());
                posProperty = (double) x + "," + (double) y + "," + (double) x + "," + ((double) y+square_size);
                positions = Property.newBuilder().setKey("position").setValue(posProperty).build();
                segments.add(Segment.newBuilder().addProperties(positions).build());
            }
        }
        // Distribute colors randomly. Vertices are immutable, need to enrich them
        Set<Vertex> verticesWithColors = new HashSet<>();
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
        Set<Segment> segmentsWithColors = new HashSet<>();
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
    }

    private int[] findVertexColour(Set<Vertex> vertices, double[] pos){
        for (Vertex v : vertices){
            if (Double.compare(v.getX(), pos[0]) == 0 && Double.compare(v.getY(), pos[1]) == 0){
                return extractColor(v.getPropertiesList());
            }
        }
        return new int[] {0, 0, 0};
    }

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

}
