package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import ca.mcmaster.cas.se2aa4.a3.island.Humidity.HumidityBehaviour;

public abstract class AbstractTile implements Tile {

    protected HumidityBehaviour humidityBehaviour;

    public abstract void setElevation(int elevation);
    public abstract int getElevation();

    public abstract Tile tryChange(Tile newTile);


    public int moistureProvided() {
        return humidityBehaviour.moistureProvided();
    }


    public boolean isWaterSource() {
        return humidityBehaviour.isWaterSource();
    }

}
