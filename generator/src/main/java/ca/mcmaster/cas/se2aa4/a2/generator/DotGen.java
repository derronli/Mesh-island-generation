package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

public class DotGen {

    public Mesh generate(int polyTrans, int segTrans, int vertexTrans, float polyThick, float segThick, int vertexThick) {
        MyMesh mesh = new MyMesh();
        return mesh.buildMesh();
    }
}
