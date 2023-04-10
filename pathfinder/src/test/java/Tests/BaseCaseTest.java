package Tests;

import LectureTestSuite.tags.Before;
import LectureTestSuite.tags.Test;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.PathfinderAlgo.DijkstraShortestPath;
import LectureTestSuite.Assertions;

import java.util.List;

public class BaseCaseTest {
    Graph g = new Graph();

    @Before
    public void setUpGraph() {

        g.addNode(0);
        g.addNode(1);
        g.addNode(2);

        g.addEdge(0, 1, 100);
        g.addEdge(0, 2, 1);
        g.addEdge(2, 1, 2);
    }

    @Test
    public void testBaseCase() {
        DijkstraShortestPath dj = new DijkstraShortestPath();
        List<Node> path = dj.findPath(g.getAdjList(), new Node(0), new Node(1));
        // Correct path would be 0 -> 2 -> 1
        Assertions.assertEquals(path.size(), 3);
    }
}
