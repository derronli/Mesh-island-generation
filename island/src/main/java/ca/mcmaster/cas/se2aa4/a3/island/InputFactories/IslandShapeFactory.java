package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.*;

import java.util.Hashtable;
import java.util.Map;

public class IslandShapeFactory {
    private final Map<String, IslandShape> builderOptions = createBuilderOptions();

    private final int width = 500, height = 500;

    private Map<String, IslandShape> createBuilderOptions(){
        Map<String, IslandShape> options = new Hashtable<>();
        options.put("circle", new Circle(width, height));
        options.put("hexagon", new Hexagon(width, height));

        return options;
    }

    public IslandShape getIslandShape(String key){
        try {
            if (!builderOptions.containsKey(key)){
                throw new NullPointerException();
            }
            return builderOptions.get(key);
        }
        catch (ClassCastException | NullPointerException exception){
            return new Circle(width, height);
        }
    }

}
