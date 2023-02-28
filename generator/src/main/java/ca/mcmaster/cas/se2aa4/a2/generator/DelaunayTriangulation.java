package ca.mcmaster.cas.se2aa4.a2.generator;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.triangulate.DelaunayTriangulationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DelaunayTriangulation {

    private DelaunayTriangulationBuilder builder = new DelaunayTriangulationBuilder();
    private Set<PolygonClass> myPolygons;

    private List <List<Coordinate>> neigbours;

    private final double PRECISION;

    public DelaunayTriangulation (Set<PolygonClass> myPolygons, double precision) {
        this.myPolygons = myPolygons;
        this.PRECISION = precision;
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

    private void initialTriangulation (){
        List <Coordinate> centroids = findCentroids(myPolygons);

        builder.setSites(centroids);
        builder.setTolerance(PRECISION);
        GeometryCollection triangle = (GeometryCollection) builder.getTriangles(new GeometryFactory());


        //initializing a list of neighbours for each centroid
        neigbours = new ArrayList<>(centroids.size());
        for (int i = 0; i<centroids.size(); i++){
            neigbours.add(new ArrayList<>());
        }
        for (int i = 0; i< triangle.getNumGeometries(); i++){
            Coordinate [] triangleCentroid= triangle.getGeometryN(i).getCoordinates();
            System.out.println(triangleCentroid.length);
            for (int j = 0; j<3; j++){
                Coordinate cent = triangleCentroid[j];
                List <Coordinate> centroidNeighbour = neigbours.get(centroids.indexOf(cent));
                centroidNeighbour.add(cent);
                for (int k = 0; k<3; k++){
                    Coordinate neighbour = triangleCentroid[k];
                    if (!neighbour.equals(cent) && !centroidNeighbour.contains(neighbour)){
                        centroidNeighbour.add(neighbour);
                    }
                }
            }
        }
    }
    private PolygonClass returnPolygon (Coordinate c){
        for (PolygonClass p: myPolygons){
            double x = c.getX();
            double y = c.getY();
            if (p.checkIfCentroid(x,y,PRECISION)) {
                return p;
            }
        }
        return null;
    }

    private void setNeighbourIndices(){
        for (PolygonClass p: myPolygons){
            p.setNeighbourIndices();
        }
    }

    private void iterateNeighbours (){
        for (List <Coordinate> coords: neigbours) {
            for (Coordinate c1: coords){
                for (Coordinate c2: coords){
                    PolygonClass p1 = returnPolygon(c1);
                    PolygonClass p2 = returnPolygon(c2);
                    if (p1 == null || p2 == null || !p1.isNeighbour(p2)){
                        continue;
                    }
                    p1.addNeighbour(p2.getIndex());
                    p2.addNeighbour(p1.getIndex());
                }
            }
        }
    }

    public void createTriangulation(){
        initialTriangulation();
        System.out.println(neigbours.get(0).size());
        ;
        iterateNeighbours();
        setNeighbourIndices();
    }

}

