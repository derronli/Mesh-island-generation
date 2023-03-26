package ca.mcmaster.cas.se2aa4.a3.island.Heatmaps;

import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;

import java.awt.*;

public class MoisturePainter extends HeatmapPainter{
    @Override
    public Color determineColor(MyPolygon p) {
        int moisture = p.getMoisture() * 50;

        //
        return switch ((p.getMoistureProvided() > 0) ? 0 :
                (moisture < 0) ? 1 :
                (moisture <= 10) ? 2 :
                (moisture <= 255) ? 3 : 4
                ) {
            case 0 -> new Color(0, 200, 255);
            case 1 -> new Color(0, 39, 54);
            case 2 -> new Color(255, 200, 255);
            case 3 -> new Color(100, 0, moisture);
            case 4 -> new Color(100, Math.min(255, moisture - 255), 255);
            default -> Color.BLACK;
        };

    }
}
