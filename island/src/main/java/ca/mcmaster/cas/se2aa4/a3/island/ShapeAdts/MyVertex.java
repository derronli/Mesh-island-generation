package ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Vertex.*;

import java.awt.*;

public class MyVertex implements MyShape {

    private static int totalIndex = 0;
    private final int index;
    private Vertex vertex;
    private IslandVertex islandVertex;

    public MyVertex(Vertex v){
        vertex = v;
        index = totalIndex;
        totalIndex++;
        islandVertex = new LandVertex();
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

    public boolean makeRiverVertex(int discharge){
        if (islandVertex.getClass() == RiverVertex.class){
            return false;
        }
        // Ensure that the originally set elevation is maintained, when changing the vertex type
        islandVertex = new RiverVertex(discharge, islandVertex.getElevation());
        Color riverColor = islandVertex.getColor();
        changeColor(riverColor.getRed() + "," + riverColor.getGreen() + "," + riverColor.getBlue());
        return true;
    }

    public void setElevation(int elevation){ islandVertex.setElevation(elevation);}


    public int getElevation() { return islandVertex.getElevation(); }

    public double getMoisture(){
        return islandVertex.getDischarge();
    }

    public double getMoistureProvided(){ return islandVertex.getDischarge(); }

    public void addToDischarge(int n) {
        islandVertex.addToDischarge(n);
    }

}
