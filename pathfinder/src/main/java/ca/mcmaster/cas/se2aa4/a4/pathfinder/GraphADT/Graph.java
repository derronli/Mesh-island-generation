package ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT;

import java.util.*;

public class Graph {
    private Map<Node, List<Node>> adjList = new HashMap<>();

    public void addNode(int id) {
        adjList.putIfAbsent(new Node(id), new ArrayList<>());
    }

    public void removeNode(int id) {
        // Make a temporary node for sake of looking it up in the hashmap
        Node tempLookup = new Node(id);
        adjList.remove(tempLookup);
    }

    // Input must be 2 preexisting nodes in the graph
    public void addEdge(int idn, int idm) {
        Node n = new Node(idn);
        Node m = new Node(idm);

        // Ensure that edge doesn't already exist
        for (Node neighbour : adjList.get(n)) {
            if (neighbour.equals(m)) {
                return;
            }
        }
        // Undirected graph
        adjList.get(n).add(m);
        adjList.get(m).add(n);
    }

    public void removeEdge(int idn, int idm) {
        Node n = new Node(idn);
        Node m = new Node(idm);

        adjList.get(n).remove(m);
        adjList.get(m).remove(n);
    }

    public void printGraph() {
        Set<Node> nodes = adjList.keySet();
        for (Node n : nodes) {
            System.out.print("Node: " + n.getId() + "\tNeighbours:");
            for (Node adj : adjList.get(n)) {
                System.out.print(" " + adj.getId());
            }
            System.out.println("");
        }
    }
}
