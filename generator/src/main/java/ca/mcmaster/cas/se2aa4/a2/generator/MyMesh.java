package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.*;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.triangulate.VoronoiDiagramBuilder;

public class MyMesh {

    private final int width = 500;
    private final int height = 500;
    private final int square_size = 20;

    private final double PRECISION = 0.01;

    private final int NUM_POLYGONS = 100;
    private final Random rand = new Random();

    // Equality is defined as being within 0.01 of each other
    private boolean isEqual(MyVertex v1, MyVertex v2) {
        if (Math.abs(v1.getX() - v2.getX()) < PRECISION && Math.abs(v1.getY() - v2.getY()) < PRECISION) {
            return true;
        }
        return false;
    }

    public Mesh buildMesh() {

        Set<MyVertex> myVertices = new LinkedHashSet<>();
        Set<MySegment> mySegments = new LinkedHashSet<>();
        Set<PolygonClass> myPolygons = new LinkedHashSet<>();
        Set<Coordinate> voronoiPoints = new LinkedHashSet<>();

//        createVertices(myVertices);
//        createSegNPoly(mySegments, myPolygons, myVertices);
        VoronoiSegNPoly(myVertices, mySegments, myPolygons, voronoiPoints);
        setAllNeighbours(myPolygons);


        Set<Vertex> vertices = extractVertices(myVertices);
        Set<Segment> segments = extractSegments(mySegments);
        Set<Polygon> polygons = extractPolygons(myPolygons);
        return Mesh.newBuilder().addAllVertices(vertices).addAllSegments(segments).addAllPolygons(polygons).build();

    }

    // Create all vertices.
    private void createVertices(Set<MyVertex> myVertices) {

        for (int x = 0; x <= width; x += square_size) {
            for (int y = 0; y <= height; y += square_size) {
                MyVertex vertex = new MyVertex(x, y, 250);

                // testing thickness
                if (x < 150 && y < 150 || x > 350 && y > 350) {
                    vertex.setThickness(10);
                } else {
                    vertex.setThickness(3);
                }

                // testing transparency
                if (x < 100 && y < 100 || x > 350 && y < 100) {
                    vertex.setTrans(100);
                }

                myVertices.add(vertex);
            }
        }
    }

    // Creates segments connecting vertices as square shapes and polygons for these segments.
    private void createSegNPoly(Set<MySegment> mySegments, Set<PolygonClass> myPolygons, Set<MyVertex> myVertices) {
        for (int x = 0; x < width; x += square_size) {
            for (int y = 0; y < height; y += square_size) {

                int x2 = x + square_size;
                int y2 = y + square_size;

                // Creates vertices in a square.
                MyVertex v1 = findVertex(myVertices, x, y);
                MyVertex v2 = findVertex(myVertices, x2, y);
                MyVertex v3 = findVertex(myVertices, x2, y2);
                MyVertex v4 = findVertex(myVertices, x, y2);

                // Creates/finds the segments which connect the 4 vertices in a square shape.
                MySegment s1 = findSegment(mySegments, v1, v2);
                MySegment s2 = findSegment(mySegments, v2, v3);
                MySegment s3 = findSegment(mySegments, v3, v4);
                MySegment s4 = findSegment(mySegments, v4, v1);

                // testing thickness
                if (x >= 100 && x <= 400 && y >= 100 && y <= 400) {
                    s1.setThickness(2);
                    s2.setThickness(2);
                    s3.setThickness(2);
                    s4.setThickness(2);
                }

                // testing transparency
                if (x <= 300 && x >= 200) {
                    s1.setTrans(150);
                    s2.setTrans(150);
                    s3.setTrans(150);
                    s4.setTrans(150);
                }

                // Adds all segments to set, if they already exist and were found, it will just not add.
                mySegments.add(s1);
                mySegments.add(s2);
                mySegments.add(s3);
                mySegments.add(s4);


                ArrayList<MySegment> segments = new ArrayList<MySegment>();
                segments.add(s1);
                segments.add(s2);
                segments.add(s3);
                segments.add(s4);
                if (polygonDoesNotExist(myPolygons, segments)) {
                    PolygonClass polygon = new PolygonClass(segments);
                    myPolygons.add(polygon);
                    myVertices.add(polygon.getCentroid());
                }

            }
        }
    }

