package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public class MySegment {

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

    // Setters

    /**
     * Create new segment with a specified colour rather than the default.
     * @param colorCode RGB code, comma separated
     */
    public void setColour(String colorCode){
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        segment = Segment.newBuilder(segment).addProperties(color).build();
    }

    /**
     * Initializes segment based on colours of its vertices, taking the average of their colours.
     */
    public void initSegment(){

        // Sets the colour.
        int [] col1 = v1.getColour();
        int [] col2 = v2.getColour();
        int red = (col1[0] + col2[0]) / 2;
        int green = (col1[1] + col2[1]) / 2;
        int blue = (col1[2] + col2[2]) / 2;
        String colorCode = red + "," + green + "," + blue;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();

        // Stores in segment.
        segment = Segment.newBuilder().setV1Idx(v1.getIndex()).setV2Idx(v2.getIndex()).addProperties(color).build();

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

}
