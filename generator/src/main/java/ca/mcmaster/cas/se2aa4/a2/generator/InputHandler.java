package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class InputHandler {

    private final int VERTEXTHICKNESS = 3;
    private final float POLYSEGTHICKNESS = 0.5f;

    public Mesh createMesh(String[] args){
        // logic to get options.
        DotGen generator = new DotGen();
        return generator.generate();
    }

}
