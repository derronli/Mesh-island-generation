package ca.mcmaster.cas.se2aa4.a3.island.Builders;


import ca.mcmaster.cas.se2aa4.a3.island.Elevation.BaseElevation;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor.StructsToAdtExtractor;
import ca.mcmaster.cas.se2aa4.a3.island.FreshWater.LakeGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.*;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.locationtech.jts.geom.Geometry;


public class IslandBuilder extends AbstractBuilder {

    private final IslandShape islandShape;

    public void constructElevation (BaseElevation elevation){
        elevation.generateElevation();
    }
    public IslandBuilder(IslandShape shape){
        islandShape = shape;
    }

    @Override
    public void buildIsland(Mesh aMesh){

        // Extracting the Structs from the input mesh and converts to our ADT
        StructsToAdtExtractor extractor = new StructsToAdtExtractor(aMesh);
        myVertices = extractor.getMyVertices();
        mySegments = extractor.getMySegments();
        myPolygons = extractor.getMyPolygons();

        // Creates island shape.
        Geometry shape = islandShape.getShape();

        // Sets land tiles.
        setTileInsideShape(shape, myPolygons, new LandTile());

        // Lake generator
        new LakeGenerator(myPolygons, 10);

    }
    public IslandShape getIslandShape (){
        return islandShape;
    }



}
