package ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT;

import java.util.HashMap;
import java.util.Objects;
import java.util.Map;

public class Node {
    private final int id;
    private Map<String, String> properties = new HashMap<>();

    public Node(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public void setProperty(String property, String value) {
        properties.put(property, value);
    }
    public String getProperty(String property) {
        return properties.get(property);
    }

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
