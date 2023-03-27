package ca.mcmaster.cas.se2aa4.a3.island.Heatmaps;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MoisturePainterTest {

    @Test
    void determineColor() {
        HeatmapPainter painter = new MoisturePainter();

        Structs.Property color = Structs.Property.newBuilder().setKey("rgb_color").setValue("0,0,0").build();
        Structs.Vertex v = Structs.Vertex.newBuilder().setX(0).setY(0).addProperties(color).build();

        MyVertex vertex = new MyVertex(v);


        vertex.makeRiverVertex(1);
        assertEquals(new Color(0, 150, 100), painter.determineColor(vertex));

    }
}