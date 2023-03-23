package ca.mcmaster.cas.se2aa4.a3.island.Builders;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Heatmaps.HeatmapPainter;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import java.util.List;

public interface MeshBuilder {

    void buildIsland(Mesh aMesh);
    Mesh getIsland();
    void applyHeatmap(HeatmapPainter painter);

}
