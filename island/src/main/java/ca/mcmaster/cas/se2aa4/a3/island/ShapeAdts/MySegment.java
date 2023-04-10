package ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public class MySegment implements MyShape {

    private static int totalIndex = 0;
    private final int index;
    private Segment segment;
    private MyVertex v1, v2;

    public MySegment(Segment s){
        segment = s;
        index = totalIndex;
        totalIndex++;
    }

    public MySegment(MyVertex v1, MyVertex v2){
        this.index = totalIndex;
        totalIndex++;
        this.v1 = v1;
        this.v2 = v2;
        initSegment();
    }

    private void initSegment(){
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("105,105,105").build();
        // Stores in segment.
        segment = Segment.newBuilder().setV1Idx(v1.getIndex()).setV2Idx(v2.getIndex()).addProperties(color).build();
        setThick(2);

    }

    public void changeColor(String colorCode){
        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        segment = Segment.newBuilder(segment).setProperties(0, color).build();
    }

    // Setters
    public void setV1(MyVertex v1) {
        this.v1 = v1;
    }
    public void setV2(MyVertex v2) {
        this.v2 = v2;
    }

    /**
     * Returns the index of the comment vertex between segments.
     * @param other other segment to be compared against.
     * @return the index of the common vertex, 0 if not adjacent.
     */
    public int isAdjacent(MySegment other){
        if (other.getV1Index() == this.getV1Index() && other.getV2Index() != this.getV2Index())
            return getV1Index();
        if (other.getV1Index() != this.getV1Index() && other.getV2Index() == this.getV2Index())
            return getV2Index();
        if (other.getV1Index() == this.getV2Index() && other.getV2Index() != this.getV1Index())
            return getV2Index();
        if (other.getV1Index() != this.getV2Index() && other.getV2Index() == this.getV1Index())
            return getV1Index();
        return -1;
    }

    /**
     * Sets the thickness of this segment.
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
            segment = Segment.newBuilder(segment).addProperties(thick).build();
        }
        // If thickness value needs to be changed.
        else{
            segment = Segment.newBuilder(segment).setProperties(1, thick).build();
        }
    }

    // Getters
    public int getIndex(){ return index; }
    public int getV1Index(){
        return segment.getV1Idx();
    }
    public int getV2Index(){
        return segment.getV2Idx();
    }
    public java.util.List<Structs.Property> getPropertiesList() {
        return segment.getPropertiesList();
    }
    public Segment getSegment() { return segment; }
    public double getV1X(){ return v1.getX(); }
    public double getV1Y(){ return v1.getY(); }
    public double getV2X(){ return v2.getX(); }
    public double getV2Y(){ return v2.getY(); }


    public MyVertex getV1(){return v1;}
    public MyVertex getV2(){return v2;}

    @Override
    public int getElevation() {
        return (v1.getElevation() + v2.getElevation()) / 2;
    }

    public double getMoisture(){ return (v1.getMoisture() + v2.getMoisture()) / 2; }

    public double getMoistureProvided(){ return (v1.getMoistureProvided() + v2.getMoistureProvided()) / 2; }

    public Point getMidpoint(){
        GeometryFactory geom = new GeometryFactory();
        double x = (getV1X() + getV2X()) / 2;
        double y = (getV1Y() + getV2Y()) / 2;
        return geom.createPoint(new Coordinate(x, y));
    }


}
