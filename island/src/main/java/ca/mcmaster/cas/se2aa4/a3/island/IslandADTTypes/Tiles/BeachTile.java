package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import java.awt.*;

public class BeachTile extends AbstractIslandTile {
    @Override
    public Color getColor() {
        return new Color(250, 216, 168);
    }

    public Tile tryChange(Tile NewTile) {
        return null;
    }
}
