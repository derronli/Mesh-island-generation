package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import ca.mcmaster.cas.se2aa4.a3.island.Humidity.HumidityBehaviour;

public abstract class AbstractNonIslandTile implements Tile{

    protected HumidityBehaviour humidityBehaviour;

    public void setElevation(int elevation){ return; }

    public int getElevation(){ return -1; }

    @Override
    public int moistureProvided() {
        return humidityBehaviour.moistureProvided();
    }

    @Override
    public boolean isWaterSource() {
        return humidityBehaviour.isWaterSource();
    }
}
