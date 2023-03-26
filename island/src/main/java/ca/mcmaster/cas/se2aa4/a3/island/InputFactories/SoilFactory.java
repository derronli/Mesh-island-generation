package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a3.island.Humidity.*;

import java.util.Hashtable;
import java.util.Map;

public class SoilFactory {
    private final Map<String, SoilProfile> soilOptions = createSoilOptions();

    private Map<String, SoilProfile> createSoilOptions(){
        Map<String, SoilProfile> options = new Hashtable<>();
        options.put("wet", new WetSoil());
        options.put("dry", new DrySoil());

        return options;
    }

    public SoilProfile getSoilProfile(String key){
        try {
            if (!soilOptions.containsKey(key)){
                throw new NullPointerException();
            }
            return soilOptions.get(key);
        }
        catch (ClassCastException | NullPointerException exception){
            return new WetSoil();
        }
    }

}
