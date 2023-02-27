package ca.mcmaster.cas.se2aa4.a2.generator;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;

import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.triangulate.quadedge.LocateFailureException;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DelaunayTriangulation {

    private DelaunayTriangulationBuilder builder = new DelaunayTriangulationBuilder();
    private Set<Coordinate> voronoiPoints;
    private List <MyVertex> myVertices;
    private Set<MySegment> mySegments;
    private Set<PolygonClass> myPolygons;

    public DelaunayTriangulation (Set<Coordinate> voronoiPoints, List <MyVertex> myVertices, Set<MySegment> mySegments, Set<PolygonClass> myPolygons) {
        this.voronoiPoints = voronoiPoints;
        this.myVertices = myVertices;
        this.mySegments = mySegments;
        this.myPolygons = myPolygons;
    }

    private List <Coordinate> findCentroids (Set <PolygonClass> myPolygons) {
        List <Coordinate> centroids = new ArrayList<>();
        for (PolygonClass polygon : myPolygons) {
            MyVertex centroidVertex = polygon.getCentroid();
            Coordinate temp = new Coordinate(centroidVertex.getX(), centroidVertex.getY());
            centroids.add(temp);
        }
        return centroids;
    }

    public void computeTriangulation (double precision){
        List <Coordinate> centroids = findCentroids(myPolygons);

        builder.setSites(centroids);
        builder.setTolerance(precision);
        GeometryCollection triangle = (GeometryCollection) builder.getTriangles(new GeometryFactory());


        //initializing a list of neighbours for each centroid
        List <List<Coordinate>> neigbours = new ArrayList<>(centroids.size());
        for (int i = 0; i<centroids.size(); i++){
            neigbours.add(new ArrayList<>());
        }

        for (int i = 0; i< triangle.getNumGeometries(); i++){
            Coordinate [] triangleCentroid= triangle.getGeometryN(i).getCoordinates();
            for (int j = 0; j<3; j++){
                Coordinate cent = triangleCentroid[j];
                List <Coordinate> centroidNeighbour = neigbours.get(centroids.indexOf(cent));
                for (int k = 0; k<3; k++){
                    Coordinate neighbour = triangleCentroid[k];
                    if (!neighbour.equals(cent) && !centroidNeighbour.contains(neighbour)){
                        centroidNeighbour.add(neighbour);
                    }
                }
            }

        }
    }
}
