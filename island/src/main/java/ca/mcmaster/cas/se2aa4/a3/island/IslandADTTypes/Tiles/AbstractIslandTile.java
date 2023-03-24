package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import ca.mcmaster.cas.se2aa4.a3.island.Humidity.HumidityBehaviour;

public abstract class AbstractIslandTile implements Tile {

    HumidityBehaviour humidityBehaviour;

    // Each tile has base elevation of 0.
    protected int elevation = 0;

    public void setElevation(int elevation){ this.elevation = elevation; }
    public int getElevation(){ return elevation; }

    public Tile tryChange(Tile newTile) {
        return newTile;
    }

    @Override
    public int moistureProvided() {
        return humidityBehaviour.moistureProvided();
    }

    @Override
    public boolean isWaterSource() {
        return humidityBehaviour.isWaterSource();
    }
}
