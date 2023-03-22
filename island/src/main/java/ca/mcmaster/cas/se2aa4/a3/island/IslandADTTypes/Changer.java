package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes;

import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.Tile;

public abstract class Changer<T> {
    public ChangingBehaviour<T> changer;

    public void trychange(T newTile) {
        changer.exec(newTile);
    }
}
