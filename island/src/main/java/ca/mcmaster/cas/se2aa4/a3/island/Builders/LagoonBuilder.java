package ca.mcmaster.cas.se2aa4.a3.island.Builders;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a3.island.Extractor.StructsToAdtExtractor;
import ca.mcmaster.cas.se2aa4.a3.island.IslandShapes.*;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.*;
import ca.mcmaster.cas.se2aa4.a3.island.*;
import org.locationtech.jts.geom.Geometry;

import java.util.List;

public class LagoonBuilder extends AbstractBuilder {

    public void buildIsland(Mesh aMesh){

        // Extracting the Structs from the input mesh and converts to our ADT
        StructsToAdtExtractor extractor = new StructsToAdtExtractor(aMesh);
        myVertices = extractor.getMyVertices();
        mySegments = extractor.getMySegments();
        myPolygons = extractor.getMyPolygons();

        // Creates island shape.
        IslandShape island = new Circle(500, 500);
        Geometry islandShape = island.getShape();

        // Sets land tiles.
        setTileInsideShape(islandShape, myPolygons, new LandTile());

        IslandShape innerLagoon = new LagoonInnerCircle(500, 500);
        Geometry innerCircle = innerLagoon.getShape();
        setTileInsideShape(innerCircle, myPolygons, new LagoonTile());

        // Goes through all polygons and sets neighbours, which also changes tiles to beaches if necessary.
        setPolyNeighbours(myPolygons);

    }

    private void setPolyNeighbours(List<MyPolygon> myPolygons){
        for (MyPolygon p1 : myPolygons){
            for (MyPolygon p2: myPolygons){
                p1.checkForNeighbour(p2);
            }
            p1.checkNeighboursForBeach();
        }
    }

}
