package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Segment;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public class MySegment {

    private static int totalIndex = 0;
    private final int index;
    private final int v1Index, v2Index;
    private Segment segment;

    public MySegment(MyVertex v1, MyVertex v2){
        this.index = totalIndex;
        totalIndex++;
        v1Index = v1.getIndex();
        v2Index = v2.getIndex();

        segment = null;
    }


    // Getters
    public int getIndex() {
        return index;
    }
    public int getV1Index(){
        return v1Index;
    }
    public int getV2Index(){
        return v2Index;
    }

}
