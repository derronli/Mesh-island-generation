package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import ca.mcmaster.cas.se2aa4.a3.island.Humidity.NonHumiditySource;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.CanChange;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Changer;

import java.awt.Color;

public class LandTile extends AbstractIslandTile {

    public LandTile() {
        humidityBehaviour = new NonHumiditySource();
    }


    @Override
    public Color getColor() {
        return new Color(45, 194, 28);
    }

    @Override
    public Tile tryChange(Tile newTile) {
        return newTile;
    }
}
