package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;


import ca.mcmaster.cas.se2aa4.a3.island.FreshWater.*;
import ca.mcmaster.cas.se2aa4.a3.island.Humidity.SoilProfile;

public abstract class IslandTile extends AbstractTile {

    // Each tile has base elevation of 0.
    protected int elevation = 0;

    public IslandTile (SoilProfile soilProfile) {
        super(soilProfile);
    }

    public void setElevation(int elevation){ this.elevation = elevation; }
    public int getElevation(){ return elevation; }

    public Tile tryChange(Tile newTile) {
        return newTile;
    }

    public boolean setAquifer () {
        if (aquifer.getClass() == HasAquifer.class){
            return false;
        }
        aquifer = new HasAquifer();
        return true;
    }

}
