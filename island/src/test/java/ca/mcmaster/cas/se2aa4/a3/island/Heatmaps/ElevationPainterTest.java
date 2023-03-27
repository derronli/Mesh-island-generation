package ca.mcmaster.cas.se2aa4.a3.island.Heatmaps;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ElevationPainterTest {

    @Test
    void determineColor() {
        HeatmapPainter painter = new ElevationPainter();

        Structs.Vertex v = Structs.Vertex.newBuilder().setX(0).setY(0).build();
        MyVertex vertex = new MyVertex(v);
        vertex.setElevation(0);
        assertEquals(new Color(210, 240, 255), painter.determineColor(vertex));

        vertex.setElevation(11);
        assertEquals(new Color(140, 220, 255), painter.determineColor(vertex));

        vertex.setElevation(21);
        assertEquals(new Color(60, 210, 253), painter.determineColor(vertex));

        vertex.setElevation(31);
        assertEquals(new Color(30, 175, 255), painter.determineColor(vertex));

        vertex.setElevation(51);
        assertEquals(new Color(0, 100, 230), painter.determineColor(vertex));

        vertex.setElevation(76);
        assertEquals(new Color(0, 40, 200), painter.determineColor(vertex));

        vertex.setElevation(-1);
        assertEquals(new Color(0, 39, 54), painter.determineColor(vertex));
    }
}