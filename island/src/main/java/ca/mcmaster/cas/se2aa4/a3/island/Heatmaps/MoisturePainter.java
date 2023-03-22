package ca.mcmaster.cas.se2aa4.a3.island.Heatmaps;

import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;

import java.awt.*;
import java.util.Random;

public class MoisturePainter extends HeatmapPainter{
    @Override
    public Color determineColor(MyPolygon p) {
        int moisture = new Random().nextInt(-1, 10);

        return switch ((0 <= moisture && moisture <= 2) ? 0 :
                (2 <= moisture && moisture <= 3) ? 1 :
                        (4 <= moisture && moisture <= 5) ? 2 :
                                (6 <= moisture && moisture <= 7) ? 3 :
                                        (8 <= moisture && moisture <= 9) ? 4 : 5
                ) {
            case 0 -> new Color(200, 240, 255);
            case 1 -> new Color(130, 220, 255);
            case 2 -> new Color(55, 210, 255);
            case 3 -> new Color(30, 175, 255);
            case 4 -> new Color(0, 150, 230);
            case 5 -> new Color(0, 130, 200);
            default -> Color.BLACK;
        };

    }
}
