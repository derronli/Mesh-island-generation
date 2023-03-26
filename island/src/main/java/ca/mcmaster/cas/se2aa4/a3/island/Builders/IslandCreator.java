package ca.mcmaster.cas.se2aa4.a3.island.Builders;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.BaseElevation;
import ca.mcmaster.cas.se2aa4.a3.island.Heatmaps.HeatmapPainter;
import ca.mcmaster.cas.se2aa4.a3.island.Humidity.SoilProfile;
import java.util.Random;


public class IslandCreator {

    // Creates an island with a heatmap.
    public Mesh createIsland(IslandBuilder islandBuilder, Mesh aMesh, BaseElevation elevation, HeatmapPainter heatmap, Random rand, int aquiferNum, SoilProfile soilProfile){
        constructBaseIsland(islandBuilder, aMesh, elevation, rand, aquiferNum, soilProfile);
        islandBuilder.applyHeatmap(heatmap);
        return islandBuilder.getIsland();
    }

    // Does the basic construction for an island, including moisture and elevation.
    private void constructBaseIsland(IslandBuilder islandBuilder, Mesh aMesh, BaseElevation elevation, Random rand, int aquiferNum, SoilProfile soilProfile){
        islandBuilder.buildIsland(aMesh, rand, aquiferNum);
        islandBuilder.addMoistureToPolygons(soilProfile);
        islandBuilder.constructElevation(elevation);
        islandBuilder.constructRivers(rand);
    }

    // Creates a regular island.
    public Mesh createIsland(IslandBuilder islandBuilder, Mesh aMesh, BaseElevation elevation, Random rand, int aquiferNum, SoilProfile soilProfile){
        constructBaseIsland(islandBuilder, aMesh, elevation, rand, aquiferNum, soilProfile);
        return islandBuilder.getIsland();
    }

    // Creates a lagoon.
    public Mesh createIsland(MeshBuilder lagoonBuilder, Mesh aMesh, Random rand, int aquiferNum){
        lagoonBuilder.buildIsland(aMesh, rand, aquiferNum);
        return lagoonBuilder.getIsland();
    }

}
