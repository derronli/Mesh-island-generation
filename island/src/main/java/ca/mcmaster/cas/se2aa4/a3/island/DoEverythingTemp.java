package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Polygon;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Vertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.Set;

public class DoEverythingTemp {

    public Mesh makeMesh(Mesh aMesh){

        List<Vertex> vertices = aMesh.getVerticesList();
        List<Segment> segments = aMesh.getSegmentsList();
        List<Polygon> polygons = aMesh.getPolygonsList();
        List<MyVertex> myVertices = new ArrayList<>();
        List<MySegment> mySegments = new ArrayList<>();
        List<MyPolygon> myPolygons = new ArrayList<>();

        // Add all items to my structure.
        for (Vertex v : vertices){
            MyVertex myV = new MyVertex(v);
            myV.changeColor("0, 0, 0");
            myVertices.add(myV);
        }
        for (Segment s : segments){
            MySegment myS = new MySegment(s);
            myS.changeColor("0, 0, 0");
            mySegments.add(myS);
        }
        for (Polygon p : polygons){
            MyPolygon myP = new MyPolygon(p);
            myP.changeColor("0, 0, 0");
            myPolygons.add(myP);
        }

        return null;
    }
}
