package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public class MyVertex {

    private static int totalIndex = 0;
    private final int index;
    private Vertex vertex;

    public MyVertex(double x, double y){
        this.index = totalIndex;
        totalIndex++;

        vertex = Vertex.newBuilder().setX(x).setY(y).build();
    }


    // Setters
    public void setColour(String colorCode){
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        vertex = Vertex.newBuilder().setX(getX()).setY(getY()).addProperties(color).build();
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
