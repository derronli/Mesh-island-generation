package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

import java.util.List;

public class MyPolygon implements MyShape{

    private static int totalIndex = 0;
    private final int index;
    private Polygon polygon;
    private TileType myTile;

    public MyPolygon(Polygon p){
        polygon = p;
        index = totalIndex;
        totalIndex++;
    }

    private void initPolygon(){

    }

    public void changeColor(String colorCode){
//        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
//        polygon = Polygon.newBuilder(polygon).setProperties(0, color).build();
    }

    // Getters
    public int getIndex(){ return index; }
}
