package ca.mcmaster.cas.se2aa4.a3.island.FreshWater;

import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.MyVertex;
import org.locationtech.jts.geom.Coordinate;

import java.util.ArrayList;
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
    public RiverGenerator(List<MyPolygon> landPolygons, List<MyPolygon> allPolygons, List<MySegment> mySegments, int numRivers, Random rand){
        this.rand = rand;
        int polygonidx, riverDischarge, randomSegmentIdx;
        MySegment segment;
        MyVertex spring;
        MyPolygon polygon;

        while (numRivers != 0) {
            polygonidx = this.rand.nextInt(landPolygons.size());
            polygon = landPolygons.get(polygonidx);
            randomSegmentIdx = this.rand.nextInt(polygon.getSegmentIdxsList().size());
            segment = polygon.getSegmentByIndex(0);
            spring = segment.getV1();

            // Starting river discharge is randomly set between 1 - 3
            riverDischarge = this.rand.nextInt(3 - 1) + 1;

            // Ensure the starting point is valid
            if (spring.makeRiverVertex(riverDischarge)) {
                numRivers--;
                generate(spring, mySegments, allPolygons, riverDischarge);
            }
        }
    }

    private void generate(MyVertex spring, List<MySegment> mySegments, List<MyPolygon> allPolygons, int startingDischarge){
        MyVertex current = spring;
        MyVertex neighbour;
        MySegment path;
        MyPolygon endoLakeLoc;
        List<MyPolygon> composedPolygons;

        while (true) {
            RiverNeighbourExtractor extractor = new RiverNeighbourExtractor(mySegments, current);
            neighbour = extractor.getLowestNeighbour();

            // These are the polygons that are made from the current vertex (adjacent polygons)
            // We want to account for ocean polygons here as well
            composedPolygons = findPolygons(allPolygons, current);

            // Check if we have reached ocean yet (ocean elevation is default of -1)
            if (isReachedOcean(composedPolygons) || neighbour.getElevation() == -1) {
                break;
            }

            if (current.getElevation() > neighbour.getElevation()) {

                // if neighbour is already a river
                if (!neighbour.makeRiverVertex(startingDischarge)) {
                    neighbour.addToDischarge(startingDischarge); // add onto the preexisting discharge
                }
                // else -> the river vertex will be made by the method

                path = extractor.getRiverSegmentPath();
                path.changeColor("135,206,235");
                path.setThick(current.getDischarge());

                current = neighbour;
            }
            // No lower elevation for river to continue
            else {
                // Create an endo lake
                endoLakeLoc = findLakeLocation(composedPolygons);
                endoLakeLoc.tryChangeTileToLake();
                break;
            }
        }
    }

    // Finds the list of polygons which are made up of the input vertex
    private List<MyPolygon> findPolygons(List<MyPolygon> myPolygons, MyVertex vertex) {
        List<MyPolygon> adjPolygons = new ArrayList<>();
        Coordinate coord = convertVertexToCoordinate(vertex);

        for (MyPolygon p : myPolygons) {
            if (p.containsCoordinate(coord)) {
                adjPolygons.add(p);
            }
        }
        return adjPolygons;
    }

    // Check if any of the adjacent polygons are ocean

    private boolean isReachedOcean(List<MyPolygon> adjacentPolygons) {
        for (MyPolygon p : adjacentPolygons) {
            if (!p.isIsland()) {
                return true;
            }
        }
        return false;
    }

    private MyPolygon findLakeLocation(List<MyPolygon> adjacentPolygons) {
        MyPolygon lowest = adjacentPolygons.get(0);

        // Finds the lowest elevation polygon that is adjacent to the current vertex
        for (MyPolygon neighbour : adjacentPolygons) {
            if (lowest.getElevation() > neighbour.getElevation()) {
                lowest = neighbour;
            }
        }

        // check if any neighbours are a water source, if so -> the endo lake will just be this water source instead
        // (prevents the creation of an endo lake right beside a normal lake)
        for (MyPolygon neighbour : adjacentPolygons) {
            if (neighbour.isWaterTile()) {
                return neighbour;
            }
        }

        return lowest;
    }

    private Coordinate convertVertexToCoordinate (MyVertex v){
        int x = (int) v.getX();
        int y = (int) v.getY();

        return new Coordinate(x,y);
    }

}
