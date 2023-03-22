package ca.mcmaster.cas.se2aa4.a3.island.IslandShapes;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.util.GeometricShapeFactory;

public class LagoonInnerCircle extends AbstractIslandShape{

    public LagoonInnerCircle(int width, int height) {
        super(width, height);
    }

    @Override
    public Geometry getShape() {
        GeometricShapeFactory gsf = new GeometricShapeFactory();
        gsf.setSize(Math.min(width, height) * 0.4);
        gsf.setCentre(midpoint);

        return gsf.createCircle();
    }

}
