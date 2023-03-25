package ca.mcmaster.cas.se2aa4.a3.island.Builders;

import ca.mcmaster.cas.se2aa4.a3.island.Elevation.BaseElevation;
import ca.mcmaster.cas.se2aa4.a3.island.FreshWater.AquiferGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.FreshWater.LakeGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.Humidity.MoistureAdder;
import ca.mcmaster.cas.se2aa4.a3.island.Humidity.SoilProfile;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.*;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.*;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import org.locationtech.jts.geom.Geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class IslandBuilder extends AbstractBuilder {

    private final IslandShape islandShape;

    public void constructElevation (BaseElevation elevation){
        elevation.generateElevation(islandShape, findPolygonsWithinIsland());
    }

    public IslandBuilder(IslandShape shape){
        islandShape = shape;
    }

    @Override
    public void buildIsland(Mesh aMesh){

        extractFromMesh(aMesh);

        // Creates island shape.
        Geometry shape = islandShape.getShape();

        // Sets land tiles and neighbours.
        setTileInsideShape(shape, myPolygons, LandTile.class);
        setPolyNeighbours(myPolygons);

        // Lake generator
        new LakeGenerator(findPolygonsWithinIsland(), 0);

        // Aquifer generator
        new AquiferGenerator(findPolygonsWithinIsland(), 1, new Random());

    }

    public void addMoistureToPolygons(SoilProfile soilProfile){
        MoistureAdder moistureAdder = new MoistureAdder();
        moistureAdder.addMoistureToPolygons(findPolygonsWithinIsland(), soilProfile);
    }

    private List<MyPolygon> findPolygonsWithinIsland(){
        Geometry island = islandShape.getShape();
        List<MyPolygon> withinIsland = new ArrayList<>();
        for (MyPolygon p : myPolygons){
            if (island.contains(p.getJTSPolygon())){
                withinIsland.add(p);
            }
        }

        return withinIsland;
    }

    protected void setPolyNeighbours(List<MyPolygon> myPolygons){
        for (MyPolygon p1 : myPolygons){
            for (MyPolygon p2: myPolygons){
                p1.checkForNeighbour(p2);
            }
        }
    }

}
