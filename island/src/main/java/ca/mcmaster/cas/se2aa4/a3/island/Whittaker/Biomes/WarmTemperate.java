package ca.mcmaster.cas.se2aa4.a3.island.Whittaker.Biomes;

import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.Whittaker.BiomeTiles.WarmTemperate.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class WarmTemperate extends Biome {

    public WarmTemperate() { super(); }

    @Override
    protected List<Geometry> setRegions() {
        List<Geometry> regions = new ArrayList<>();
        regions.add(makeRainForest());
        regions.add(makeWetForest());
        regions.add(makeMoistForest());
        regions.add(makeDryForest());
        regions.add(makeWoodland());
        regions.add(makeDesertScrub());
        regions.add(makeDesert());
        return regions;
    }

    @Override
    protected Map<Geometry, Class<? extends Tile>> setMappings() {
        Map<Geometry, Class<? extends Tile>> mappings = new Hashtable<>();
        mappings.put(regions.get(0), RainForest.class);
        mappings.put(regions.get(1), WetForest.class);
        mappings.put(regions.get(2), MoistForest.class);
        mappings.put(regions.get(3), DryForest.class);
        mappings.put(regions.get(4), Woodland.class);
        mappings.put(regions.get(5), DesertScrub.class);
        mappings.put(regions.get(6), Desert.class);
        return mappings;
    }

    private Geometry makeRainForest() {
        GeometryFactory geom = new GeometryFactory();
        Coordinate[] coords = new Coordinate[]{
                new Coordinate(0, (int) (height * 3/ 4.0)),
                new Coordinate(0, height),
                new Coordinate((int) (width / 2.0), height),
                new Coordinate((int) (width / 2.0), (int) (height * 5/ 6.0)),
                new Coordinate(0, (int) (height * 3/ 4.0))
        };
        return geom.createPolygon(coords);
    }

    private Geometry makeWetForest() {
        GeometryFactory geom = new GeometryFactory();
        Coordinate[] coords = new Coordinate[]{
                new Coordinate(width, (int) (height * 3/ 4.0)),
                new Coordinate(width, height),
                new Coordinate((int) (width / 2.0), height),
                new Coordinate((int) (width / 2.0), (int) (height * 5/ 6.0)),
                new Coordinate(width, (int) (height * 3/ 4.0))
        };
        return geom.createPolygon(coords);
    }

    private Geometry makeMoistForest() {
        GeometryFactory geom = new GeometryFactory();
        Coordinate[] coords = new Coordinate[]{
                new Coordinate(0, (int) (height / 2.0)),
                new Coordinate((int) (width / 2.0), (int) (height * 5 / 8.0)),
                new Coordinate(width, (int) (height / 2.0)),
                new Coordinate(width, (int) (height * 3 / 4.0)),
                new Coordinate((int) (width / 2.0), (int) (height * 5 / 6.0)),
                new Coordinate(0, (int) (height * 3 / 4.0)),
                new Coordinate(0, (int) (height / 2.0))
        };
        return geom.createPolygon(coords);
    }

    private Geometry makeDryForest() {
        GeometryFactory geom = new GeometryFactory();
        Coordinate[] coords = new Coordinate[]{
                new Coordinate(0, (int) (height / 2.0)),
                new Coordinate((int) (width / 2.0), (int) (height * 5 / 8.0)),
                new Coordinate(width, (int) (height / 2.0)),
                new Coordinate((int) (width / 2.0), (int) (height * 3 / 8.0)),
                new Coordinate(0, (int) (height / 2.0))
        };
        return geom.createPolygon(coords);
    }

    private Geometry makeWoodland() {
        GeometryFactory geom = new GeometryFactory();
        Coordinate[] coords = new Coordinate[]{
                new Coordinate(0, (int) (height / 4.0)),
                new Coordinate(width, (int) (height / 4.0)),
                new Coordinate(width, (int) (height / 2.0)),
                new Coordinate((int) (width / 2.0), (int) (height * 3 / 8.0)),
                new Coordinate(0, (int) (height / 2.0)),
                new Coordinate(0, (int) (height / 4.0))
        };
        return geom.createPolygon(coords);
    }

    private Geometry makeDesertScrub() {
        GeometryFactory geom = new GeometryFactory();
        Coordinate[] coords = new Coordinate[]{
                new Coordinate(0, 0),
                new Coordinate(0, (int) (height / 4.0)),
                new Coordinate((int) (width * 3 / 4.0), (int) (height / 4.0)),
                new Coordinate((int) (width * 3 / 4.0), 0),
                new Coordinate(0, 0)
        };
        return geom.createPolygon(coords);
    }

    private Geometry makeDesert() {
        GeometryFactory geom = new GeometryFactory();
        Coordinate[] coords = new Coordinate[]{
                new Coordinate((int) (width * 3 / 4.0), 0),
                new Coordinate((int) (width * 3 / 4.0), (int) (height / 4.0)),
                new Coordinate(width, (int) (height / 4.0)),
                new Coordinate(width, 0),
                new Coordinate((int) (width * 3 / 4.0), 0)
        };
        return geom.createPolygon(coords);
    }

}
