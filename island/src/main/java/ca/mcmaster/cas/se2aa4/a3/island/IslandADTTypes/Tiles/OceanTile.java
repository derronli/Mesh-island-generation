package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import ca.mcmaster.cas.se2aa4.a3.island.Humidity.WaterSource;

import java.awt.*;

public class OceanTile implements Tile, WaterSource {

    @Override
    public Color getColor() {
        return new Color(54, 146, 246);
    }

    // Ocean is salty and bad for vegetation so gets gives negative water value.
    @Override
    public int moistureProvided() {
        return -1;
    }

    public Tile tryChange(Tile newTile) {
        return null;
    }

}
