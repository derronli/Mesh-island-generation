package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

import java.awt.*;
import java.util.List;

public class MyRenderer {

    private void drawVertices(){}
    private void drawSegments(){}
    private void drawPolygons(){}

    public void render(Mesh aMesh, Graphics2D canvas){
        drawPolygons();
        drawSegments();
        drawVertices();
    }

    // Gets the position of a segment based on its vertices.
    private double[] extractPosition(List<Vertex> vertices, int v1Idx, int v2Idx) {
        Vertex v1 = vertices.get(v1Idx);
        Vertex v2 = vertices.get(v2Idx);
        double x1 = v1.getX();
        double y1 = v1.getY();
        double x2 = v2.getX();
        double y2 = v2.getY();
        return new double[] {x1, y1, x2, y2};
    }

}
