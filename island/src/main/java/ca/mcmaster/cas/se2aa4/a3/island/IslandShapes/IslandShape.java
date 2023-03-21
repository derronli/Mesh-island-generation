package ca.mcmaster.cas.se2aa4.a3.island.IslandShapes;

import org.locationtech.jts.geom.*;

public interface IslandShape {
    Geometry getShape(int width, int height);
    Point getCenter();
}
