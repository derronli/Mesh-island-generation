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

    /**
     * Initializes segment based on colours of its vertices, taking the average of their colours.
     */
    private void initSegment(){

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

    /**
     * Checks if this segment has the indices input as its indices.
     * @param idx1 first index of a vertex to check
     * @param idx2 second index of a vertex to check
     * @return true if this segment is a connection between the two input vertices
     */
    public boolean existsHere(int idx1, int idx2){
        return (idx1 == getV1Index() && idx2 == getV2Index()) || (idx1 == getV2Index() && idx2 == getV1Index());
    }

    // Setters
    /**
     * Create new segment with a specified colour rather than the default.
     * @param colorCode RGB code, comma separated
     */
    public void setColour(String colorCode){
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        segment = Segment.newBuilder(segment).setProperties(0, color).build();
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
    public java.util.List<ca.mcmaster.cas.se2aa4.a2.io.Structs.Property> getPropertiesList() {
        return segment.getPropertiesList();
    }
    public Segment getSegment() { return segment; }
    public int[] getColour(){
        String val = null;
        for(Property p: segment.getPropertiesList()) {
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