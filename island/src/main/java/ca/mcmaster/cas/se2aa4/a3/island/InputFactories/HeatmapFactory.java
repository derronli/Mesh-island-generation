package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a3.island.Heatmaps.*;

import java.util.Hashtable;
import java.util.Map;

public class HeatmapFactory {

    private final Map<String, HeatmapPainter> heatmapOptions = createHeatmapOptions();

    private Map<String, HeatmapPainter> createHeatmapOptions(){
        Map<String, HeatmapPainter> options = new Hashtable<>();
        options.put("elevation", new ElevationPainter());
        options.put("moisture", new MoisturePainter());
        options.put("vertexelevation", new VertexElevationPainter());

        return options;
    }

    public HeatmapPainter getHeatmap(String key){
        try {
            if (!heatmapOptions.containsKey(key)){
                throw new NullPointerException();
            }
            return heatmapOptions.get(key);
        }
        catch (ClassCastException | NullPointerException exception){
            // Sets elevation heatmap as default if issue arises when searching for elevation.
            return heatmapOptions.get("elevation");
        }
    }

}
