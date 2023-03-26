package ca.mcmaster.cas.se2aa4.a3.island.FreshWater;

import ca.mcmaster.cas.se2aa4.a3.island.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.MyVertex;

import java.util.ArrayList;
import java.util.List;

public class RiverNeighbourExtractor {

    private List<MyVertex> vertexNeighbours = new ArrayList<>();
    private List<MySegment> attachedSegments = new ArrayList<>();
    private MyVertex lowestNeighbour;

    public RiverNeighbourExtractor(List<MySegment> mySegments, MyVertex vertex) {
        exec(mySegments, vertex);
        findLowestNeighbour();
    }

    private void exec(List<MySegment> mySegments, MyVertex vertex){
        for (MySegment s : mySegments) {
            // V1 = vertex
            if (s.getV1X() == vertex.getX() && s.getV1Y() == vertex.getY()) {
                attachedSegments.add(s);
                vertexNeighbours.add(s.getV2());
            }
            // V2 = vertex
            else if (s.getV2X() == vertex.getX() && s.getV2Y() == vertex.getY()) {
                attachedSegments.add(s);
                vertexNeighbours.add(s.getV1());
            }

        }
    }

    private void findLowestNeighbour() {
        lowestNeighbour = vertexNeighbours.get(0);

        for (MyVertex vertex : vertexNeighbours) {
            if (lowestNeighbour.getElevation() > vertex.getElevation()) {
                lowestNeighbour = vertex;
            }
        }
    }

    // Find the segment that connects the current vertex with the lowest neighbour
    // Will only find the river path for the lowestNeighbour, no reason to do for the others
    // As it is not possible to go down a non-lowest path
    public MySegment getRiverSegmentPath() {
        for (MySegment s : attachedSegments) {
            if ((s.getV1X() == lowestNeighbour.getX() && s.getV1Y() == lowestNeighbour.getY()) ||
            (s.getV2X() == lowestNeighbour.getX() && s.getV2Y() == lowestNeighbour.getY())) {
                return s;
            }
        }
        return null;
    }

    public MyVertex getLowestNeighbour() { return lowestNeighbour; }


}
