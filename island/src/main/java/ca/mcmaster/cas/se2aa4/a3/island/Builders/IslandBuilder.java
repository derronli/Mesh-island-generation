package ca.mcmaster.cas.se2aa4.a3.island.Builders;

import ca.mcmaster.cas.se2aa4.a3.island.Elevation.BaseElevation;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor.StructsToAdtExtractor;
import ca.mcmaster.cas.se2aa4.a3.island.FreshWater.LakeGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.*;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import org.locationtech.jts.geom.Geometry;

import java.util.List;


public class IslandBuilder extends AbstractBuilder {

    private final IslandShape islandShape;

    public void constructElevation (BaseElevation elevation){
        elevation.generateElevation(islandShape, myPolygons);
    }
    public IslandBuilder(IslandShape shape){
        islandShape = shape;
    }

    @Override
    public void buildIsland(Mesh aMesh){

        extractFromMesh(aMesh);

        // Creates island shape.
        Geometry shape = islandShape.getShape();

        // Sets land tiles.
        setTileInsideShape(shape, myPolygons, LandTile.class);

        // Lake generator
        new LakeGenerator(myPolygons, 10);

    }

}
