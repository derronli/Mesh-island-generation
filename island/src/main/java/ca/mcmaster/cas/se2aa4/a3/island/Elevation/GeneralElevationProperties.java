package ca.mcmaster.cas.se2aa4.a3.island.Elevation;

import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import org.locationtech.jts.geom.Geometry;
import java.util.List;

//take in geometry in the constructor
//set protected fields island.getCentre (Point object)
//pass in list of polygons, set protected my centre polygon in the field
public abstract class GeneralElevationProperties implements  BaseElevation{

    private Geometry geom;
    private List <MyPolygon> polygonList;

    protected int maxElevation = 1000;
    public GeneralElevationProperties (Geometry g, List <MyPolygon> polygons){
        this.geom = g;
        this.polygonList = polygons;
    }

    protected void getIslandCentre (){

    }


}
