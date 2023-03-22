package ca.mcmaster.cas.se2aa4.a3.island.IslandShapes;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public abstract class AbstractIslandShape implements IslandShape{

    protected Coordinate midpoint;
    protected int width, height;

    public AbstractIslandShape(int width, int height){
        this.width = width; this.height = height;
        midpoint = new Coordinate(width / 2.0, height / 2.0);
    }

    public Point getCenter() {
        GeometryFactory gf = new GeometryFactory();
        return gf.createPoint(midpoint);
    }

    protected double roundHundredth(double x){
        return ((int)(x * 100)) / 100.0;
    }

}
