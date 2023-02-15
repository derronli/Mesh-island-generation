package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;
import java.awt.Color;
import java.util.List;

public class PropertyManager {

    public static String getProperty(List<Property> values,
                                     String key){
        String val = null;
        for(Property p: values) {
            if (p.getKey().equals(key)) {
                val = p.getValue();
            }
        }
        return val;
    }

    public static Color extractColor(String val){
        String[] raw = val.split(",");
        int red = Integer.parseInt(raw[0]);
        int green = Integer.parseInt(raw[1]);
        int blue = Integer.parseInt(raw[2]);

        // Checks if we have alpha transparency value.
        if (raw.length == 4){
            int alpha = Integer.parseInt(raw[3]);
            return new Color(red, green, blue, alpha);
        }

        return new Color(red, green, blue);
    }
}

