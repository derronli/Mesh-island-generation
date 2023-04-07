package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Vertex;

import java.awt.*;

public class CityVertex extends IslandVertex {
    private final int size;

    public CityVertex(int size) {
        this.size = size;
    }

    public Color getColor() {
        return new Color(170, 64, 78);
    }

}
