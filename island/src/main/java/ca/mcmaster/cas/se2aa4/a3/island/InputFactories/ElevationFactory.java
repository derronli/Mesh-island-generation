package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a3.island.Elevation.BaseElevation;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.PlainsElevation;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.VolcanoElevation;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElevationFactory {

    private final Map<String, BaseElevation> elevationOptions = createElevationOptions();

    private Map<String, BaseElevation> createElevationOptions(){
        Map<String, BaseElevation> options = new HashMap<>();
        options.put("plains", new PlainsElevation());
        options.put("volcano", new VolcanoElevation());

        return options;
    }

    public BaseElevation getElevation(String key){
        try {
            return elevationOptions.get(key);
        }
        catch (ClassCastException | NullPointerException exception){
            // Sets elevation heatmap as default if issue arises when searching for elevation.
            return elevationOptions.get("plains");
        }
    }

}
