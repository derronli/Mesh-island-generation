package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.PathfinderAlgo.DijkstraShortestPath;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Graph g = new Graph();

//        g.addNode(0);
//        g.addNode(1);
//        g.addNode(2);
//        g.addNode(3);
//        g.addNode(4);
//
//        g.addNode(5);
//        g.removeNode(5);
//        g.addEdge(0, 4, 10);
//        g.removeEdge(0, 4);
//
//        g.addEdge(0, 1, 6);
//        g.addEdge(0, 3, 1);
//        g.addEdge(1, 3, 2);
//        g.addEdge(1, 4, 2);
//        g.addEdge(3, 4, 1);
//        g.addEdge(1, 2, 5);
//        g.addEdge(4, 2, 5);
//
//        g.addEdge(2, 4, 12);
//
//        g.printGraph();

        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(5);

        g.addEdge(0, 1, 2);
        g.addEdge(0, 3, 8);
        g.addEdge(1, 3, 5);
        g.addEdge(1, 4, 6);
        g.addEdge(4, 3, 3);
        g.addEdge(5, 3, 2);
        g.addEdge(5, 4, 1);
        g.addEdge(4, 2, 9);
        g.addEdge(2, 5, 3);

        DijkstraShortestPath dj = new DijkstraShortestPath();

        List<Node> path = dj.findPath(g.getAdjList(), new Node(0), new Node(2));

        // Print path
        System.out.print("\nPath: ");
        for (Node n : path) {
            System.out.print(" " + n.getId());
        }
    }
}
