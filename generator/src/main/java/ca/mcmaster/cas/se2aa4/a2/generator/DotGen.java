package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class DotGen {

    public Mesh generate(int polyTrans, int segTrans, int vertexTrans, float polyThick, float segThick, int vertexThick) {
        MyMesh mesh = new GridMesh();
        return mesh.buildMesh();
    }

    public Mesh generate(int polyTrans, int segTrans, int vertexTrans, float polyThick, float segThick,
                         int vertexThick, int numPolygons, int relaxation) {
        MyMesh mesh = new IrregularMesh();
        return mesh.buildMesh();
    }
}
