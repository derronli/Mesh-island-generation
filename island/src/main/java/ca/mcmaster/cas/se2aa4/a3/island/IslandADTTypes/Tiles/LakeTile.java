package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import ca.mcmaster.cas.se2aa4.a3.island.Humidity.HumiditySource;

import java.awt.*;

public class LakeTile extends IslandTile {

    public LakeTile() {
        super();
        humidityBehaviour = new HumiditySource();
    }

    @Override
    public Color getColor() {
        return new Color(65,105,225);
    }

    @Override
    public Tile tryChange(Tile newTile) {
        return null;
    }

}
