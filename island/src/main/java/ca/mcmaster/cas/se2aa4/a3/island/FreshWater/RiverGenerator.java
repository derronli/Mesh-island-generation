package ca.mcmaster.cas.se2aa4.a3.island.FreshWater;

import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.MyVertex;

import java.util.List;
import java.util.Random;

public class RiverGenerator {

    // Input: list of myVertices, command line args

    // Perform the following x number of times, where x is the number specified by args

    // Pick any random vertex from myVertices
    // vertice list = new ExtractNeighbourVertices.getNeighbours
    // findNextVertex() {
    // iterate through vertice list, looking for the smallest elevation.
    // Check if smallest elevation < current Vertex elevation
        // Yes -> set the current node =
    private final Random rand;
    public RiverGenerator(List<MyPolygon> myPolygons, List<MySegment> mySegments, int numRivers, Random rand){
        this.rand = rand;
        int polygonidx;
        MySegment segment;
        MyVertex spring;

        while (numRivers != 0) {
            polygonidx = this.rand.nextInt(myPolygons.size());
            segment = myPolygons.get(polygonidx).getSegmentByIndex(0);
            spring = segment.getV1();

            // Ensure the starting point is valid
            if (spring.makeRiverVertex()) {
                numRivers--;
                generate(spring, mySegments);
            }
        }
    }

    private void generate(MyVertex spring, List<MySegment> mySegments){
        MyVertex current = spring;
        MyVertex neighbour;
        MySegment path;

        while (true) {
            RiverNeighbourExtractor extractor = new RiverNeighbourExtractor(mySegments, current);
            neighbour = extractor.getLowestNeighbour();
            // Check if we have reached ocean yet
            if (neighbour.getElevation() == -1) {
                break;
            }

            if (current.getElevation() > neighbour.getElevation()) {
                neighbour.makeRiverVertex(); // Will need to edit later -> this would be the case we combine rivers
                path = extractor.getRiverSegmentPath();
                path.changeColor("135,206,235");

                current = neighbour;
            }
            else { // What happens if we hit ocean????
                break;
                // Create endo lake
            }
        }
    }


}
