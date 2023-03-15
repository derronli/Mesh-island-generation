package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;

import java.util.ArrayList;
import java.util.List;

public class MyPolygon implements MyShape{

    private static int totalIndex = 0;
    private final int index;
    private Polygon polygon;
    private Tile myTile;
    private List <MySegment> segments = new ArrayList<>();
    private List <double[]> vertices = new ArrayList<>();

    public MyPolygon(Polygon p){
        polygon = p;
        index = totalIndex;
        totalIndex++;
    }

    private void initPolygon(){

    }

    // Orders the segments and sets the vertices list.
    public void orderSegments (){
        List <MySegment> orderedSegments = new ArrayList<>();
        MySegment firstSeg = segments.get(0);
        orderedSegments.add(firstSeg);
        vertices.add(new double[]{firstSeg.getV1X(), firstSeg.getV1Y()});
        vertices.add(new double[]{firstSeg.getV2X(), firstSeg.getV2Y()});
        int count = 0;

        // Orders segments and adds vertices in order to vertices list.
        while (count < segments.size()){
            for (MySegment segment : segments) {
                if (!orderedSegments.contains(segment)) {
                    MySegment last = orderedSegments.get(orderedSegments.size() - 1);
                    int commonVertex = last.isAdjacent(segment);
                    if (commonVertex != 0) {

                        // If we are adding the second segment, checks the vertices on both to ensure proper ordering.
                        if (orderedSegments.size() == 1){
                            // First adds the vertex not in common, then the vertex in common.
                            double[] firstVertex = (commonVertex == last.getV1Index()) ? new double[] {last.getV2X(), last.getV2Y()} : new double[] {last.getV1X(), last.getV1Y()};
                            double[] secondVertex = (commonVertex == last.getV1Index()) ? new double[] {last.getV1X(), last.getV1Y()} : new double[] {last.getV2X(), last.getV2Y()};
                            vertices.add(firstVertex); vertices.add(secondVertex);
                        }

                        // Next adds the vertex not already in the list which is in the new segment.
                        commonVertex = segment.isAdjacent(last);
                        double[] newVertex = (commonVertex == segment.getV1Index()) ? new double[] {segment.getV2X(), segment.getV2Y()} : new double[] {segment.getV1X(), segment.getV1Y()};
                        vertices.add(newVertex);

                        orderedSegments.add(segment);
                    }
                }
            }
            count++;
        }
        segments = orderedSegments;
    }


    public void changeColor(String colorCode){
//        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue(colorCode).build();
//        polygon = Polygon.newBuilder(polygon).setProperties(0, color).build();
    }

    // Adds segment to segment list.
    public void addSegment(MySegment s){
        segments.add(s);
    }

    // Getters
    public int getIndex(){ return index; }
    public Polygon getPolygon() {
        return polygon;
    }
    public List<Integer> getSegmentIdxsList(){ return polygon.getSegmentIdxsList(); }
}
