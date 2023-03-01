package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

public class MyPolygon implements MyShape{

    private Polygon polygon;
    private TileType myTile;

    public MyPolygon(Polygon p){
        polygon = p;
    }

    public void changeColor(String colorCode){}
}
