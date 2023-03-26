package ca.mcmaster.cas.se2aa4.a3.island.Heatmaps;

import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.MyShape;
import ca.mcmaster.cas.se2aa4.a3.island.MyVertex;
import ca.mcmaster.cas.se2aa4.a3.island.MySegment;
import java.util.List;

import java.awt.*;

public abstract class HeatmapPainter {

    protected void normalizeShapeColor(List<? extends MyShape> shapeList, String color){
        for (MyShape shape: shapeList){
            shape.changeColor(color);
        }
    }

    public void createHeatmap(List<MyPolygon> myPolygons, List<MyVertex> myVertices, List<MySegment> mySegments){
        normalizeShapeColor(myVertices, "0,0,0");
        normalizeShapeColor(mySegments, "0,0,0");
        for (MyPolygon p : myPolygons){
            Color newColor = determineColor(p);
            p.changeColor(newColor.getRed() + "," + newColor.getGreen() + "," + newColor.getBlue());
        }
    }

    public abstract Color determineColor(MyShape s);

}
