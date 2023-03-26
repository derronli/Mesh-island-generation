package ca.mcmaster.cas.se2aa4.a3.island.Humidity;


import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;

import java.util.List;

// Will go through all polygons and add humidity from surrounding water sources.
public class MoistureAdder {

    // Goes through all polygons and other polygons, adding moisture to each based on the soil absorption profile.
    public void addMoistureToPolygons(List<MyPolygon> myPolygons, SoilProfile soilProfile){
        for (MyPolygon p : myPolygons){
            for (MyPolygon other : myPolygons){
                int otherMoisture = other.getMoistureProvided();
                double distance = p.getCenterOfPolygon().distance(other.getCenterOfPolygon());

                // If checking the polygon against itself, sets distance to 1.
                int moistureAdded = (p == other) ? soilProfile.calcMoisture(otherMoisture, 1) : soilProfile.calcMoisture(otherMoisture, distance);
                p.addMoisture(moistureAdded);
            }
        }
    }

}
