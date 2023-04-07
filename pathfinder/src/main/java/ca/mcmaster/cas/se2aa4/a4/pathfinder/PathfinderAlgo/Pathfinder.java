package ca.mcmaster.cas.se2aa4.a4.pathfinder.PathfinderAlgo;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.UndirectedEdge;

import java.util.List;
import java.util.Map;

public interface Pathfinder {

    List<Node> findPath(Map<Node, List<UndirectedEdge>> adjList, Node src, Node target);
}
