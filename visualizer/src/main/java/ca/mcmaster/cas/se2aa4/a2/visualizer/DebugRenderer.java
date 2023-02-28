package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;


import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class DebugRenderer extends MyRenderer{

    private static final int VER_THICKNESS = 3;
    protected void drawSegments (Mesh aMesh, Graphics2D canvas) {
        for (Segment s: aMesh.getSegmentsList()) {
            // Default thickness
            float strokeThickness = 0.5f;
            Stroke oldStroke = canvas.getStroke();
            Color old = canvas.getColor();

            canvas.setStroke(new BasicStroke(strokeThickness));
            canvas.setColor(Color.WHITE);

            double[] position = extractPosition(aMesh.getVerticesList(), s.getV1Idx(), s.getV2Idx());
            Line2D line = new Line2D.Double(position[0], position[1], position[2], position[3]);
            canvas.draw(line);

            // Resetting canvas
            canvas.setColor(old);
            canvas.setStroke(oldStroke);
        }
        drawNeighbourSegments(aMesh, canvas); // DRAWS neighbour relations
    }

    // Reduce repetitive code -> just for drawing dots (vertices and centroids)
    private Ellipse2D drawDot(Vertex v) {

        int thick = VER_THICKNESS;
        double centre_x = v.getX() - (thick/2.0d);
        double centre_y = v.getY() - (thick/2.0d);
        Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, thick, thick);

        return point;
    }

    // Draws every vertex by extracting vertex list from mesh
    protected void drawVertices(Mesh aMesh, Graphics2D canvas) {
        for (Vertex v: aMesh.getVerticesList()) {

            Color old = canvas.getColor();
            canvas.setColor(Color.WHITE); // Colour for vertices

            Ellipse2D point = drawDot(v);
            canvas.fill(point);

            // Resetting canvas.
            canvas.setColor(old);
        }
        // Drawing centroids
        drawCentroids(aMesh, canvas);
    }

    // Draws all polygon centroids by extracting the list of centroids (Vertex type)
    protected void drawCentroids(Mesh aMesh, Graphics2D canvas) {

        for (Vertex v: extractCentroidList(aMesh)) {

            Color old = canvas.getColor();
            canvas.setColor(Color.RED); // colour for centroids

            Ellipse2D point = drawDot(v);
            canvas.fill(point);

            // Resetting canvas.
            canvas.setColor(old);
        }
    }

    // Converts polygon list to a list of each vertices
    private List<Vertex> extractCentroidList(Mesh aMesh) {
        int idx;
        Vertex v;
        List<Vertex> centroidList = new ArrayList<>();;

        for (Polygon polygon : aMesh.getPolygonsList()) {
            idx = polygon.getCentroidIdx(); // The centroid is indexed as part of the set of vertices
            // Find corresponding vertex
            v = getVertexByIdx(aMesh, idx);
            centroidList.add(v);

        }

        return centroidList;
    }

    // Looks for the vertex when given an index
    private Vertex getVertexByIdx(Mesh aMesh, int idx) {
        List<Vertex> vertexList = aMesh.getVerticesList();

        return vertexList.get(idx);
    }

    private void drawNeighbourSegments(Mesh aMesh, Graphics2D canvas) {
        List<Polygon> polygonList = aMesh.getPolygonsList();
        List<Integer> neighbourIdx;
        Polygon neighbour;
        double[] position;

        for (Polygon polygon : polygonList) {
            neighbourIdx = polygon.getNeighborIdxsList();
            // Iterate over each neighbour index
            for (Integer i : neighbourIdx) {
                // Get (x,y) coordinates of current polygon centroid and each of its neighbours
                neighbour = polygonList.get(i);
                position = extractPosition(aMesh.getVerticesList(), polygon.getCentroidIdx(), neighbour.getCentroidIdx());

                // Draws the segment between the two centroids
                float strokeThickness = 0.5f;
                Stroke oldStroke = canvas.getStroke();
                Color old = canvas.getColor();

                canvas.setStroke(new BasicStroke(strokeThickness));
                canvas.setColor(Color.GRAY);

                Line2D line = new Line2D.Double(position[0], position[1], position[2], position[3]);
                canvas.draw(line);

                // Resetting canvas.
                canvas.setColor(old);
                canvas.setStroke(oldStroke);
            }
        }
    }
    protected void drawPolygons(Mesh aMesh, Graphics2D canvas){
        List<Segment> segments = aMesh.getSegmentsList();
        List<Vertex> vertices = aMesh.getVerticesList();

        for (Polygon p : aMesh.getPolygonsList()){
            float strokeThickness = 0.5f;
            Stroke oldStroke = canvas.getStroke();
            Color old = canvas.getColor();

            canvas.setStroke(new BasicStroke(strokeThickness));
            canvas.setColor(Color.BLACK);

            java.awt.Polygon polygon = createPolygon(p, segments, vertices);
            canvas.fill(polygon);

            // Resetting canvas.
            canvas.setColor(old);
            canvas.setStroke(oldStroke);

        }

    }
}
