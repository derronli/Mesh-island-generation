package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import ca.mcmaster.cas.se2aa4.a3.island.Humidity.WaterSource;

import java.awt.*;

public class LagoonTile implements Tile, WaterSource {

    @Override
    public Color getColor() {
        return new Color(68, 217, 212);
    }

    // Lagoon gives 2 nutrition because it is extra nutritious.
    @Override
    public int nutritionProvided(){
        return 2;
    }

    public Tile tryChange(Tile NewTile) {
        return null;
    }
}
