package ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles;

import ca.mcmaster.cas.se2aa4.a3.island.Humidity.HumiditySource;
import java.awt.*;

public class OceanTile extends NonIslandTile {

    public OceanTile() {
        humidityBehaviour = new HumiditySource();
    }

    @Override
    public Color getColor() {
        return new Color(54, 146, 246);
    }

    // Ocean does not give moisture.
    @Override
    public int moistureProvided() {
        return 0;
    }

}
