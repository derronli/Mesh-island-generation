package ca.mcmaster.cas.se2aa4.a3.island.Whittaker.Biomes;

import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.Whittaker.BiomeTiles.Arctic.DryTundra;
import ca.mcmaster.cas.se2aa4.a3.island.Whittaker.BiomeTiles.Arctic.MoistTundra;
import ca.mcmaster.cas.se2aa4.a3.island.Whittaker.BiomeTiles.Arctic.RainTundra;
import ca.mcmaster.cas.se2aa4.a3.island.Whittaker.BiomeTiles.Arctic.WetTundra;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Arctic extends Biome {

    public Arctic(){
        super();
    }

    @Override
    protected List<Geometry> setRegions() {
        List<Geometry> regions = new ArrayList<>();
        regions.add(makeRainTundra());
        regions.add(makeWetTundra());
        regions.add(makeMoistTundra());
        regions.add(makeDryTundra());
        return regions;
    }

    @Override
    protected Map<Geometry, Class<? extends Tile>> setMappings() {
        Map<Geometry, Class<? extends Tile>> mappings = new Hashtable<>();
        mappings.put(regions.get(0), RainTundra.class);
        mappings.put(regions.get(1), WetTundra.class);
        mappings.put(regions.get(2), MoistTundra.class);
        mappings.put(regions.get(3), DryTundra.class);
        return mappings;
    }

    private Geometry makeRainTundra(){
        GeometryFactory geom = new GeometryFactory();
        Coordinate[] coords = new Coordinate[] {
                new Coordinate(0, height),
                new Coordinate((int) (width / 2.0), height),
                new Coordinate(0, (int) (height / 2.0)),
                new Coordinate(0, height)
        };
        return geom.createPolygon(coords);
    }

    private Geometry makeWetTundra(){
        GeometryFactory geom = new GeometryFactory();
        Coordinate[] coords = new Coordinate[] {
                new Coordinate(0, 0),
                new Coordinate(width, height),
                new Coordinate((int) (width / 2.0), height),
                new Coordinate(0, (int) (height / 2.0)),
                new Coordinate(0, 0)
        };
        return geom.createPolygon(coords);
    }

    private Geometry makeMoistTundra(){
        GeometryFactory geom = new GeometryFactory();
        Coordinate[] coords = new Coordinate[] {
                new Coordinate(0, 0),
                new Coordinate(width, height),
                new Coordinate(width, (int) (height / 2.0)),
                new Coordinate((int) (width / 2.0), 0),
                new Coordinate(0, 0)
        };
        return geom.createPolygon(coords);
    }

    private Geometry makeDryTundra(){
        GeometryFactory geom = new GeometryFactory();
        Coordinate[] coords = new Coordinate[] {
                new Coordinate((int) (width / 2.0), 0),
                new Coordinate(width, (int) (height / 2.0)),
                new Coordinate(width, 0),
                new Coordinate((int) (width / 2.0), 0)
        };
        return geom.createPolygon(coords);
    }

}
