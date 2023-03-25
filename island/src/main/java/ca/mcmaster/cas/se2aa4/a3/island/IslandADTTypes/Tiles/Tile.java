package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import java.awt.Color;

public interface Tile {
    
    Color getColor();

    Tile tryChange(Tile newTile);

    void setElevation(int elevation);

    int getElevation();

    int moistureProvided();

    boolean isWaterSource();

    boolean setAquifer();

    void addMoisture(int moisture);
}
