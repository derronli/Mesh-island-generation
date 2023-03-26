package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a3.island.Whittaker.*;
import ca.mcmaster.cas.se2aa4.a3.island.Whittaker.Biomes.Arctic;

import java.util.Hashtable;
import java.util.Map;

public class BiomeFactory {

    private final Map<String, WhittakerDiagram> biomeOptions = createBiomeOptions();

    private Map<String, WhittakerDiagram> createBiomeOptions(){
        Map<String, WhittakerDiagram> options = new Hashtable<>();
        options.put("arctic", new Arctic());

        return options;
    }

    public WhittakerDiagram getBiome(String key){
        try {
            if (!biomeOptions.containsKey(key)){
                throw new NullPointerException();
            }
            return biomeOptions.get(key);
        }
        catch (ClassCastException | NullPointerException exception){
            // Sets no biome as default.
            return null;
        }
    }

}
