package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

public abstract class NonIslandTile extends AbstractTile{

    public void setElevation(int elevation){ return; }

    public int getElevation(){ return -1; }

    public Tile tryChange(Tile newTile) {
        return null;
    }

}
