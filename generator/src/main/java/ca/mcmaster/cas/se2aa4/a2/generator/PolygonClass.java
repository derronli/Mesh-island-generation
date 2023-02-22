package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolygonClass {
    //pass in segments
    //order the segments based on if they are adjacent
    //calculate the centroid of a polygon based on the segments
    //set colour method and changes all segments in list to said colour
    //consider hashmap
    //
    // centroid getter (return vertex itself)

    private int width = 500;
    private int height = 500;
    private int square_size = 20;
    private static int totalIndex = 0;
    private final int index;

    private List <MySegment> segments;
    private List <Integer> neighbourIndices;
    private MyVertex centroid;

    private Polygon polygon;

    public PolygonClass (List <MySegment> segments){
        this.index = totalIndex;
        totalIndex++;
        //this.segments = segments;
        this.segments = orderSegments(segments);
        calcCentroid();
        initPolygon();
    }
    //create second constructor to take alpha

    public PolygonClass (List <MySegment> segments, int alpha){
        this.index = totalIndex;
        totalIndex++;
        //this.segments = segments;
        this.segments = orderSegments(segments);
        calcCentroid();
        initPolygon();
        setTransparency(alpha);
    }

    private void initPolygon() {
        //centroid index needs to be set
        //need list of segment indices, neighbour indices?
        //generate a list of indices that represent the neighbouring polygons (do in mesh)
        //method to assign a polygon's associated neighbour indices
        //get polygon colour
        //do thickness and transparency of polygon
        //take neighbourIndices and set it using property
        //setting neighbour list


        //add property colour, start with green
        //if alpha, add transparency,

        Property color = Property.newBuilder().setKey("rgb_color").setValue("0,255,0").build(); //set to green
        polygon = Polygon.newBuilder().addAllSegmentIdxs(convertSegments()).addProperties(color).setCentroidIdx(centroid.getIndex()).build(); //pass in polygon in future uses

        Property segment = Property.newBuilder().setKey("").setValue("0,0,0").build();



    }

    private String averageSegColours(){

        int[] red = new int[segments.size()];
        int[] green = new int[segments.size()];
        int[] blue = new int[segments.size()];
        int[] trans = new int[segments.size()];

        for (int i = 0; i < segments.size(); i++){
            String colorCode = PropertyManager.getProperty(segments.get(i).getPropertiesList(), "rbg_color");
            int[] color = PropertyManager.extractColor(colorCode);
            red[i] = color[0];
            green[i] = color[1];
            blue[i] = color[2];

            if (color.length == 4){
                trans[i] = color[3];
            }
            else{
                trans[i] = 255;
            }

        }
        int avRed = Arrays.stream(red).sum() / red.length;
        int avGreen = Arrays.stream(green).sum() / red.length;
        int avBlue = Arrays.stream(blue).sum() / red.length;
        int avTrans = Arrays.stream(trans).sum() / red.length;

        return avRed + "," + avBlue + "," + avGreen + "," + avTrans;
    }

    //return centroid method to add to list of vertices
    //

    private List <Integer> convertSegments (){
        //convert list of segments to indices separated by commas
        List <Integer> convert = new ArrayList<>();

        for (MySegment segment : segments) {
            convert.add(segment.getIndex());
        }
        return convert;
    }

    public MyVertex getCentroid (){
        return centroid;
    }

    private List <MySegment> orderSegments (List <MySegment> segments){
        //checking initial segments list
        System.out.println(segments.toString());

        List <MySegment> orderedSegments = new ArrayList<>();
        orderedSegments.add(segments.get(0));

        //while

        while (orderedSegments.size() != segments.size()){
            for (MySegment segment : segments) {
                if (!orderedSegments.contains(segment)) {
                    MySegment last = orderedSegments.get(orderedSegments.size() - 1);
                    if (last.isAdjacent(segment)) {
                        orderedSegments.add(segment);
                    }
                }
            }
        }

        //checking if segments are ordered
        System.out.println(orderedSegments.toString());
        return orderedSegments;
    }

    private void calcCentroid (){
        double midX = 0;
        double midY = 0;
        for (MySegment segment : segments) {
            midX += segment.getMiddle()[0];
            midY += segment.getMiddle()[1];
        }
        double x = midX/segments.size();
        double y = midY/segments.size();

        centroid = new MyVertex(x,y);
    }

    private void setNeighbourIndices (List <Integer> indices) {
        neighbourIndices.addAll(indices);
    }

    /**
     * Tells if other polygon has a neighbouring segment to this.
     * @param other other polygon we are checking against
     * @return shared segment between the two polygons
     */
    public MySegment isNeighbourSegment(PolygonClass other){

        for (int i = 0; i<other.segments.size(); i++){
            for (int j = 0; j<this.segments.size(); j++){
                if (other.segments.get(i).getIndex() == this.segments.get(j).getIndex()){
                    return this.segments.get(j);
                }
            }
        }
        return null;
    }

    public String getColour (){
        String color = "";
        return color;
    }
    public java.util.List<ca.mcmaster.cas.se2aa4.a2.io.Structs.Property> getPropertiesList() {
        return polygon.getPropertiesList();
    }
    public void setTransparency (int alpha){
        String colorCode = PropertyManager.getProperty(getPropertiesList(), "rgb_color");
        int[] colors = PropertyManager.extractColor(colorCode);
        String newColorCode = colors[0] + "," + colors[1] + "," + colors[2] + "," + alpha;
        Property color = Property.newBuilder().setKey("rgb_color").setValue(newColorCode).build();
        polygon = Polygon.newBuilder(polygon).setProperties(0, color).build();
    }

    public void setThick (int thickness){
        Property thick = Property.newBuilder().setKey("thickness").setValue("" + thickness).build();

        String val = PropertyManager.getProperty(this.getPropertiesList(), "thickness");

        // If thickness property does not already exist.
        if (val == null) {
            polygon = Polygon.newBuilder(polygon).addProperties(thick).build();
        }
        // If thickness value needs to be changed.
        else{
            polygon = Polygon.newBuilder(polygon).setProperties(1, thick).build();
        }
    }

    public boolean isNeighbour(PolygonClass other){
        for (int i = 0; i<other.segments.size(); i++){
            for (int j = 0; j<this.segments.size(); j++){
                if (other.segments.get(i).getIndex() == this.segments.get(j).getIndex()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Changes colour of specific segment named.
     * @param segment segment to have its colour changed in this polygon
     */
    public void changeSegColour(MySegment segment, String colorCode){
        segment.setColour(colorCode);
    }

    /**
     * Sets all the segments in the polygon to the same colour.
     * @param colorCode string colour code to set segments to
     */
    public void setSegmentsColour(String colorCode){
        for (int i = 0; i<segments.size(); i++){
            segments.get(i).setColour(colorCode);
        }
    }

    public void setPolygonColour(String colorCode){
        Property color = Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
        polygon = Polygon.newBuilder(polygon).setProperties(0, color).build();
    }

    /**
     * Sets the centroid to a certain colour.
     * @param colorCode string colour code to set centroid to
     */
    public void setCentroidColour(String colorCode){
        centroid.setColour(colorCode);
    }

    /**
     * Returns if the input list of segments is the same segments this instance contains.
     * @param segments list of segments we are checking against.
     * @return if this polygon represents the input list of segments.
     */
    public boolean equals(List<MySegment> segments) {

        if (this.segments.size() != segments.size()) {
            return false;
        }
        else { //modify this such that we compare every element of this.segments to passed in segments
            for (int i = 0; i < this.segments.size(); i++) {
                if (this.segments.get(i).getIndex() != segments.get(i).getIndex()) {
                    return false;
                }
            }
            return true;
        }
    }

    public void addNeighbour (int index){
        //add to list of neighbours
        neighbourIndices.add(index);
    }

    public int getIndex(){
        return index;
    }

    public Polygon getPolygon() {
        return polygon;
    }
}
