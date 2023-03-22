package ca.mcmaster.cas.se2aa4.a3.island.Extractor;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.MySegment;
import ca.mcmaster.cas.se2aa4.a3.island.MyVertex;

import java.util.ArrayList;
import java.util.List;

public class StructsToAdtExtractor {

    private final List<MyVertex> myVertices = new ArrayList<>();
    private final List<MySegment> mySegments = new ArrayList<>();
    private final List<MyPolygon> myPolygons = new ArrayList<>();

    public StructsToAdtExtractor(Structs.Mesh aMesh) {
        List<Structs.Vertex> vertices = aMesh.getVerticesList();
        List<Structs.Segment> segments = aMesh.getSegmentsList();
        List<Structs.Polygon> polygons = aMesh.getPolygonsList();

        // Add all items to my structure.
        for (Structs.Vertex v : vertices){
            MyVertex myV = new MyVertex(v);
            myV.changeColor("0,0,0");
            myVertices.add(myV);
        }
        for (Structs.Segment s : segments){
            MySegment myS = new MySegment(s);
            myS.changeColor("0,0,0");
            mySegments.add(myS);
        }
        for (Structs.Polygon p : polygons){
            MyPolygon myP = new MyPolygon(p);
            myPolygons.add(myP);
        }

        addVerticesToSegments(myVertices, mySegments);
        addSegmentsToPolygons(mySegments, myPolygons);

    }

    // Adds MyVertex instances to MySegment as a field.
    private void addVerticesToSegments(List<MyVertex> myVertices, List<MySegment> mySegments){
        for (MySegment s : mySegments){
            int v1Idx = s.getV1Index();
            int v2Idx = s.getV2Index();
            MyVertex v1 = myVertices.get(v1Idx);
            MyVertex v2 = myVertices.get(v2Idx);
            s.setV1(v1); s.setV2(v2);
        }
    }

    // Add MySegment instances to MyPolygon as a field.
    private void addSegmentsToPolygons(List<MySegment> mySegments, List<MyPolygon> myPolygons){
        for (MyPolygon p : myPolygons){
            for (Integer idx : p.getSegmentIdxsList()){
                MySegment s = mySegments.get(idx);
                p.addSegment(s);
            }
        }
    }

    public List<MyPolygon> getMyPolygons() {return myPolygons;}
    public List<MySegment> getMySegments() {return mySegments;}
    public List<MyVertex> getMyVertices() {return myVertices;}

}
