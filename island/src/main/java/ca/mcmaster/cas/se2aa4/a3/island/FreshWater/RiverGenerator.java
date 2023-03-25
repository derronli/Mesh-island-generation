package ca.mcmaster.cas.se2aa4.a3.island.FreshWater;

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
    public RiverGenerator(List<MyVertex> myVertices, List<MySegment> mySegments, int numRivers, Random rand){
        this.rand = rand;
        int spring;

        for (int i = 0; i < numRivers; i++) {
            spring = this.rand.nextInt(myVertices.size());
            generate(myVertices.get(spring), mySegments);
        }

    }

    private void generate(MyVertex spring, List<MySegment> mySegments){
        MyVertex current = spring;
        MyVertex neighbour;

        while (true) {
            RiverNeighbourExtractor extractor = new RiverNeighbourExtractor(mySegments, current);
            neighbour = extractor.getLowestNeighbour();
            if (current.getElevation() > neighbour.getElevation()) {
                current = neighbour;
            }
        }
    }


}
