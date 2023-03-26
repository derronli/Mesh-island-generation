package ca.mcmaster.cas.se2aa4.a3.island.Humidity;


import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import org.locationtech.jts.geom.Geometry;

import java.util.List;

// Will go through all polygons and add humidity from surrounding water sources.
public class MoistureAdder {

    public void addMoistureToPolygons(List<MyPolygon> myPolygons, SoilProfile soilProfile){
        for (MyPolygon p : myPolygons){
            for (MyPolygon other : myPolygons){
                int otherMoisture = other.getMoistureProvided();
                double distance = p.getCenterOfPolygon().distance(other.getCenterOfPolygon());
                int moistureAdded = soilProfile.calcMoisture(otherMoisture, distance);
                p.addMoisture(moistureAdded);
            }
        }
    }

}
