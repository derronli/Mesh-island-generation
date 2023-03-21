package ca.mcmaster.cas.se2aa4.a3.island.Elevation;
//taking in a shape, calculate midpoint, move outwards from the highest point
//modify polygon, segment, and vertex classes to have methods to get and set an elevation value 
public interface BaseElevation {

    void generateElevation();
    void selectElevation();

}
