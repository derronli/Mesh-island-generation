package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;

import java.awt.*;
import java.util.List;

public class MyRenderer {

    protected void drawVertices(Mesh aMesh, Graphics2D canvas){}
    protected void drawSegments(Mesh aMesh, Graphics2D canvas){}
    protected void drawPolygons(Mesh aMesh, Graphics2D canvas){}

    public void render(Mesh aMesh, Graphics2D canvas){
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);
        
        drawPolygons(aMesh, canvas);
        drawSegments(aMesh, canvas);
        drawVertices(aMesh, canvas);
    }

    // Gets the position of a segment based on its vertices.
    protected double[] extractPosition(List<Vertex> vertices, int v1Idx, int v2Idx) {
        Vertex v1 = vertices.get(v1Idx);
        Vertex v2 = vertices.get(v2Idx);
        double x1 = v1.getX();
        double y1 = v1.getY();
        double x2 = v2.getX();
        double y2 = v2.getY();
        return new double[] {x1, y1, x2, y2};
    }

    protected java.awt.Polygon createPolygon(Polygon polygon, List<Segment> segments, List<Vertex> vertices){
        List<Integer> segmentIdxList = polygon.getSegmentIdxsList();

        // Number of vertices is number of segments multiplied by 2.
        int numVertices = segmentIdxList.size();

        int[] xCoords = new int[numVertices];
        int[] yCoords = new int[numVertices];
        int count = 0;

        // Goes through segments by index.
        for (Integer index : segmentIdxList){
            Segment segment = segments.get(index);

            // Goes through vertices of each segment and adds their coordinates to array.
            Vertex v1 = vertices.get(segment.getV1Idx());
            Vertex v2 = vertices.get(segment.getV2Idx());
            if (isNewVertex(xCoords, yCoords, v1)) {
                xCoords[count] = (int) v1.getX();
                yCoords[count] = (int) v1.getY();
            }
            else {
                xCoords[count] = (int) v2.getX();
                yCoords[count] = (int) v2.getY();
            }
            count++;
        }


        return new java.awt.Polygon(xCoords, yCoords, numVertices);
    }

    private boolean isNewVertex(int[] xCoords, int[] yCoords, Vertex v) {
        for (int i = 0; i < xCoords.length; i++) {
            if (v.getX() == xCoords[i] && v.getY() == yCoords[i]) {
                return false;
            }
        }
        return true;
    }

}
