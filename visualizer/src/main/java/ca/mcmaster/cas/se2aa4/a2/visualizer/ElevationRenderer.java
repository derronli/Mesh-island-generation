package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.text.ParseException;

public class ElevationRenderer extends MyRenderer {

    private static final int THICKNESS = 3;

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

    private Color determineColor(java.util.List<Structs.Property> properties) {
        String val = PropertyManager.getProperty(properties, "elevation");
        if (val == null)
            return new Color(200, 240, 255);

        // Goes through possible elevation ranges to determine colour of tile.
        try {
            int elevation = Integer.parseInt(val);

            switch ((0 <= elevation && elevation <= 10) ? 0 :
                    (11 <= elevation && elevation <= 20) ? 1:
                            (21 <= elevation && elevation <= 30) ? 2:
                                    (31 <= elevation && elevation <= 50) ? 3:
                                            (51 <= elevation && elevation <= 75) ? 4: 5
            ){
                case 0:
                    return new Color(200, 240, 255);
                case 1:
                    return new Color(130, 220, 255);
                case 2:
                    return new Color(55, 210, 255);
                case 3:
                    return new Color(30, 175, 255);
                case 4:
                    return new Color(0, 150, 230);
                case 5:
                    return new Color(0, 130, 200);
                default:
                    System.out.println("elevation is not greater than 0 at a tile");
                    return Color.BLACK;
            }

        } catch (NumberFormatException e){
            System.out.println("error in parsing elevation for tile");
            return Color.RED;
        }

    }


}
