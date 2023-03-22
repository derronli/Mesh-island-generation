package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Builders.*;
import ca.mcmaster.cas.se2aa4.a3.island.Heatmaps.*;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.*;

import java.util.HashMap;
import java.util.Map;

public class InputHandler {
    private final Map<String, HeatmapPainter> heatmapOptions = createHeatmapOptions();



    private final String mode;

    public InputHandler(String mode){
        this.mode = mode;
    }

    private Map<String, HeatmapPainter> createHeatmapOptions(){
        Map<String, HeatmapPainter> options = new HashMap<>();
        options.put("elevation", new ElevationPainter());
        options.put("moisture", new MoisturePainter());

        return options;
    }
    
    public Mesh makeMesh(Mesh aMesh){
        MeshBuilder d = buildIsland(aMesh);
        return d.getIsland();
    }

    private MeshBuilder buildIsland(Mesh aMesh){
        MeshBuilder d;

        // Checks for a lagoon mesh.
        if (mode.equals("lagoon")){
            d = new LagoonBuilder();
        }
        else {
            // Makes a mesh of the specified type if not lagoon mode.
            BuilderFactory builderFactory = new BuilderFactory();
            IslandShape shape = builderFactory.getIslandShape(mode);
            d = new IslandBuilder(shape);
        }

        d.buildIsland(aMesh);
        return d;
    }

    public Mesh makeMesh(Mesh aMesh, String heatmap){
        MeshBuilder d = buildIsland(aMesh);
        HeatmapPainter heatmapPainter = heatmapOptions.get(heatmap);
        d.applyHeatmap(heatmapPainter);
        return d.getIsland();
    }

}
