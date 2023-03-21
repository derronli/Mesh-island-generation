package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.awt.*;

public class MoistureRenderer extends MapDebugRenderer {

    // Determines colour specifically for elevation depending on the property given.
    protected Color determineColor(java.util.List<Structs.Property> properties) {
        String val = PropertyManager.getProperty(properties, "moisture");
        if (val == null)
            return new Color(200, 240, 255);

        // Goes through possible elevation ranges to determine colour of tile.
        try {
            int moisture = Integer.parseInt(val);

            switch ((0 <= moisture && moisture <= 2) ? 0 :
                    (2 <= moisture && moisture <= 3) ? 1:
                            (4 <= moisture && moisture <= 5) ? 2:
                                    (6 <= moisture && moisture <= 7) ? 3:
                                            (8 <= moisture && moisture <= 9) ? 4: 5
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
                    System.out.println("moisture is not greater than 0 at a tile");
                    return Color.BLACK;
            }

        } catch (NumberFormatException e){
            System.out.println("error in parsing moisture for tile");
            return Color.RED;
        }

    }


}
