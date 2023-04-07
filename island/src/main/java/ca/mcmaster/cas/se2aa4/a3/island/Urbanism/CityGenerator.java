package ca.mcmaster.cas.se2aa4.a3.island.Urbanism;

import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.ShapeAdts.MyVertex;
import org.locationtech.jts.geom.Point;

import java.util.Random;
import java.util.List;

public class CityGenerator {

    private MyVertex pickRandomCentroid(List<MyPolygon> myPolygons, List<MyVertex> myVertices, Random rand) {
        int pIdx = rand.nextInt(myPolygons.size());
        MyPolygon p = myPolygons.get(pIdx);

        Point center = p.getCenterOfPolygon();

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

    public void generate(List<MyPolygon> myPolygons, List<MyVertex> myVertices, int numCities, Random rand) {
        while (numCities != 0) {
            MyVertex centroid = pickRandomCentroid(myPolygons, myVertices, rand);
            if (centroid.makeCityVertex(rand.nextInt(9) + 3)) {
                numCities--;
            }
        }
    }
}
