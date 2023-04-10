package ca.mcmaster.cas.se2aa4.a3.island.Urbanism;

import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT.Node;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.PathfinderAlgo.DijkstraShortestPath;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.List;

public class MapToGraphAdapter {
    private final Graph graph = new Graph();

    public MapToGraphAdapter(List<MyPolygon> myPolygons, List<MyVertex> myVertices) {
        makeNodes(myPolygons, myVertices);
        makeEdges(myPolygons, myVertices);
    }

    private void makeNodes(List<MyPolygon> myPolygons, List<MyVertex> myVertices) {
        // Iterate through all polygons on the island
        // Ensure they are not Lake tile types
        // Make each into a node
        for (MyPolygon p : myPolygons) {
            if (p.isWaterTile()) {
                continue;
            }
            // ID of the node will correlate with the index of the vertex
            MyVertex centroid = findVertex(myVertices, p.getCenterOfPolygon());
            graph.addNode(centroid.getIndex());
        }

    }

    private void makeEdges(List<MyPolygon> myPolygons, List<MyVertex> myVertices) {
        // Iterate through each polygon, adding edges between all neighbours
        for (MyPolygon p : myPolygons) {
            if (p.isWaterTile()) {
                continue;
            }
            // We don't need to worry about duplicate edges due to how addEdge was constructed
            for (MyPolygon neighbour : p.getNeighbours()) {
                if (p.isWaterTile()) {
                    continue;
                }
                int distance = distanceFormula(p, neighbour);
                MyVertex pVertex = findVertex(myVertices, p.getCenterOfPolygon());
                MyVertex neighbourVertex = findVertex(myVertices, neighbour.getCenterOfPolygon());

                graph.addEdge(pVertex.getIndex(), neighbourVertex.getIndex(), distance);
            }
        }
    }

    public List<MyVertex> pathBetweenTwoCities(List<MyVertex> myVertices, MyVertex sourceVertex, MyVertex targetVertex) {
        DijkstraShortestPath dj = new DijkstraShortestPath();

        // Convert the source and target vertices to nodes in the graph
        // If the nodes already exist in the graph, they will just return the preexisting object
        Node source = new Node(sourceVertex.getIndex());
        Node target = new Node(targetVertex.getIndex());

        // Return type will be a list of nodes
        List<Node> pathNodes = dj.findPath(graph.getAdjList(), source, target);

        // readapt the return type to be vertices
        return convertPathToVertices(pathNodes, myVertices);
    }

    private List<MyVertex> convertPathToVertices(List<Node> pathNodes, List<MyVertex> myVertices) {
        List <MyVertex> pathVertices = new ArrayList<>();
        MyVertex v;

        for (Node n : pathNodes) {
            // The id of the node corresponds to index of vertex
            v = myVertices.get(n.getId());
            pathVertices.add(v);
        }

        return pathVertices;
    }

    private int distanceFormula(MyPolygon p1, MyPolygon p2) {
        return (int) Math.round(Math.sqrt(Math.pow(p2.getCenterOfPolygon().getX() - p1.getCenterOfPolygon().getX(), 2) + Math.pow(p2.getCenterOfPolygon().getY() - p1.getCenterOfPolygon().getY(), 2)));
    }

    private MyVertex findVertex(List<MyVertex> myVertices, Point target) {
        for (MyVertex v : myVertices) {
            if (v.getX() == target.getX() && v.getY() == target.getY()) {
                return v;
            }
        }
        return null;
    }

}
