package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Builders.*;

import java.util.HashMap;
import java.util.Map;

public class InputHandler {

    private Map<String, IslandBuilder> builderOptions = new HashMap<String, IslandBuilder>();

    private final String mode;

    public InputHandler(String mode){
        this.mode = mode;
    }
    
    public Mesh makeMesh(Mesh aMesh){
        LagoonBuilder d = new LagoonBuilder();
        return d.buildIsland(aMesh);
    }

}
