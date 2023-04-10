package Tests;

import LectureTestSuite.Assertions;
import LectureTestSuite.tags.Before;
import LectureTestSuite.tags.Test;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.PathfinderAlgo.DijkstraShortestPath;

import java.util.List;

public class NegativeEdgeTest {
    // This is a boundary case
    Graph g = new Graph();

    @Before
    public void setUpGraph() {

        g.addNode(0);
        g.addNode(1);
        g.addNode(2);

        g.addEdge(0, 1, 10);
        g.addEdge(0, 2, -5);
        g.addEdge(1, 2, 5);

    }

    @Test
    public void testInductiveCase() {
        DijkstraShortestPath dj = new DijkstraShortestPath();
        List<Node> path = dj.findPath(g.getAdjList(), new Node(0), new Node(1));
        // Construct edge from the path

        // correct node path should be 0 -> 2 -> 1 with weight 0
        // Ensure the weight to the target is only 0
        Assertions.assertEquals(dj.getDistance(new Node(1)), 0);
    }
}
