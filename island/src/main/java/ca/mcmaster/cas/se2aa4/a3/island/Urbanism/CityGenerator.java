package ca.mcmaster.cas.se2aa4.a3.island.Urbanism;

import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import org.locationtech.jts.geom.Point;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class CityGenerator {

    private MyVertex pickRandomCentroid(List<MyPolygon> myPolygons, List<MyVertex> myVertices, Random rand) {
        MyPolygon p;
        Point center;
        // while loop is for ensuring that the city is not created on a "water tile" (aka lake)
        // While the pick is a lake -> choose something else
        do {
            int pIdx = rand.nextInt(myPolygons.size());
            p = myPolygons.get(pIdx);
            center = p.getCenterOfPolygon();
        } while (p.isWaterTile());

        return findVertex(myVertices, center);
    }

    private MyVertex findVertex(List<MyVertex> myVertices, Point target) {
        for (MyVertex v : myVertices) {
            if (v.getX() == target.getX() && v.getY() == target.getY()) {
                return v;
            }
        }
        return null;
    }

    public List<MyVertex> generate(List<MyPolygon> myPolygons, List<MyVertex> myVertices, int numCities, Random rand) {
        List<MyVertex> citiesList = new ArrayList<>();
        while (numCities != 0) {
            MyVertex centroid = pickRandomCentroid(myPolygons, myVertices, rand);
            if (centroid.makeCityVertex(rand.nextInt(9) + 3)) {
                numCities--;
                citiesList.add(centroid);
            }
        }
        return citiesList;
    }
}
