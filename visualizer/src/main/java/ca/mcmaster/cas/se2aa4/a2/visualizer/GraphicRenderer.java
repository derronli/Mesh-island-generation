package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

public class GraphicRenderer {

    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);

        // Render segments.
        for (Segment s: aMesh.getSegmentsList()) {
            float strokeThickness = extractThicknessStroke(s.getPropertiesList());
            Stroke oldStroke = canvas.getStroke();
            Color old = canvas.getColor();

            canvas.setStroke(new BasicStroke(strokeThickness));
            canvas.setColor(extractColor(s.getPropertiesList()));

            double[] position = extractPosition(aMesh.getVerticesList(), s.getV1Idx(), s.getV2Idx());
            Line2D line = new Line2D.Double(position[0], position[1], position[2], position[3]);
            canvas.draw(line);

            // Resetting canvas.
            canvas.setColor(old);
            canvas.setStroke(oldStroke);
        }

        // Render vertices.
        for (Vertex v: aMesh.getVerticesList()) {
            Color old = canvas.getColor();
            canvas.setColor(extractColor(v.getPropertiesList()));

            int thick = extractThicknessVer(v.getPropertiesList());
            double centre_x = v.getX() - (thick/2.0d);
            double centre_y = v.getY() - (thick/2.0d);
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, thick, thick);
            canvas.fill(point);

            // Resetting canvas.
            canvas.setColor(old);
        }

    }

    private Color extractColor(List<Property> properties) {
        String val = PropertyManager.getProperty(properties, "rgb_color");
        if (val == null)
            return Color.BLACK;
        return PropertyManager.extractColor(val);
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

    // Gets thickness from properties and uses default thickness if there is none.
    private int extractThicknessVer(List<Property> properties){
        String raw = PropertyManager.getProperty(properties, "thickness");
        if (raw == null)
            return THICKNESS;
        return Integer.parseInt(raw);
    }

    // Gets thickness from properties and uses default thickness if there is none.
    private float extractThicknessStroke(List<Property> properties){
        String raw = PropertyManager.getProperty(properties, "thickness");
        if (raw == null)
            return 0.5f;
        return Float.parseFloat(raw);
    }

}
