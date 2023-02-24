package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Property;

public interface MyShape {

    void setTrans(int alpha);
    void setThick(float thickness);
    int getIndex();
    int[] getColor();
    java.util.List<Property> getPropertiesList();
}
