package ca.mcmaster.cas.se2aa4.a4.pathfinder.PathfinderAlgo;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.ComparableNode;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.UndirectedEdge;

import java.util.*;

public class DijkstraShortestPath implements Pathfinder {

    @Override
    public List<Node> findPath(Map<Node, List<UndirectedEdge>> adjList, Node src, Node target) {
        PriorityQueue<ComparableNode> pqueue = new PriorityQueue<>(adjList.size(), new ComparableNode());
        Map<Node, Integer> distance = new HashMap<>();
        Map<Node, Node> path = new HashMap<>();
        Set<Node> visited = new HashSet<>();

        // Setting up the distance and path maps
        for (Node n : adjList.keySet()) {
            distance.put(n, Integer.MAX_VALUE);
            path.put(n, null);
        }
        distance.put(src, 0);
        pqueue.add(new ComparableNode(src, 0));

        while (pqueue.size() != 0) {
            Node currNode = pqueue.remove().getNode();
            visited.add(currNode);

            for (UndirectedEdge edge : adjList.get(currNode)) {
                Node neighbourNode = edge.getOtherNode(currNode);

                // Don't process a node if it's already been visited
                if (visited.contains(neighbourNode)) {
                    continue;
                }

                int newPathCost = distance.get(currNode) + edge.getWeight();
                if (newPathCost < distance.get(neighbourNode)) {
                    distance.put(neighbourNode, newPathCost);
                    path.put(neighbourNode, currNode);
                    pqueue.add(new ComparableNode(neighbourNode, newPathCost));
                }
            }
        }
        Node pointer = target;
        List<Node> shortestPath = new ArrayList<>();

        while (pointer != null) {
            shortestPath.add(pointer);
            pointer = path.get(pointer);
        }

        Collections.reverse(shortestPath);

        return shortestPath;
    }
}
