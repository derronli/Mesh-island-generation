package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a3.island.Elevation.BaseElevation;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.PlainsElevation;
import ca.mcmaster.cas.se2aa4.a3.island.Elevation.VolcanoElevation;

import java.util.Hashtable;
import java.util.Map;

public class ElevationFactory {

    private final Map<String, BaseElevation> elevationOptions = createElevationOptions();

    private Map<String, BaseElevation> createElevationOptions(){
        Map<String, BaseElevation> options = new Hashtable<>();
        options.put("plains", new PlainsElevation());
        options.put("volcano", new VolcanoElevation());

        return options;
    }

    public BaseElevation getElevation(String key){
        try {
            return elevationOptions.get(key);
        }
        catch (ClassCastException | NullPointerException exception){
            // Sets plains elevation as default if issue arises when searching for elevation.
            return elevationOptions.get("plains");
        }
    }

}
