package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.Hexagon;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;

import java.util.HashMap;
import java.util.Map;

public class BuilderFactory {
    private final Map<String, IslandShape> builderOptions = createBuilderOptions();

    private Map<String, IslandShape> createBuilderOptions(){
        int width = 500; int height = 500;
        Map<String, IslandShape> options = new HashMap<>();
        options.put("default", new Circle(width, height));
        options.put("circle", new Circle(width, height));
        options.put("hexagon", new Hexagon(width, height));

        return options;
    }

    public IslandShape getIslandShape(String key){
        try {
            return builderOptions.get(key);
        }
        catch (ClassCastException | NullPointerException exception){
            return builderOptions.get("default");
        }
    }

}
