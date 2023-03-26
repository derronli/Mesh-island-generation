package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.MyVertex;

import java.util.List;

//taking in a shape, calculate midpoint, move outwards from the highest point
//modify polygon, segment, and vertex classes to have methods to get and set an elevation value 
public interface BaseElevation {

    void generateElevation(IslandShape i, List<MyPolygon> polygons, List <MyVertex> vertices);
    //void setElevation();

}
