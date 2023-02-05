package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
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
import java.util.Arrays;
import java.util.List;

// methods to use: getSegmentsList(),

public class GraphicRenderer {

    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);
        Stroke stroke = new BasicStroke(0.5f);
        canvas.setStroke(stroke);

        // Render vertices.
        for (Vertex v: aMesh.getVerticesList()) {
            double centre_x = v.getX() - (THICKNESS/2.0d);
            double centre_y = v.getY() - (THICKNESS/2.0d);
            Color old = canvas.getColor();
            canvas.setColor(extractColor(v.getPropertiesList()));
            Ellipse2D point = new Ellipse2D.Double(centre_x, centre_y, THICKNESS, THICKNESS);
            canvas.fill(point);
            canvas.setColor(old);
        }

        // Render segments.
        for (Segment s: aMesh.getSegmentsList()) {
            Color old = canvas.getColor();
            canvas.setColor(extractColor(s.getPropertiesList()));
            double[] positions = extractPosition(s.getPropertiesList());
            Line2D line = new Line2D.Double(positions[0], positions[1], positions[2], positions[3]);
            canvas.fill(line);
            canvas.setColor(old);
        }
    }

    private Color extractColor(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("rgb_color")) {
                System.out.println(p.getValue());
                val = p.getValue();
            }
        }
        if (val == null)
            return Color.BLACK;
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return new Color(red, green, blue);
    }

    private double[] extractPosition(List<Property> properties) {
        String val = null;
        for(Property p: properties) {
            if (p.getKey().equals("position")) {
                System.out.println(p.getValue());
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
