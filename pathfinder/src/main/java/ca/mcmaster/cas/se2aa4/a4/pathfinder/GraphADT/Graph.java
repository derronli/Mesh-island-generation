package ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT;

import java.util.*;

public class Graph {
    private Map<Node, List<UndirectedEdge>> adjList = new HashMap<>();

    // Maybe create a constructor that can make the graph from an edge list?

    public void addNode(int id) {
        adjList.putIfAbsent(new Node(id), new ArrayList<>());
    }

    public void removeNode(int id) {
        // Make a temporary node for sake of looking it up in the hashmap
        Node tempLookup = new Node(id);
        adjList.remove(tempLookup);
    }

    // Input must be 2 preexisting nodes in the graph
    public void addEdge(int idn, int idm, int weight) {
        Node n = new Node(idn);
        Node m = new Node(idm);
        UndirectedEdge newEdge = new UndirectedEdge(n, m, weight);

        // Ensure that edge doesn't already exist
        for (UndirectedEdge neighbourEdges : adjList.get(n)) {
            if (neighbourEdges.equals(newEdge)) {
                return;
            }
        }
        // Undirected graph
        adjList.get(n).add(newEdge);
        adjList.get(m).add(newEdge);
    }

    public void removeEdge(int idn, int idm) {
        Node n = new Node(idn);
        Node m = new Node(idm);
        UndirectedEdge edge = new UndirectedEdge(n, m, 1);

        adjList.get(n).remove(edge);
        adjList.get(m).remove(edge);
    }

    public void printGraph() {
        Set<Node> nodes = adjList.keySet();
        for (Node n : nodes) {
            System.out.print("Node: " + n.getId() + "\tNeighbours:");
            for (UndirectedEdge adj : adjList.get(n)) {
                System.out.print(" " + adj.getOtherNode(n).getId());
            }
            System.out.println("");
        }
    }

    public Map<Node, List<UndirectedEdge>> getAdjList() {
        return adjList;
    }
}
