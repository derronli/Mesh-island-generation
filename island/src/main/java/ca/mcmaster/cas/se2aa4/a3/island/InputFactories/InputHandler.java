package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Builders.*;
import ca.mcmaster.cas.se2aa4.a3.island.Heatmaps.*;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.*;

import java.util.HashMap;
import java.util.Map;

public class InputHandler {

    private final String mode;

    public InputHandler(String mode){
        this.mode = mode;
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
            IslandShapeFactory islandShapeFactory = new IslandShapeFactory();
            IslandShape shape = islandShapeFactory.getIslandShape(mode);
            d = new IslandBuilder(shape);
        }

        d.buildIsland(aMesh);
        return d;
    }

    public Mesh makeMesh(Mesh aMesh, String heatmap){
        MeshBuilder d = buildIsland(aMesh);
        HeatmapFactory heatFactory = new HeatmapFactory();
        HeatmapPainter heatmapPainter = heatFactory.getHeatmap(heatmap);
        d.applyHeatmap(heatmapPainter);
        return d.getIsland();
    }

}
