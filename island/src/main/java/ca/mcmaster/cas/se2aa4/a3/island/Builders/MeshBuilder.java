package ca.mcmaster.cas.se2aa4.a3.island.Builders;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Heatmaps.HeatmapPainter;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import java.util.List;
import java.util.Random;

public interface MeshBuilder {

    void buildIsland(Mesh aMesh, Random rand);
    Mesh getIsland();
    void applyHeatmap(HeatmapPainter painter);

}
