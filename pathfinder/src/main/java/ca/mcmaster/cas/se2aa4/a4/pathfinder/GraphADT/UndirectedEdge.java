package ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT;

public class UndirectedEdge {
    private final Node n;
    private final Node m;
    private final int weight;

    public UndirectedEdge(Node n, Node m, int weight) {
        this.n = n;
        this.m = m;
        this.weight = weight;
    }

    public Node getN() {
        return n;
    }

    public Node getM() {
        return m;
    }

    // Given some node, return the other node in the edge
    public Node getOtherNode(Node node) {
        if (n.equals(node)) {
            return m;
        }
        else {
            return n;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UndirectedEdge)) {
            return false;
        }

        UndirectedEdge e = (UndirectedEdge) obj;

        return (this.n.equals(e.n) || this.m.equals(e.n)) && (this.n.equals(e.m) || this.m.equals(e.m));

    }
}
