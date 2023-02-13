package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.util.Random;

public class MyVertex {

    private static int totalIndex = 0;
    private final int index;
    private Vertex vertex;

    public MyVertex(double x, double y){
        this.index = totalIndex;
        totalIndex++;
        initVertex(x, y);
    }

    /**
     * Initializes vertex with a random colour.
     * @param x x-position of vertex
     * @param y y-position of vertex
     */
    private void initVertex(double x, double y){
        Random bag = new Random();
        int red = bag.nextInt(255);
        int green = bag.nextInt(255);
        int blue = bag.nextInt(255);
        String colorCode = red + "," + green + "," + blue;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        vertex = Vertex.newBuilder().setX(x).setY(y).addProperties(color).build();
    }

    /**
     * Checks if the input coordinates are the coordinates at which this vertex exists.
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if this vertex exists at the input coordinates, false if input coordinates are unique
     */
    public boolean existsAtPoint(double x, double y){
        return Double.compare(x, getX()) == 0 && Double.compare(y, getY()) == 0;
    }

    // Setters
    /**
     * Create new vertex with a specified colour rather than the default.
     * @param colorCode RGB code, comma separated
     */
    public void setColour(String colorCode){
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        vertex = Vertex.newBuilder(vertex).setProperties(0, color).build();
    }

    /**
     * Sets the thickness of this vertex.
     * @param thickness int value of thickness wanted
     */
    public void setThickness(int thickness){
        Property thick = Property.newBuilder().setKey("thickness").setValue("" + thickness).build();

        String val = PropertyManager.getProperty(this.getPropertiesList(), "thickness");

        // If thickness property does not already exist.
        if (val == null) {
            vertex = Vertex.newBuilder(vertex).addProperties(thick).build();
        }
        // If thickness value needs to be changed.
        else{
            vertex = Vertex.newBuilder(vertex).setProperties(1, thick).build();
        }
    }


    // Getters
    public int getIndex() {
        return index;
    }
    public double getX(){
        return vertex.getX();
    }
    public double getY(){
        return vertex.getY();
    }
    public double[] getPoint(){
        return new double[] {getX(), getY()};
    }
    public Vertex getVertex(){ return vertex; }

    /**
     * Extracts the colour from the vertex and returns as array.
     * @return RBG value of vertex colour
     */
    public int[] getColour(){
        String val = PropertyManager.getProperty(this.getPropertiesList(), "rgb_color");
        if (val == null)
            return new int[] {0, 0, 0};
        return PropertyManager.extractColor(val);
    }

    public java.util.List<ca.mcmaster.cas.se2aa4.a2.io.Structs.Property> getPropertiesList() {
        return vertex.getPropertiesList();
    }
}
