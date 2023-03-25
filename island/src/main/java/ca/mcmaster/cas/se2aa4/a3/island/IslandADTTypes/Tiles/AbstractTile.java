package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import ca.mcmaster.cas.se2aa4.a3.island.FreshWater.Aquifer;
import ca.mcmaster.cas.se2aa4.a3.island.FreshWater.DoesNotHaveAquifer;
import ca.mcmaster.cas.se2aa4.a3.island.Humidity.HumidityBehaviour;
import ca.mcmaster.cas.se2aa4.a3.island.Humidity.SoilProfile;

public abstract class AbstractTile implements Tile {

    protected Aquifer aquifer;
    protected HumidityBehaviour humidityBehaviour;
    protected SoilProfile soilProfile;

    public AbstractTile (SoilProfile soilProfile) {
        aquifer = new DoesNotHaveAquifer();
        this.soilProfile = soilProfile;
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
