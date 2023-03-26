package ca.mcmaster.cas.se2aa4.a3.island;

public interface MyShape {
    void changeColor(String colorCode);
    int getIndex();

    int getElevation();

    double getMoisture();
    double getMoistureProvided();
}
