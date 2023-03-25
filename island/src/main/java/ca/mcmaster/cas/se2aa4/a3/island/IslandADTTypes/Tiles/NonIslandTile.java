package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import ca.mcmaster.cas.se2aa4.a3.island.FreshWater.DoesNotHaveAquifer;
import ca.mcmaster.cas.se2aa4.a3.island.FreshWater.HasAquifer;
import ca.mcmaster.cas.se2aa4.a3.island.Humidity.SoilProfile;

public abstract class NonIslandTile extends AbstractTile{

    public NonIslandTile (SoilProfile soilProfile) {
        super(soilProfile);
    }
    public void setElevation(int elevation){ return; }

    public int getElevation(){ return -1; }

    public Tile tryChange(Tile newTile) {
        return null;
    }

    public boolean setAquifer () {
        return false;
    }

}
