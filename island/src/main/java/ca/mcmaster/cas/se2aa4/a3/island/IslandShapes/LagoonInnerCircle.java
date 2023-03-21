package ca.mcmaster.cas.se2aa4.a3.island.IslandShapes;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.util.GeometricShapeFactory;

public class LagoonInnerCircle implements IslandShape{

    private Coordinate midpoint;

    @Override
    public Geometry getShape(int width, int height) {
        midpoint = new Coordinate(width / 2.0, height / 2.0);
        GeometricShapeFactory gsf = new GeometricShapeFactory();
        gsf.setSize(Math.min(width, height) * 0.4);
        gsf.setCentre(midpoint);

        return gsf.createCircle();
    }

    @Override
    public Coordinate getCenter() {
        return midpoint;
    }
}
