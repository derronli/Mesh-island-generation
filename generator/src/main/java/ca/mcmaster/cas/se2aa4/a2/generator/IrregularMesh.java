package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import java.util.*;

public class IrregularMesh extends MyMesh{

    private final int NUM_POLYGONS;
    private final Random rand = new Random();
    private final int RELAXATION_LEVEL;

    public IrregularMesh(int numPolygons, int relaxation){
        NUM_POLYGONS = numPolygons;
        RELAXATION_LEVEL = relaxation;
    }

    public Structs.Mesh buildMesh(int polyTrans, int segTrans, int vertexTrans, float polyThick, float segThick,
                                  int vertexThick) {

        Set<MyVertex> myVertices = new LinkedHashSet<>();
        Set<MySegment> mySegments = new LinkedHashSet<>();
        Set<PolygonClass> myPolygons = new LinkedHashSet<>();
        Set<Coordinate> voronoiPoints = new LinkedHashSet<>();

        VoronoiSegNPoly(myVertices, mySegments, myPolygons, voronoiPoints);
        setAllNeighbours(myPolygons);

        setAllPolyTrans(myPolygons, polyTrans);
        setAllSegTrans(mySegments, segTrans);
        setAllVertexTrans(myVertices, vertexTrans);
        setAllPolyThick(myPolygons, polyThick);
        setAllSegThick(mySegments, segThick);
        setAllVertexThick(myVertices, vertexThick);

        Set<Structs.Vertex> vertices = extractVertices(myVertices);
        Set<Structs.Segment> segments = extractSegments(mySegments);
        Set<Structs.Polygon> polygons = extractPolygons(myPolygons);
        return Structs.Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build();

    }

    // ========================= CHANGES
    // DO WE WANT TO STORE THE POINTS AS VERTICES???
    // These points are only relevant to the original generation of the irregular mesh
    private void createRandomPoints(Set<Coordinate> voronoiPoints) {
        int x = -1;
        int y = -1;
        Coordinate point;

        // Generates a random vertex for each polygon
        for (int i = 0; i <= NUM_POLYGONS; i++) {
            // Keep generating random x and y until a unique coordinate is found
            while ((x == -1 && y == -1) || isDuplicatePoint(voronoiPoints, x, y)) {
                x = rand.nextInt(width);
                y = rand.nextInt(height);
            }

            point = new Coordinate(x, y);

            // myVertices.add(vertex); // MIGHT BE UNNECESSARY -> ONLY NEED IT NOW BECAUSE OUR POINTS ARE STORED AS VERTEXES
            // WITHOUT THIS IT SCREWS WITH THE INDEXES
            voronoiPoints.add(point);
        }
    }
    // RIGHT NOW ONLY INTEGERS

    private List<Polygon> createVoronoiAboutPoints(Set<Coordinate> voronoiPoints) {
        VoronoiDiagramBuilder voronoi = new VoronoiDiagramBuilder();
        GeometryFactory factory = new GeometryFactory();

        createRandomPoints(voronoiPoints);

        voronoi.setSites(voronoiPoints);
        return voronoi.getSubdivision().getVoronoiCellPolygons(factory);

    }

    private void VoronoiSegNPoly(Set<MyVertex> myVertices, Set<MySegment> mySegments, Set<PolygonClass> myPolygons, Set<Coordinate> voronoiPoints) {
        List<org.locationtech.jts.geom.Polygon> polygons = createVoronoiAboutPoints(voronoiPoints);
        Coordinate[] polyCoords;
        MyVertex v1, v2;
        MySegment s;
        Coordinate c1, c2;
        int count = 0;

        do {

            for (org.locationtech.jts.geom.Polygon p : polygons) {
                polyCoords = p.getCoordinates();
                // Create 2 segments at a time by looking at 2 coordinates at once -> coordinates correspond to every vertex in the polygon
                ArrayList<MySegment> polySegments = new ArrayList<>();
                for (int i = 0; i < polyCoords.length; i++) {
                    // Gets Coordinate pair
                    c1 = polyCoords[i];
                    // Handles edge case of the last point not having a + 1 index
                    // Last point should connect to the originally FIRST point
                    if (i == polyCoords.length - 1) {
                        c2 = polyCoords[0];
                    } else {
                        c2 = polyCoords[i + 1];
                    }

                    // Ensures we don't get any coordinates out of bounds.
                    if (c1.getX() > width || c1.getY() > height || c2.getX() > width || c2.getY() > height) {
                        continue;
                    }

                    // Checks if (x,y) pair is a preexisting vertex -> will make new one if not
                    v1 = findVertex(myVertices, c1.getX(), c1.getY());
                    v2 = findVertex(myVertices, c2.getX(), c2.getY());

                    myVertices.add(v1);
                    myVertices.add(v2);

                    s = findSegment(mySegments, v1, v2);

                    mySegments.add(s);
                    polySegments.add(s);
                }

                if (polygonDoesNotExist(myPolygons, polySegments) && polySegments.size() > 0) {
                    PolygonClass polygon = new PolygonClass(polySegments);
                    myPolygons.add(polygon);
                    myVertices.add(polygon.getCentroid()); // Don't want to add this until the last relaxed centroid
                }
            }
            // Continue to compute the voronoi diagram -> as a part of lloyd relaxation
            // Until the relaxation level is reached
            count++;

            // Make sure it's not the last relaxation level
            if (count != RELAXATION_LEVEL) {
                // Reset the collections in preparation of the next voronoi generation
                myVertices = new LinkedHashSet<>();
                mySegments = new LinkedHashSet<>();
                polygons = relaxLloyd(myPolygons, voronoiPoints);
                myPolygons = new LinkedHashSet<>();
            }

        } while (count < RELAXATION_LEVEL);
    }

    // Generates a new set of voronoiPoints and voronoi diagram given the previously generated polygon centroids
    private List<org.locationtech.jts.geom.Polygon> relaxLloyd(Set<PolygonClass> myPolygons, Set<Coordinate> voronoiPoints) {
        voronoiPoints = new LinkedHashSet<>();
        MyVertex centroidVertex;
        Coordinate newPoint;

        // new voronoi points will be the previously computed centroids
        for (PolygonClass p : myPolygons) {
            centroidVertex = p.getCentroid();
            newPoint = new Coordinate(centroidVertex.getX(), centroidVertex.getY());
            voronoiPoints.add(newPoint);
        }

        // Compute the new voronoi diagram with the set of voronoi points
        return createVoronoiAboutPoints(voronoiPoints);
    }

    private boolean isDuplicatePoint(Set<Coordinate> voronoiPoints, double x, double y) {
        for (Coordinate point : voronoiPoints) {
            if (point.getX() == x && point.getY() == y) { // HAVE NOT IMPLEMENTED PRECISION YET
                return true;
            }
        }
        return false;
    }

}
