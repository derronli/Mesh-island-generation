package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.Node;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Graph g = new Graph();

        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(0);

        g.addEdge(0, 1);
        g.addEdge(1, 3);
        g.addEdge(0, 2);
        g.addEdge(3, 2);

        g.addEdge(0, 3);
        g.removeEdge(3, 0);

        g.printGraph();

    }
}
