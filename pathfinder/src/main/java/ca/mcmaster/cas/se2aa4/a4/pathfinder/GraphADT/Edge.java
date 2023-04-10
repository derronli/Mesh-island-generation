package ca.mcmaster.cas.se2aa4.a4.pathfinder.GraphADT;

public interface Edge {

    Node getN();

    Node getM();

    Node getOtherNode(Node node);

    boolean equals(Object obj);
}
