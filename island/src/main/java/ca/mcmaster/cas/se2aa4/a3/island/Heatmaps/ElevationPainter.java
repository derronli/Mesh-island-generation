package ca.mcmaster.cas.se2aa4.a3.island.Heatmaps;

import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;

import java.awt.*;

public class ElevationPainter extends HeatmapPainter{
    @Override
    public Color determineColor(MyPolygon p) {
        int elevation = p.getElevation();

        return switch ((0 <= elevation && elevation <= 10) ? 0 :
                (11 <= elevation && elevation <= 20) ? 1 :
                        (21 <= elevation && elevation <= 30) ? 2 :
                                (31 <= elevation && elevation <= 50) ? 3 :
                                        (51 <= elevation && elevation <= 75) ? 4 : 5
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
