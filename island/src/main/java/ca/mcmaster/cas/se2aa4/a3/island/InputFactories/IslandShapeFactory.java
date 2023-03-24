package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.Circle;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.Hexagon;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.IslandShape;

import java.util.Hashtable;
import java.util.Map;

public class IslandShapeFactory {
    private final Map<String, IslandShape> builderOptions = createBuilderOptions();

    private Map<String, IslandShape> createBuilderOptions(){
        int width = 500; int height = 500;
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
            return builderOptions.get("circle");
        }
    }

}
