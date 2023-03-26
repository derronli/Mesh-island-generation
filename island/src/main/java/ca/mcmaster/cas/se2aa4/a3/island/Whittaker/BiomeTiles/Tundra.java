package ca.mcmaster.cas.se2aa4.a3.island.Whittaker.BiomeTiles;

import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.LandTile;

import java.awt.Color;

public class Tundra extends LandTile {

    @Override
    public Color getColor() {
        return new Color(169, 250, 240);
    }
}
