package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class InputHandler {

    private final String mode;

    public InputHandler(String mode){
        this.mode = mode;
    }
    
    public Mesh makeMesh(Mesh aMesh){
        LagoonBuilder d = new LagoonBuilder();
        return d.makeLagoon(aMesh);
    }

}
