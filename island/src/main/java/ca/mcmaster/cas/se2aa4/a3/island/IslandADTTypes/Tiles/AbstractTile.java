package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import ca.mcmaster.cas.se2aa4.a3.island.FreshWater.*;
import ca.mcmaster.cas.se2aa4.a3.island.Humidity.HumidityBehaviour;
import ca.mcmaster.cas.se2aa4.a3.island.Humidity.TileSoil;

public abstract class AbstractTile implements Tile {

    protected Aquifer aquifer;
    protected HumidityBehaviour humidityBehaviour;
    protected TileSoil soil;

    public AbstractTile () {
        aquifer = new DoesNotHaveAquifer();
        this.soil = new TileSoil();
    }

    public abstract void setElevation(int elevation);
    public abstract int getElevation();

    public abstract boolean setAquifer();

    public abstract Tile tryChange(Tile newTile);


    public int moistureProvided() {
        return humidityBehaviour.moistureProvided() + aquifer.getMoisture();
    }


    public boolean isWaterSource() {
        return humidityBehaviour.isWaterSource();
    }

}
