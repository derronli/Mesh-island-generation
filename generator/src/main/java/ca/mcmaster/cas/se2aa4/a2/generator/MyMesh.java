package ca.mcmaster.cas.se2aa4.a2.generator;

import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;
import org.locationtech.jts.geom.Geometry;


public class MyMesh {

    protected final int width = 500;
    protected final int height = 500;

    private Set<Coordinate> voronoiPoints = new LinkedHashSet<>();
    protected final int PRECISION = 1;


    public Mesh buildMesh(int polyTrans, int segTrans, int vertexTrans, float polyThick, float segThick, float vertexThick) {

        Set<MyVertex> myVertices = new LinkedHashSet<>();
        Set<MySegment> mySegments = new LinkedHashSet<>();
        Set<PolygonClass> myPolygons = new LinkedHashSet<>();

        // myPolygons = VoronoiSegNPoly(myVertices, mySegments, myPolygons);

        // setAllNeighbours(myPolygons);


        Set<Vertex> vertices = extractVertices(myVertices);
        Set<Segment> segments = extractSegments(mySegments);
        Set<Polygon> polygons = extractPolygons(myPolygons);

        return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build();

    }

    // Sets input polygon's neighbours by checking it against all other polygons in set
    protected void setNeighbours(Set<PolygonClass> myPolygons, PolygonClass polygon) {
        for (PolygonClass p : myPolygons) {
            if (polygon.isNeighbour(p)) {
                // add p index to the polygon.neighbours instance var
                polygon.addNeighbour(p.getIndex());
            }
        }
    }

    // Set's neighbours for all created polygons in the hashset
    protected void setAllNeighbours(Set<PolygonClass> myPolygons) {
        for (PolygonClass p : myPolygons) {
            setNeighbours(myPolygons, p);
            p.setNeighbourIndices();
        }
    }

    // Goes through PolygonClass list and returns list of all the polygons each one contains.
    protected Set<Polygon> extractPolygons(Set<PolygonClass> myPolygons) {
        Set<Polygon> oPolygons = new LinkedHashSet<>();
        for (PolygonClass polygon : myPolygons) {
            oPolygons.add(polygon.getPolygon());
        }
        return oPolygons;
    }

    // Goes through MySegment list and returns list of all the segments each one contains.
    protected Set<Segment> extractSegments(Set<MySegment> mySegments) {
        Set<Segment> oSegments = new LinkedHashSet<>();
        for (MySegment segment : mySegments) {
            oSegments.add(segment.getSegment());
        }
        return oSegments;
    }

    // Goes through MySegment list and returns list of all the segments each one contains.
    protected Set<Vertex> extractVertices(Set<MyVertex> myVertices) {
        Set<Vertex> oVertices = new LinkedHashSet<>();
        for (MyVertex vertex : myVertices) {
            oVertices.add(vertex.getVertex());
        }
        return oVertices;
    }

    // Checks if a polygon in the list already represents the specified connection of segments.
    protected boolean polygonDoesNotExist(Set<PolygonClass> polygons, List<MySegment> segments) {
        for (PolygonClass polygon : polygons) {
            if (polygon.equals(segments)) {
                return false;
            }
        }
        return true;
    }

    // Finds if there is a vertex at a certain point, creating a new one if there is not.
    protected MyVertex findVertex(Set<MyVertex> vertices, double x, double y) {
        for (MyVertex vertex : vertices) {
            if (vertex.existsAtPoint(x, y, PRECISION)) {
                return vertex;
            }
        }
        return new MyVertex(x, y);
    }

    // Finds if there is a segment connecting two points, creating a new one if there is not.
    protected MySegment findSegment(Set<MySegment> segments, MyVertex v1, MyVertex v2) {
        for (MySegment segment : segments) {
            if (segment.equals(v1.getIndex(), v2.getIndex())) {
                return segment;
            }
        }
        return new MySegment(v1, v2);
    }

    // Generates random Coordinate points and saves in hashset for initial generation of voronoi diagram
    private void createRandomPoints() {
        int x = -1;
        int y = -1;
        Coordinate point;

        // Generates a random vertex for each polygon
        for (int i = 0; i < NUM_POLYGONS; i++) {
            // Keep generating random x and y until a unique coordinate is found
            while ((x == -1 && y == -1) || isDuplicatePoint( x, y)) {
                x = rand.nextInt(width);
                y = rand.nextInt(height);
            }
            point = new Coordinate(x, y);

            voronoiPoints.add(point);
        }
    }
    // RIGHT NOW ONLY INTEGERS
    // Generates the voronoi diagram Geometry object
    private List<Geometry> createVoronoiAboutPoints() {
        List<Geometry> polyList = new ArrayList<>();
        VoronoiDiagramBuilder voronoi = new VoronoiDiagramBuilder();
        GeometryFactory factory = new GeometryFactory();

        voronoi.setSites(voronoiPoints);

        Geometry g =  voronoi.getDiagram(factory); // creates voronoi



        // Specifies size of canvas
        Envelope envelope = new Envelope(0, width, 0, height);
        g = g.intersection(factory.toGeometry(envelope));


        // Adding all geometries that make the voronoi to an arraylist
        for (int k = 0; k < g.getNumGeometries(); k++) {
            polyList.add(g.getGeometryN(k));
        }
        return polyList;
    }
    // Converts the voronoi diagram object into vertices, segments and polygons (from JTS to io Structs)
    private Set<PolygonClass> VoronoiSegNPoly(Set<MyVertex> myVertices, Set<MySegment> mySegments, Set<PolygonClass> myPolygons) {
        createRandomPoints();
        List<org.locationtech.jts.geom.Geometry> polygons = createVoronoiAboutPoints();
        Coordinate[] polyCoords;
        MyVertex v1, v2;
        MySegment s;
        Coordinate c1, c2;
        int count = 0;

        do {
            for (org.locationtech.jts.geom.Geometry p : polygons) {
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

            // Make sure its not the last relaxation level
            if (count != RELAXATION_LEVEL) {
                // Reset the collections in preparation of the next voronoi generation
                MyVertex.resetCount();
                MySegment.resetCount();
                myVertices.clear();

                mySegments.clear();
                polygons = relaxLloyd(myPolygons);
                PolygonClass.resetCount();
                myPolygons.clear();
            }

        } while (count < RELAXATION_LEVEL);

        return myPolygons;
    }

    // Generates a new set of voronoiPoints and voronoi diagram given the previously generated polygon centroids
    private List<org.locationtech.jts.geom.Geometry> relaxLloyd(Set<PolygonClass> myPolygons) {
        voronoiPoints = new LinkedHashSet<>();
        MyVertex centroidVertex;
        Coordinate newPoint;
        // Compute the new voronoi diagram with the set of voronoi points
        return createVoronoiAboutPoints();
    }
    // Setters to use command line arguments for all values.
    protected void setShapeThick(Set<? extends MyShape> myShapes, float thickness){
        for (MyShape shape : myShapes){
            shape.setThick(thickness);
        }
    }
    protected void setShapeTrans(Set<? extends MyShape> myShapes, int trans){
        for (MyShape shape : myShapes){
            shape.setTrans(trans);
        }
    }
}