    // Sets input polygon's neighbours by checking it against all other polygons in set
    private void setNeighbours(Set<PolygonClass> myPolygons, PolygonClass polygon) {
        for (PolygonClass p : myPolygons) {
            if (polygon.isNeighbour(p)) {
                // add p index to the polygon.neighbours instance var
                polygon.addNeighbour(p.getIndex());
            }
        }
    }

    private void setAllNeighbours(Set<PolygonClass> myPolygons) {
        for (PolygonClass p : myPolygons) {
            setNeighbours(myPolygons, p);
            p.setNeighbourIndices();
        }
    }

    // Get a Polygon object given some integer index
    private PolygonClass getPolygonByIndex(Set<PolygonClass> myPolygons, int polygonIdx) {
        for (PolygonClass p : myPolygons) {
            // iterate through all polygons currently made, looking for the matching index
            if (p.getIndex() == polygonIdx) {
                return p;
            }
        }
        return null;
    }

    // Goes through PolygonClass list and returns list of all the polygons each one contains.
    private Set<Polygon> extractPolygons(Set<PolygonClass> myPolygons) {
        Set<Polygon> oPolygons = new LinkedHashSet<>();
        for (PolygonClass polygon : myPolygons) {
            oPolygons.add(polygon.getPolygon());
        }
        return oPolygons;
    }

    // Goes through MySegment list and returns list of all the segments each one contains.
    private Set<Segment> extractSegments(Set<MySegment> mySegments) {
        Set<Segment> oSegments = new LinkedHashSet<>();
        for (MySegment segment : mySegments) {
            oSegments.add(segment.getSegment());
        }
        return oSegments;
    }

    // Goes through MySegment list and returns list of all the segments each one contains.
    private Set<Vertex> extractVertices(Set<MyVertex> myVertices) {
        Set<Vertex> oVertices = new LinkedHashSet<>();
        for (MyVertex vertex : myVertices) {
            oVertices.add(vertex.getVertex());
        }
        return oVertices;
    }

    // Checks if a polygon in the list already represents the specified connection of segments.
    private boolean polygonDoesNotExist(Set<PolygonClass> polygons, List<MySegment> segments) {
        for (PolygonClass polygon : polygons) {
            if (polygon.equals(segments)) {
                return false;
            }
        }
        return true;
    }

    // Finds if there is a vertex at a certain point, creating a new one if there is not.
    private MyVertex findVertex(Set<MyVertex> vertices, double x, double y) {
        for (MyVertex vertex : vertices) {
            if (vertex.existsAtPoint(x, y, PRECISION)) {
                return vertex;
            }
        }
        return new MyVertex(x, y);
    }

    private boolean isDuplicatePoint(Set<Coordinate> voronoiPoints, double x, double y) {
        for (Coordinate point : voronoiPoints) {
            if (point.getX() == x && point.getY() == y) { // HAVE NOT IMPLEMENTED PRECISION YET
                return true;
            }
        }
        return false;
    }

    // Finds if there is a segment connecting two points, creating a new one if there is not.
    private MySegment findSegment(Set<MySegment> segments, MyVertex v1, MyVertex v2) {
        for (MySegment segment : segments) {
            if (segment.equals(v1.getIndex(), v2.getIndex())) {
                return segment;
            }
        }
        return new MySegment(v1, v2);
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

    private List<org.locationtech.jts.geom.Polygon> createVoronoiAboutPoints(Set<Coordinate> voronoiPoints) {
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

//            if (polygonDoesNotExist(myPolygons, polySegments)) {
//                PolygonClass polygon = new PolygonClass(polySegments);
//                myPolygons.add(polygon);
//                myVertices.add(polygon.getCentroid());
//            }
        }
    }
}

