package ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT;

import java.util.Objects;

public class Node {
    private final int id;

    public Node(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    // Override equals so it will be defined by if the ids are equivalent
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Node)) {
            return false;
        }
        Node n = (Node) obj;

        return id == n.getId();

    }
    // Override hashcode so hashcodes are all generated based on id
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
