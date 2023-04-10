package Tests;

import LectureTestSuite.Assertions;
import LectureTestSuite.tags.Before;
import LectureTestSuite.tags.Test;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.UndirectedEdge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.PathfinderAlgo.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.List;

public class InductiveCaseTest {
    // Dijkstra's pathfinding algorithm is a greedy algorithm
    // It relies on picking the local minimum at each step in order to get the overall global minimum result
    // This test checks if the algorithm is correctly picking the local min
    // Given 2 edges different edges both from the source -> target, we ensure that the one smaller edge is selected
    Graph g = new Graph();

    @Before
    public void setUpGraph() {

        g.addNode(0);
        g.addNode(1);

        g.addEdge(0, 1, 1);
        g.addEdge(0, 1, 100);

    }

    @Test
    public void testInductiveCase() {
        DijkstraShortestPath dj = new DijkstraShortestPath();
        List<Node> path = dj.findPath(g.getAdjList(), new Node(0), new Node(1));
        // Construct edge from the path


        // correct node path should be 0 -> 1 with weight 1
        // Ensure the weight to the target is only 1
        Assertions.assertEquals(dj.getDistance(new Node(1)), 1);
    }
}
