package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public abstract class MapDebugRenderer extends MyRenderer {

    protected static final int THICKNESS = 3;

    // Draws vertices in a simple debug fashion for heatmap/elevation map, etc.
    @Override
    protected void drawVertices(Structs.Mesh aMesh, Graphics2D canvas){
        // Render vertices.
        for (Structs.Vertex v: aMesh.getVerticesList()) {
            Color old = canvas.getColor();
            canvas.setColor(Color.BLACK);
            double centre_x = v.getX() - (THICKNESS/2.0d);
            double centre_y = v.getY() - (THICKNESS/2.0d);
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
            canvas.fill(point);

            // Resetting canvas.
            canvas.setColor(old);
        }
    }

    // Draws segments in a simple debug fashion for heatmap/elevation map, etc.
    protected void drawSegments(Structs.Mesh aMesh, Graphics2D canvas){
        // Render segments.
        for (Structs.Segment s: aMesh.getSegmentsList()) {
            Stroke oldStroke = canvas.getStroke();
            Color old = canvas.getColor();

            canvas.setStroke(new BasicStroke(0.5f));
            canvas.setColor(Color.BLACK);

            double[] position = extractPosition(aMesh.getVerticesList(), s.getV1Idx(), s.getV2Idx());
            Line2D line = new Line2D.Double(position[0], position[1], position[2], position[3]);
            canvas.draw(line);

            // Resetting canvas.
            canvas.setColor(old);
            canvas.setStroke(oldStroke);
        }
    }

    // Draws polygons in simple debug fashion, only changing the colour based on an implemented method in subclasses.
    protected void drawPolygons(Structs.Mesh aMesh, Graphics2D canvas){
        java.util.List<Structs.Segment> segments = aMesh.getSegmentsList();
        java.util.List<Structs.Vertex> vertices = aMesh.getVerticesList();

        for (Structs.Polygon p : aMesh.getPolygonsList()){
            Color old = canvas.getColor();

            canvas.setColor(determineColor(p.getPropertiesList()));

            java.awt.Polygon polygon = createPolygon(p, segments, vertices);
            canvas.fill(polygon);

            // Resetting canvas.
            canvas.setColor(old);

        }

    }

    // Allows subclasses to determine which colours are used for their specific type of map.
    protected abstract Color determineColor(java.util.List<Structs.Property> properties);

}
