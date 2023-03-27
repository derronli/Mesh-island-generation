package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Vertex;

import java.awt.*;

public class RiverVertex extends IslandVertex {

    private int discharge;
    // Discharge will be a 1:1 ratio relative to the amount of moisture provided and the thickness of river on map
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
