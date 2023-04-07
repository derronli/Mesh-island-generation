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

    public boolean makeCityVertex(int size){
        // Make sure vertex is not already a city
        if (islandVertex.getClass() == CityVertex.class){
            return false;
        }

        islandVertex = new CityVertex(size);
        Color riverColor = islandVertex.getColor();
        changeColor(riverColor.getRed() + "," + riverColor.getGreen() + "," + riverColor.getBlue());
        setThick(size);
        return true;
    }

    /**
     * Sets the thickness of this vertex.
     * @param thickness int value of thickness wanted
     */
    public void setThick(float thickness){
        Structs.Property thick = Structs.Property.newBuilder().setKey("thickness").setValue("" + thickness).build();

        String val = null;
        for(Structs.Property p: this.getPropertiesList()) {
            if (p.getKey().equals("thickness")) {
                val = p.getValue();
            }
        }

        // If thickness property does not already exist.
        if (val == null) {
            vertex = Vertex.newBuilder(vertex).addProperties(thick).build();
        }
        // If thickness value needs to be changed.
        else{
            vertex = Vertex.newBuilder(vertex).setProperties(1, thick).build();
        }
    }

    public void setElevation(int elevation){ islandVertex.setElevation(elevation);}

    public java.util.List<Structs.Property> getPropertiesList() {
        return vertex.getPropertiesList();
    }


    public int getElevation() { return islandVertex.getElevation(); }

    public double getMoisture(){
        return islandVertex.getDischarge();
    }

    public double getMoistureProvided(){ return islandVertex.getDischarge(); }

    public void addToDischarge(int n) {
        islandVertex.addToDischarge(n);
    }

}
