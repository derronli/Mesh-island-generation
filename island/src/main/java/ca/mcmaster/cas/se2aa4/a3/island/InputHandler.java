package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Builders.*;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.*;

import java.util.HashMap;
import java.util.Map;

public class InputHandler {

    private final Map<String, IslandShape> builderOptions = createBuilderOptions();

    private final String mode;

    public InputHandler(String mode){
        this.mode = mode;
    }
    
    private Map<String, IslandShape> createBuilderOptions(){
        int width = 500; int height = 500;
        Map<String, IslandShape> options = new HashMap<>();
        options.put("circle", new Circle(width, height));
        options.put("hexagon", new Hexagon(width, height));

        return options;
    }
    
    public Mesh makeMesh(Mesh aMesh){

        MeshBuilder d;

        // Checks for a lagoon mesh.
        if (mode.equals("lagoon")){
            d = new LagoonBuilder();
            return d.buildIsland(aMesh);
        }

        // Makes a mesh of the specified type if not lagoon mode.
        IslandShape shape = builderOptions.get(mode);
        if (shape == null){
            throw new IllegalArgumentException("mode must be of a valid type. Check documentation for more information.");
        }

        d = new IslandBuilder(shape);
        return d.buildIsland(aMesh);
    }

}
