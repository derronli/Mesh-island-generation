package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.locationtech.jts.algorithm.ConvexHull;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

import java.util.*;

public class IrregularMesh extends MyMesh{

    private final int NUM_POLYGONS;
    private final Random rand = new Random();
    private final int RELAXATION_LEVEL;

    private final PrecisionModel precisionModel = new PrecisionModel(1);

    public IrregularMesh(int numPolygons, int relaxation){
        NUM_POLYGONS = numPolygons;
        RELAXATION_LEVEL = relaxation;
    }

    public Structs.Mesh buildMesh(int polyTrans, int segTrans, int vertexTrans, float polyThick, float segThick,
                                  float vertexThick) {

        Set<MyVertex> myVertices = new LinkedHashSet<>();
        Set<MySegment> mySegments = new LinkedHashSet<>();
        Set<PolygonClass> myPolygons = new LinkedHashSet<>();
        Set<Coordinate> voronoiPoints = new LinkedHashSet<>();


        createVoronoiSegNPoly(voronoiPoints, myVertices, mySegments, myPolygons);
        setAllNeighbours(myPolygons);

        setShapeTrans(myPolygons, polyTrans);
        setShapeTrans(mySegments, segTrans);
        setShapeTrans(myVertices, vertexTrans);
        setShapeThick(myPolygons, polyThick);
        setShapeThick(mySegments, segThick);
        setShapeThick(myVertices, vertexThick);

        Set<Structs.Vertex> vertices = extractVertices(myVertices);
        Set<Structs.Segment> segments = extractSegments(mySegments);
        Set<Structs.Polygon> polygons = extractPolygons(myPolygons);
        return Structs.Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build();

    }

    // These points are only relevant to the original generation of the irregular mesh
    // Generates random Coordinate points and saves in hashset for initial generation of voronoi diagram
    private void createRandomPoints(Set<Coordinate> voronoiPoints) {
        int x = -1;
        int y = -1;
        Coordinate point;

        // Generates a random vertex for each polygon
        for (int i = 0; i < NUM_POLYGONS; i++) {
            // Keep generating random x and y until a unique coordinate is found
            while ((x == -1 && y == -1) || isDuplicatePoint(voronoiPoints, x, y)) {
                x = rand.nextInt(width);
                y = rand.nextInt(height);
            }
            point = new Coordinate(x, y);

            voronoiPoints.add(point);
        }
    }
    // Generates the voronoi diagram Geometry object
    private List<Geometry> createVoronoiAboutPoints(Set<Coordinate> voronoiPoints) {
        List<Geometry> polyList = new ArrayList<>();
        VoronoiDiagramBuilder voronoi = new VoronoiDiagramBuilder();
        GeometryFactory factory = new GeometryFactory();

        voronoi.setSites(voronoiPoints);

        Geometry g =  voronoi.getDiagram(factory); // creates voronoi
        for (Coordinate c : g.getCoordinates()) {
            precisionModel.makePrecise(c);
        }

        // Specifies size of canvas
        Envelope envelope = new Envelope(0, width, 0, height);
        g = g.intersection(factory.toGeometry(envelope));

        // Adding all geometries that make the voronoi to an arraylist
        for (int k = 0; k < g.getNumGeometries(); k++) {
            polyList.add(new ConvexHull(g.getGeometryN(k)).getConvexHull());
        }
        return polyList;
    }

    // Converts the voronoi diagram object into vertices, segments and polygons (from JTS to io Structs)
    private void createVoronoiSegNPoly(Set<Coordinate> voronoiPoints, Set<MyVertex> myVertices, Set<MySegment> mySegments, Set<PolygonClass> myPolygons) {
        createRandomPoints(voronoiPoints);
        List<Geometry> polygons = createVoronoiAboutPoints(voronoiPoints);
        Coordinate[] polyCoords;
        MyVertex v1, v2;
        MySegment s;
        Coordinate c1, c2;
        int count = 0;

        do {

            // Reset counter in classes which serves to count index.
            MyVertex.resetCount();
            MySegment.resetCount();
            PolygonClass.resetCount();

            // Reset the collections in preparation of the next voronoi generation
            myVertices.clear();
            mySegments.clear();
            myPolygons.clear();

            for (Geometry p : polygons) {

                polyCoords = p.getCoordinates();
                // Create 2 segments at a time by looking at 2 coordinates at once -> coordinates correspond to every vertex in the polygon
                ArrayList<MySegment> polySegments = new ArrayList<>();
                for (int i = 0; i < polyCoords.length - 1; i++) {
                    // Gets Coordinate pair
                    c1 = polyCoords[i];
                    c2 = polyCoords[i + 1];

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
                    PolygonClass polygon = new PolygonClass(polySegments, p.getCentroid().getX(), p.getCentroid().getY());
                    myPolygons.add(polygon);
                    myVertices.add(polygon.getCentroid()); // Don't want to add this until the last relaxed centroid
                }
            }
            // Continue to compute the voronoi diagram -> as a part of lloyd relaxation
            // Until the relaxation level is reached
            count++;

            // Make sure it's not the last relaxation level
            if (count < RELAXATION_LEVEL) {
                polygons = relaxLloyd(voronoiPoints, myPolygons);
            }

        } while (count < RELAXATION_LEVEL);

    }

    // Generates a new set of voronoiPoints and voronoi diagram given the previously generated polygon centroids
    private List<Geometry> relaxLloyd(Set<Coordinate> voronoiPoints, Set<PolygonClass> myPolygons) {
        voronoiPoints.clear();
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

    protected void setAllNeighbours(Set<PolygonClass> myPolygons) {
        DelaunayTriangulation d = new DelaunayTriangulation(myPolygons, PRECISION);
        d.createTriangulation();
    }
}
