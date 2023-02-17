package ca.mcmaster.cas.se2aa4.a2.generator;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.util.ArrayList;
import java.util.List;

public class PolygonClass {
    //pass in segments
    //order the segments based on if they are adjacent
    //calculate the centroid of a polygon based on the segments
    //set colour method and changes all segments in list to said colour
    //consider hashmap
    //
    // centroid getter

    private int width = 500;
    private int height = 500;
    private int square_size = 20;
    private static int totalIndex = 0;
    private final int index;

    private List <MySegment> segments;
    private MyVertex centroid;

    public PolygonClass (List <MySegment> segments){
        this.index = totalIndex;
        totalIndex++;
        this.segments = segments;
    }
    private void orderSegments (){
        //checking initial segments list
        System.out.println(segments.toString());

        boolean flag = true;
        List <MySegment> orderedSegments = new ArrayList<>();

        orderedSegments.add(segments.get(0));

        if (segments.size() == 0){
            System.out.println("Edge case, might remove later");
        }
        else {
            //while
            for (int j = 1; j<segments.size(); j++){
                MySegment temp = orderedSegments.get(0);
                if (temp.isAdjacent(segments.get(j))){
                    orderedSegments.add(segments.get(j));
                    segments.set(j, null);
                    break;
                }
            }
        }

    }

    private void calcCentroid (){

    }

    /**
     * Tells if other polygon has a neighbouring segment to this.
     * @param other other polygon we are checking against
     * @return shared segment between the two polygons
     */
    public MySegment isNeighbour(PolygonClass other){

        return null;
    }

    /**
     * Changes colour of specific segment named.
     * @param segment segment to have its colour changed in this polygon
     */
    public void changeSegColour(MySegment segment){

    }

    /**
     * Sets all the segments in the polygon to the same colour.
     * @param colorCode string colour code to set segments to
     */
    public void setPolyColour(String colorCode){

    }

    /**
     * Sets the centroid to a certain colour.
     * @param colorCode string colour code to set centroid to
     */
    public void setCentroidColour(String colorCode){

    }

    /**
     * Returns if the input list of segments is the same segments this instance contains.
     * @param segments list of segments we are checking against.
     * @return if this polygon represents the input list of segments.
     */
    public boolean equals(List<MySegment> segments){
        return true;
    }


}
