package ca.mcmaster.cas.se2aa4.a3.island.Humidity;


import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;

import java.util.List;

// Will go through all polygons and add humidity from surrounding water sources.
public class HumidityAdder {

    public void addMoistureToPolygons(List<MyPolygon> myPolygons){
        for (MyPolygon p : myPolygons){
            for (MyPolygon other : myPolygons){
                if (p.isNeighbour(other)){
                    p.addMoisture(other.getMoistureProvided());
                }
            }
        }
    }

}
