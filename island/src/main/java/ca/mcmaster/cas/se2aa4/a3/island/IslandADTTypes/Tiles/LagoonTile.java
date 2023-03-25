package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import ca.mcmaster.cas.se2aa4.a3.island.Humidity.HumiditySource;

import java.awt.*;

public class LagoonTile extends NonIslandTile {

    public LagoonTile() {
        humidityBehaviour = new HumiditySource();
    }

    @Override
    public Color getColor() {
        return new Color(68, 217, 212);
    }

    // Lagoon gives 2 nutrition because it is extra nutritious.
    @Override
    public int moistureProvided(){
        return 2;
    }

}
