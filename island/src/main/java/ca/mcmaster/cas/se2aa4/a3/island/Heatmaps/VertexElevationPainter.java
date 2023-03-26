package ca.mcmaster.cas.se2aa4.a3.island.Heatmaps;

import ca.mcmaster.cas.se2aa4.a3.island.MyShape;

import java.awt.*;

public class VertexElevationPainter extends VertexHeatmapPainter{

    @Override
    public Color determineColor(MyShape s) {
        int elevation = Math.min(s.getElevation() * 3, 255);

        return switch ((elevation < 0) ? 0 :
                (elevation == 0) ? 1 : 2
                ) {
            case 0 -> new Color(0, 0,0);
            case 1 -> new Color(255, 200, 255);
            case 2 -> new Color(150, 0, elevation);
            default -> Color.BLACK;
        };
    }

}
