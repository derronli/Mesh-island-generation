package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;

public class ElevationRenderer extends MapDebugRenderer {

    // Determines colour specifically for elevation depending on the property given.
    protected Color determineColor(java.util.List<Structs.Property> properties) {
        String val = PropertyManager.getProperty(properties, "elevation");
        if (val == null)
            return new Color(200, 240, 255);

        // Goes through possible elevation ranges to determine colour of tile.
        try {
            int elevation = Integer.parseInt(val);

            switch ((0 <= elevation && elevation <= 10) ? 0 :
                    (11 <= elevation && elevation <= 20) ? 1:
                            (21 <= elevation && elevation <= 30) ? 2:
                                    (31 <= elevation && elevation <= 50) ? 3:
                                            (51 <= elevation && elevation <= 75) ? 4: 5
            ){
                case 0:
                    return new Color(200, 240, 255);
                case 1:
                    return new Color(130, 220, 255);
                case 2:
                    return new Color(55, 210, 255);
                case 3:
                    return new Color(30, 175, 255);
                case 4:
                    return new Color(0, 150, 230);
                case 5:
                    return new Color(0, 130, 200);
                default:
                    System.out.println("elevation is not greater than 0 at a tile");
                    return Color.BLACK;
            }

        } catch (NumberFormatException e){
            System.out.println("error in parsing elevation for tile");
            return Color.RED;
        }

    }


}
