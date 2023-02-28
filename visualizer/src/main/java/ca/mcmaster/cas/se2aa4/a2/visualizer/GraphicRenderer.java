package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

public class GraphicRenderer {

    private static final int THICKNESS = 3;
    public void render(Mesh aMesh, Graphics2D canvas, String commandArg) {

        MyRenderer renderer;
        if (commandArg.equals("-X")){
            renderer = new DebugRenderer();
        }
        else {
            renderer = new NormalRender();
        }

        renderer.render(aMesh, canvas);

    }

    public void render(Mesh aMesh, Graphics2D canvas){
        MyRenderer renderer = new NormalRender();
        renderer.render(aMesh, canvas);
    }

}
