package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import ca.mcmaster.cas.se2aa4.a3.island.Humidity.WaterSource;

import java.awt.*;

public class LakeTile extends AbstractIslandTile implements WaterSource {

    @Override
    public Color getColor() {
        return new Color(255, 0, 28);
    }

    @Override
    public Tile tryChange(Tile newTile) {
        return null;
    }

    @Override
    public int moistureProvided() {
        return 1;
    }
}
