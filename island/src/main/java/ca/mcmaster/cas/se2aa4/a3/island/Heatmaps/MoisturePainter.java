package ca.mcmaster.cas.se2aa4.a3.island.Heatmaps;

import ca.mcmaster.cas.se2aa4.a3.island.MyShape;

import java.awt.*;

public class MoisturePainter extends HeatmapPainter{
    @Override
    public Color determineColor(MyShape s) {
        int moisture = (int) s.getMoisture();

        // First condition checks if the tile is a lake or aquifer.
        return switch ( (s.getMoistureProvided() > 0) ? 0 :
                (moisture < 0) ? 1 :
                (moisture <= 5) ? 2 :
                (moisture <= 255) ? 3 :
                (moisture <= 510) ? 4 : 5
                ) {
            case 0 -> new Color(0, 150, 100);
            case 1 -> new Color(0, 39, 54);
            case 2 -> new Color(255, 200, 255);
            case 3 -> new Color(100, 0, moisture);
            case 4 -> new Color(100, Math.min(255, moisture - 255), 255);
            case 5 -> new Color(Math.min(255, moisture - 510), 255, 255);
            default -> Color.BLACK;
        };

    }
}
