package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

public class NormalRender extends MyRenderer {

    protected void drawVertices(Mesh aMesh, Graphics2D canvas){
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

    protected void drawSegments(Mesh aMesh, Graphics2D canvas){
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
    }

    protected void drawPolygons(Mesh aMesh, Graphics2D canvas){

    }

    private static final int THICKNESS = 3;

    private Color extractColor(List<Property> properties) {
        String val = PropertyManager.getProperty(properties, "rgb_color");
        if (val == null)
            return Color.BLACK;
        return PropertyManager.extractColor(val);
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
