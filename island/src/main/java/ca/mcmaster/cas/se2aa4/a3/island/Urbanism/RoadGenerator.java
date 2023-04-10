package ca.mcmaster.cas.se2aa4.a3.island.Urbanism;

import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;

import java.util.List;
import java.util.Random;

public class RoadGenerator {


    public void generate(List<MyVertex> citiesList, List<MyVertex> myVertices, List<MySegment> mySegments, Random rand, MapToGraphAdapter graphAdapter) {
        List<MyVertex> roadPath;
        // Get random city from the cities list
        MyVertex capital = citiesList.get(rand.nextInt(citiesList.size()));
        citiesList.remove(capital);
        capital.changeColor("219,112,147"); // Changes capital to be a different colour

        for (MyVertex city : citiesList) {
            roadPath = graphAdapter.pathBetweenTwoCities(myVertices, capital, city);
            createRoads(roadPath, mySegments);
        }

    }

    private void createRoads(List<MyVertex> roadPath, List<MySegment> mySegments) {
        // Create segments between nodes in the path
        for (int i = 0; i < roadPath.size() - 1; i++) {
            MySegment s = new MySegment(roadPath.get(i), roadPath.get(i + 1));
            mySegments.add(s);
        }
    }

}
