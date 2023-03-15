package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;

public class MyVertex implements MyShape{

    private static int totalIndex = 0;
    private final int index;
    private Vertex vertex;

    public MyVertex(Vertex v){
        vertex = v;
        index = totalIndex;
        totalIndex++;
    }

    public void changeColor(String colorCode){
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        vertex = Vertex.newBuilder(vertex).setProperties(0, color).build();
    }


    // Getters
    public int getIndex(){ return index; }
    public double getX(){ return vertex.getX(); }
    public double getY(){ return vertex.getY(); }
    public Vertex getVertex(){ return vertex; }
    public java.util.List<Structs.Property> getPropertiesList() { return vertex.getPropertiesList(); }
}
