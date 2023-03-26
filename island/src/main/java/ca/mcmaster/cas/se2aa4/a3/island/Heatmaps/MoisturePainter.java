package ca.mcmaster.cas.se2aa4.a3.island.Heatmaps;

import ca.mcmaster.cas.se2aa4.a3.island.MyShape;

import java.awt.*;

public class MoisturePainter extends HeatmapPainter{
    @Override
    public Color determineColor(MyShape s) {
        int moisture = s.getMoisture() * 100;

        return switch ((moisture < 0) ? 0 :
                (moisture == 0) ? 1 :
                (moisture <= 255) ? 2 :
                (moisture <= 510) ? 3 : 4
                ) {
            case 0 -> new Color(0, 39, 54);
            case 1 -> new Color(255, 200, 255);
            case 2 -> new Color(150, 0, moisture);
            case 3 -> new Color(150, 100, 510 - moisture);
            case 4 -> new Color(0, 200, 255);
            default -> Color.BLACK;
        };

    }
}
