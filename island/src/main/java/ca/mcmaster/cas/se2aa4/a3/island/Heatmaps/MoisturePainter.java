package ca.mcmaster.cas.se2aa4.a3.island.Heatmaps;

import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;

import java.awt.*;

public class MoisturePainter extends HeatmapPainter{
    @Override
    public Color determineColor(MyPolygon p) {
        int moisture = p.getMoisture();

        return switch ((moisture == -1) ? 6 :
                (0 <= moisture && moisture <= 2) ? 0 :
                (2 <= moisture && moisture <= 3) ? 1 :
                        (4 <= moisture && moisture <= 5) ? 2 :
                                (6 <= moisture && moisture <= 7) ? 3 :
                                        (8 <= moisture && moisture <= 9) ? 4 : 5
                ) {
            case 0 -> new Color(210, 240, 255);
            case 1 -> new Color(140, 220, 255);
            case 2 -> new Color(60, 210, 253);
            case 3 -> new Color(30, 175, 255);
            case 4 -> new Color(0, 100, 230);
            case 5 -> new Color(0, 40, 200);
            case 6 -> new Color(0, 39, 54);
            default -> Color.BLACK;
        };

    }
}
