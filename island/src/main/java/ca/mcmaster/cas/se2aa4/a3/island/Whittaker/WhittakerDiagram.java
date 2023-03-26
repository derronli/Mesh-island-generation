package ca.mcmaster.cas.se2aa4.a3.island.Whittaker;

import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.Tile;

public interface WhittakerDiagram {

    Tile getTile(int moisture, int elevation);
}
