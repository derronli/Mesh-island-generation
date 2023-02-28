package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.awt.Graphics2D;

public class GraphicRenderer {
    
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
