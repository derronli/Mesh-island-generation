package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Builders.*;
import ca.mcmaster.cas.se2aa4.a3.island.Heatmaps.*;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.*;

public class InputHandler {

    private final String mode;

    public InputHandler(String mode){
        this.mode = mode;
    }
    
    public Mesh makeMesh(Mesh aMesh, String elevation){
        MeshBuilder d = buildIsland(aMesh, elevation);
        return d.getIsland();
    }

    private MeshBuilder buildIsland(Mesh aMesh, String elevation){
        MeshBuilder d;

        // Checks for a lagoon mesh.
        if (mode.equals("lagoon")){
            d = new LagoonBuilder();
            d.buildIsland(aMesh);
        }
        else {
            // Makes a mesh of the specified type if not lagoon mode.
            IslandShapeFactory islandShapeFactory = new IslandShapeFactory();
            IslandShape shape = islandShapeFactory.getIslandShape(mode);
            d = new IslandBuilder(shape);
            d.buildIsland(aMesh);
            ElevationFactory elevationFactory = new ElevationFactory();
            ((IslandBuilder) d).constructElevation(elevationFactory.getElevation(elevation));
        }
        return d;
    }

    public Mesh makeMesh(Mesh aMesh, String heatmap, String elevation){
        MeshBuilder d = buildIsland(aMesh, elevation);
        HeatmapFactory heatFactory = new HeatmapFactory();
        HeatmapPainter heatmapPainter = heatFactory.getHeatmap(heatmap);
        d.applyHeatmap(heatmapPainter);
        return d.getIsland();
    }

}
