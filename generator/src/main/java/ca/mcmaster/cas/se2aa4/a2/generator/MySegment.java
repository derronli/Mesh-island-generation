package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public class MySegment implements MyShape {

    private static int totalIndex = 0;
    private final int index;
    private final MyVertex v1;
    private final MyVertex v2;
    private Segment segment;

    public MySegment(MyVertex v1, MyVertex v2){
        this.index = totalIndex;
        totalIndex++;
        this.v1 = v1;
        this.v2 = v2;
        initSegment();
    }

    // Initializer if user specifies alpha value from creation.
    public MySegment(MyVertex v1, MyVertex v2, int alpha){
        this.index = totalIndex;
        totalIndex++;
        this.v1 = v1;
        this.v2 = v2;
        initSegment();
        setTrans(alpha);
    }

    /**
     * Tells the user if an input segment is adjacent to this instance.
     * @param other other segment being checked
     * @return true if the other segment is adjacent
     */
    public boolean isAdjacent(MySegment other){
        if (other.getV1Index() == this.getV1Index() && other.getV2Index() != this.getV2Index())
            return true;
        if (other.getV1Index() != this.getV1Index() && other.getV2Index() == this.getV2Index())
            return true;
        if (other.getV1Index() == this.getV2Index() && other.getV2Index() != this.getV1Index())
            return true;
        if (other.getV1Index() != this.getV2Index() && other.getV2Index() == this.getV1Index())
            return true;
        return false;
    }

    /**
     * Initializes segment based on colours of its vertices, taking the average of their colours and alpha values if applicable.
     */
    private void initSegment(){

        // Sets the colour.
        int [] col1 = v1.getColor();
        int [] col2 = v2.getColor();
        int red = (col1[0] + col2[0]) / 2;
        int green = (col1[1] + col2[1]) / 2;
        int blue = (col1[2] + col2[2]) / 2;
        String colorCode = red + "," + green + "," + blue;

        // Sets colorCode to average of alpha values if applicable.
        if (col1.length == 4 && col2.length == 4){
            int alpha =  (col1[3] + col2[3]) / 2;
            colorCode = red + "," + green + "," + blue + "," + alpha;
        }
        else if (col1.length == 4){
            int alpha =  (col1[3] + 255) / 2;
            colorCode = red + "," + green + "," + blue + "," + alpha;
        }
        else if (col2.length == 4){
            int alpha =  (col2[3] + 255) / 2;
            colorCode = red + "," + green + "," + blue + "," + alpha;
        }

        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();

        // Stores in segment.
        segment = Segment.newBuilder().setV1Idx(v1.getIndex()).setV2Idx(v2.getIndex()).addProperties(color).build();

    }

    /**
     * Checks if this segment has the indices input as its indices.
     * @param idx1 first index of a vertex to check
     * @param idx2 second index of a vertex to check
     * @return true if this segment is a connection between the two input vertices
     */
    public boolean equals(int idx1, int idx2){
        return (idx1 == getV1Index() && idx2 == getV2Index()) || (idx1 == getV2Index() && idx2 == getV1Index());
    }


    // Setters
    /**
     * Create new segment with a specified colour rather than the default.
     * @param colorCode RGB code, comma separated
     */
    public void setColor(String colorCode){
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        segment = Segment.newBuilder(segment).setProperties(0, color).build();
    }

    /**
     * Sets the transparency value of the vertex.
     * @param alpha transparency value, from 0 to 255.
     */
    public void setTrans(int alpha){
        String colorCode = PropertyManager.getProperty(getPropertiesList(), "rgb_color");
        int[] colors = PropertyManager.extractColor(colorCode);
        String newColorCode = colors[0] + "," + colors[1] + "," + colors[2] + "," + alpha;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(newColorCode).build();
        segment = Segment.newBuilder(segment).setProperties(0, color).build();
    }

    /**
     * Sets the thickness of this segment.
     * @param thickness int value of thickness wanted
     */
    public void setThick(float thickness){
        Property thick = Property.newBuilder().setKey("thickness").setValue("" + thickness).build();

        String val = PropertyManager.getProperty(this.getPropertiesList(), "thickness");

        // If thickness property does not already exist.
        if (val == null) {
            segment = Segment.newBuilder(segment).addProperties(thick).build();
        }
        // If thickness value needs to be changed.
        else{
            segment = Segment.newBuilder(segment).setProperties(1, thick).build();
        }
    }


    // Getters
    public int getIndex() {
        return index;
    }
    public int getV1Index(){
        return segment.getV1Idx();
    }
    public int getV2Index(){
        return segment.getV2Idx();
    }
    public java.util.List<Property> getPropertiesList() {
        return segment.getPropertiesList();
    }
    public Segment getSegment() { return segment; }
    public int[] getColor(){
        String val = PropertyManager.getProperty(this.getPropertiesList(), "rgb_color");
        if (val == null)
            return new int[] {0, 0, 0};
        return PropertyManager.extractColor(val);
    }

    // Provides coordinates of middle of segment.
    public double[] getMiddle(){
        return new double[]{(v1.getX() + v2.getX()) / 2, (v1.getY() + v2.getY()) / 2};
    }

}
