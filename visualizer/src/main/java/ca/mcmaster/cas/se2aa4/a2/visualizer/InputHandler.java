package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;
import java.io.IOException;

public class InputHandler {

    // Visualize mesh in debug mode or regular.
    public void visualizeMesh(String input, String output, String debug) throws IOException {

        // Getting width and height for the canvas
        Structs.Mesh aMesh = new MeshFactory().read(input);

        // Creating the Canvas to draw the mesh
        Graphics2D canvas = canvasFromMesh(aMesh);

        MyRenderer renderer;
        if (debug != null) {
            renderer = new DebugRenderer();
        }
        else{
            renderer = new NormalRender();
        }
        renderer.render(aMesh, canvas);

        meshToFile(output, canvas, aMesh);

    }

    private Graphics2D canvasFromMesh(Structs.Mesh aMesh){
        double max_x = Double.MIN_VALUE;
        double max_y = Double.MIN_VALUE;
        for (Structs.Vertex v: aMesh.getVerticesList()) {
            max_x = (Double.compare(max_x, v.getX()) < 0? v.getX(): max_x);
            max_y = (Double.compare(max_y, v.getY()) < 0? v.getY(): max_y);
        }

        return SVGCanvas.build((int) Math.ceil(max_x), (int) Math.ceil(max_y));

    }

    private void meshToFile(String output, Graphics2D canvas, Structs.Mesh aMesh) throws IOException {
        // Storing the result in an SVG file
        SVGCanvas.write(canvas, output);
        // Dump the mesh to stdout
        MeshDump dumper = new MeshDump();
        dumper.dump(aMesh);
    }

}
