package ca.mcmaster.cas.se2aa4.a3.island.Builders;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public interface MeshBuilder {

    void buildIsland(Mesh aMesh);
    Mesh getIsland();

}
