package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

public class MySegment implements MyShape{

    private Segment segment;

    public MySegment(Segment s){
        segment = s;
    }

    public void changeColor(String colorCode){
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        segment = Segment.newBuilder(segment).setProperties(0, color).build();
    }
}
