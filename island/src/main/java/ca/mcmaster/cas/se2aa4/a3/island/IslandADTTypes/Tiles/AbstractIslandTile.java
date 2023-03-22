package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

public abstract class AbstractIslandTile implements Tile {

    // Each tile has base elevation of 0.
    protected int elevation = 0;

    public void setElevation(int elevation){ this.elevation = elevation; }
    public int getElevation(){ return elevation; }

    public Tile tryChange(Tile newTile) {
        return newTile;
    }

}
