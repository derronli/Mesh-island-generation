package ca.mcmaster.cas.se2aa4.a3.island.Heatmaps;

import ca.mcmaster.cas.se2aa4.a3.island.*;

import java.awt.*;
import java.util.List;

public abstract class VertexHeatmapPainter extends HeatmapPainter {

    public void createHeatmap(List<MyPolygon> myPolygons, List<MyVertex> myVertices, List<MySegment> mySegments){
        normalizeShapeColor(mySegments, "0,0,0");
        normalizeShapeColor(myPolygons, "0,0,0");
//        for (MyPolygon p : myPolygons){
//            Color newColor = determineColor(p);
//            p.changeColor(newColor.getRed() + "," + newColor.getGreen() + "," + newColor.getBlue());
//        }
        for (MyVertex v : myVertices){
            Color newColor = determineColor(v);
            v.changeColor(newColor.getRed() + "," + newColor.getGreen() + "," + newColor.getBlue());
        }
    }

}
