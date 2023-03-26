package ca.mcmaster.cas.se2aa4.a3.island.Humidity;


import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;

import java.util.List;

// Will go through all polygons and add humidity from surrounding water sources.
public class MoistureAdder {

    // Goes through all polygons and other polygons and segments, adding moisture to each based on the soil absorption profile.
    public void addMoistureToPolygons(List<MyPolygon> myPolygons, SoilProfile soilProfile, List<MySegment> mySegments){
        for (MyPolygon p : myPolygons){

            // Goes through other polygons and checks if they give moisture to p.
            for (MyPolygon other : myPolygons){
                int otherMoisture = (int) other.getMoistureProvided();
                double distance = p.getCenterOfPolygon().distance(other.getCenterOfPolygon());

                // If checking the polygon against itself, sets distance to 1.
                int moistureAdded = (p == other) ? soilProfile.calcMoisture(otherMoisture, 1) : soilProfile.calcMoisture(otherMoisture, distance);
                p.addMoisture(moistureAdded);
            }

            // Goes through segments and check if any have rivers which give moisture to p.
            for (MySegment other : mySegments){
                int otherMoisture = (int) other.getMoistureProvided();
                double distance = p.getCenterOfPolygon().distance(other.getMidpoint());

                int moistureAdded = soilProfile.calcMoisture(otherMoisture, distance);
                p.addMoisture(moistureAdded);
            }
        }

    }

}
