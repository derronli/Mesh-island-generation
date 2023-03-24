package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import ca.mcmaster.cas.se2aa4.a3.island.Humidity.NonHumiditySource;

import java.awt.*;

public class BeachTile extends AbstractIslandTile {

    public BeachTile() {
        humidityBehaviour = new NonHumiditySource();
    }

    @Override
    public Color getColor() {
        return new Color(250, 216, 168);
    }

    public Tile tryChange(Tile newTile) {
        return null;
    }
}
