package ca.mcmaster.cas.se2aa4.a3.island.Whittaker.Biomes;

import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.LandTile;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.Whittaker.WhittakerDiagram;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public abstract class Biome implements WhittakerDiagram {

    protected List<Geometry> regions;
    protected Map<Geometry, Class<? extends Tile>> biomeMappings;
    protected int width = 100, height = 600;

    public Biome(){
        regions = setRegions();
        biomeMappings = setMappings();
    }

    protected abstract List<Geometry> setRegions();
    protected abstract Map<Geometry, Class<? extends Tile>> setMappings();

    public Tile getTile(int moisture, int elevation) {

        Class<? extends Tile> klass;

        // Checks for out of bounds, returning first item in biomes if more moisture than the height.
        if (moisture > height) {
            klass = biomeMappings.get(regions.get(0));
            try{
                return klass.getDeclaredConstructor().newInstance();
            }
            // Should an error occur, returns a regular land tile.
            catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException |
                   InvocationTargetException exp){
                return new LandTile();
            }
        }
        // Returns last item in biomes if more elevation than width.
        if (elevation > width){
            klass = biomeMappings.get(regions.get(regions.size() - 1));
            try{
                return klass.getDeclaredConstructor().newInstance();
            }
            // Should an error occur, returns a regular land tile.
            catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException |
                   InvocationTargetException exp){
                return new LandTile();
            }
        }

        GeometryFactory geom = new GeometryFactory();
        Point p = geom.createPoint(new Coordinate(elevation, moisture));
        for (Geometry g : regions){
           if (g.contains(p)){
               klass = biomeMappings.get(g);
               try{
                   return klass.getDeclaredConstructor().newInstance();
               }
               // Should an error occur, returns a regular land tile.
               catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException |
                      InvocationTargetException exp){
                   return new LandTile();
               }
           }
        }

        // In the case that the point does not exist in any regions, returns a land tile.
        return new LandTile();

    }

}
