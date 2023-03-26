package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Vertex;

import java.awt.*;

public abstract class IslandVertex {

    protected int elevation = 0;

    public void setElevation(int elevation){ this.elevation = elevation; }
    public int getElevation() { return this.elevation; }

    public Color getColor() {
        return new Color(0, 0, 0);
    }


}
