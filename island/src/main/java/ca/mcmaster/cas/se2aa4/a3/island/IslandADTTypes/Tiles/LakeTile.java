package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import java.awt.*;

public class LakeTile extends AbstractIslandTile {

    @Override
    public Color getColor() {
        return new Color(255, 0, 28);
    }

    @Override
    public Tile tryChange(Tile newTile) {
        return null;
    }
}
