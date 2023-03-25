package ca.mcmaster.cas.se2aa4.a3.island.Builders;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.BaseElevation;
import ca.mcmaster.cas.se2aa4.a3.island.Heatmaps.HeatmapPainter;
import ca.mcmaster.cas.se2aa4.a3.island.Humidity.SoilProfile;

public class IslandCreator {

    // Creates an island with a heatmap.
    public Mesh createIsland(IslandBuilder islandBuilder, Mesh aMesh, BaseElevation elevation, HeatmapPainter heatmap, SoilProfile soilProfile){
        constructBaseIsland(islandBuilder, aMesh, elevation, soilProfile);
        islandBuilder.applyHeatmap(heatmap);
        return islandBuilder.getIsland();
    }

    // Does the basic construction for an island, including moisture and elevation.
    private void constructBaseIsland(IslandBuilder islandBuilder, Mesh aMesh, BaseElevation elevation, SoilProfile soilProfile){
        islandBuilder.buildIsland(aMesh);
        islandBuilder.addMoistureToPolygons(soilProfile);
        islandBuilder.constructElevation(elevation);
    }

    // Creates a regular island.
    public Mesh createIsland(IslandBuilder islandBuilder, Mesh aMesh, BaseElevation elevation, SoilProfile soilProfile){
        constructBaseIsland(islandBuilder, aMesh, elevation, soilProfile);
        return islandBuilder.getIsland();
    }

    // Creates a lagoon.
    public Mesh createIsland(MeshBuilder lagoonBuilder, Mesh aMesh){
        lagoonBuilder.buildIsland(aMesh);
        return lagoonBuilder.getIsland();
    }

}
