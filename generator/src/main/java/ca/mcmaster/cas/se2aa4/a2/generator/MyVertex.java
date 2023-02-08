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

    // Setters
    /**
     * Create new vertex with a specified colour rather than the default.
     * @param colorCode RGB code, comma separated
     */
    public void setColour(String colorCode){
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        vertex = Vertex.newBuilder(vertex).setProperties(0, color).build();
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

    /**
     * Extracts the colour from the vertex and returns as array.
     * @return RBG value of vertex colour
     */
    public int[] getColour(){
        String val = null;
        for(Property p: vertex.getPropertiesList()) {
            if (p.getKey().equals("rgb_color")) {
                val = p.getValue();
            }
        }
        if (val == null)
            return new int[] {0, 0, 0};
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);
        return new int[]{red, green, blue};
    }

}
