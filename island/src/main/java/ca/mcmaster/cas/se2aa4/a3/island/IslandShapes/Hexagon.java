package ca.mcmaster.cas.se2aa4.a3.island.IslandShapes;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.util.GeometricShapeFactory;

import java.util.ArrayList;

public class Hexagon extends AbstractIslandShape{

    public Hexagon(int width, int height) {
        super(width, height);
    }

    @Override
    public Geometry getShape() {
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        Coordinate c1 = new Coordinate(roundHundredth(width * 0.25), roundHundredth(height * 0.05));
        Coordinate c2 = new Coordinate(roundHundredth(width * 0.75), roundHundredth(height * 0.05));
        Coordinate c3 = new Coordinate(roundHundredth(width * 0.95), roundHundredth(height * 0.5));
        Coordinate c4 = new Coordinate(roundHundredth(width * 0.75), roundHundredth(height * 0.95));
        Coordinate c5 = new Coordinate(roundHundredth(width * 0.25), roundHundredth(height * 0.95));
        Coordinate c6 = new Coordinate(roundHundredth(width * 0.05), roundHundredth(height * 0.5));

        coordinates.add(c1);
        coordinates.add(c2);
        coordinates.add(c3);
        coordinates.add(c4);
        coordinates.add(c5);
        coordinates.add(c6);
        coordinates.add(c1);

        Coordinate[] newCoords = new Coordinate[coordinates.size()];
        return new GeometryFactory().createPolygon(coordinates.toArray(newCoords));
    }
}
