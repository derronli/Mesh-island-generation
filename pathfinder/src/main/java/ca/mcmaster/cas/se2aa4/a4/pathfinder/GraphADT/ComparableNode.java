package ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT;

import java.util.Comparator;

public class ComparableNode implements Comparator<ComparableNode> {

    public int cost;
    private Node node;

    public ComparableNode() {}

    public ComparableNode(Node node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    public Node getNode() {
        return node;
    }

    @Override
    public int compare(ComparableNode o1, ComparableNode o2) {
        if (o1.cost < o2.cost) {
            return -1;
        }
        else if (o1.cost > o2.cost) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
