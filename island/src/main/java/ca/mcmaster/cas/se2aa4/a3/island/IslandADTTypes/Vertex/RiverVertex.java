package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Vertex;

import java.awt.*;

public class RiverVertex extends IslandVertex {

    private int discharge;
    // Randomly set discharge value
    public RiverVertex(int discharge, int elevation) {
        this.discharge = discharge;
        this.elevation = elevation;
    }

    public Color getColor() {
        return new Color(135, 206, 235);
    }

    public void addToDischarge(int n) {
        this.discharge += n;
    }
    public int getDischarge(){ return discharge; }
}
