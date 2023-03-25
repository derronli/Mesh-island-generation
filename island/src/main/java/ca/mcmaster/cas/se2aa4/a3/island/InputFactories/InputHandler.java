package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Builders.*;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.BaseElevation;
import ca.mcmaster.cas.se2aa4.a3.island.Heatmaps.HeatmapPainter;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;

public class InputHandler {

    private final String mode;

    public InputHandler(String mode){
        this.mode = mode;
    }

    // Makes a regular island.
    public Mesh makeMesh(Mesh aMesh, String elevation){
        IslandCreator islandCreator = new IslandCreator();
        MeshBuilder builder = getBuilder();

        // Checks if using an island builder or lagoon builder.
        if (builder.getClass() == IslandBuilder.class){
            BaseElevation elevationProfile = getElevationProfile(elevation);
            return islandCreator.createIsland((IslandBuilder) builder, aMesh, elevationProfile);
        }

        // Returns the lagoon if just using a lagoon builder.
        return islandCreator.createIsland(builder, aMesh);
    }

    private IslandShape getIslandShape(){
        IslandShapeFactory islandShapeFactory = new IslandShapeFactory();
        return islandShapeFactory.getIslandShape(mode);
    }

    private BaseElevation getElevationProfile(String elevation){
        ElevationFactory elevationFactory = new ElevationFactory();
        return elevationFactory.getElevation(elevation);
    }

    private HeatmapPainter getHeatmapPainter(String heatmap){
        HeatmapFactory heatFactory = new HeatmapFactory();
        return heatFactory.getHeatmap(heatmap);
    }

    private MeshBuilder getBuilder(){
        MeshBuilder builder;
        // Checks for a lagoon mesh.
        if (mode.equals("lagoon")){
            builder = new LagoonBuilder();
        }
        else {
            // Makes a mesh of the specified type if not lagoon mode.
            builder = new IslandBuilder(getIslandShape());
        }

        return builder;

    }

    // Makes an island with a heatmap, unless making a lagoon.
    public Mesh makeMesh(Mesh aMesh, String heatmap, String elevation){
        IslandCreator islandCreator = new IslandCreator();
        MeshBuilder builder = getBuilder();

        // Checks if using an island builder or lagoon builder.
        if (builder.getClass() == IslandBuilder.class){
            BaseElevation elevationProfile = getElevationProfile(elevation);
            HeatmapPainter heatmapPainter = getHeatmapPainter(heatmap);
            return islandCreator.createIsland((IslandBuilder) builder, aMesh, elevationProfile, heatmapPainter);
        }

        // Returns the lagoon if just using a lagoon builder.
        return islandCreator.createIsland(builder, aMesh);
    }

}
