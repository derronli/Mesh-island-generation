package ca.mcmaster.cas.se2aa4.a3.island.FreshWater;

import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.LakeTile;
import ca.mcmaster.cas.se2aa4.a3.island.MyPolygon;
import ca.mcmaster.cas.se2aa4.a3.island.IslandADTTypes.Tiles.LakeTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
go through input list of polygons (will input only polygons in island)
method should take that list as input and nothing else
randomly choose polygons from list to add aquifers to up to user input number of times (set as field for now)
will have a method when choosing a polygon to call polygon.hasAquifer or something to ensure aquifer has not been added already and then regen index
polygon will have a method polygon.addAquifer which takes in no input, and that's all that needs to be called (polygon will handle moisture on its own)

also make when this generator is made, takes in random as parameter in constructor and sets field to it
*/

public class AquiferGenerator {

    private final int numAquifers;
    private final Random rand = new Random();
    private List <Integer> aquiferValues;
    private List <Integer> moistureValues;
    public AquiferGenerator(List<MyPolygon> myPolygons, int numAquifers) {
        moistureValues = new ArrayList<>(Collections.nCopies(moistureValues.size(), 0)); ///fill all tile moistures with 0 initially to be reassigned accordingly
        this.numAquifers = numAquifers;
        createAquifers(myPolygons);
        //generateRandomAquifers(myPolygons);
    }
    private void generateRandomAquifers(List<MyPolygon> polygons) {
        for (int i = 0; i < numAquifers; i++) {
            int moisture = rand.nextInt(1, 40);
            aquiferValues.add(moisture);
        }
    }

    private void assignToRandomTiles (List <MyPolygon> polygons){
    int counter = 0;
        for (int j = 0; j<polygons.size(); j++){
            int assignToPolygon = rand.nextInt(0,2);
            if (assignToPolygon== 0){
                //set to tile
                counter++;
                for (int k = 0; k<polygons.size(); k++){
                    if (polygons.get(j).checkForNeighbour(polygons.get(k))){
                        //set aquiferValue here
                    }
                }
            }
            if (counter == numAquifers){
                break;
            }
        }
    }

    private void createAquifers(List<MyPolygon> polygons){
        generateRandomAquifers(polygons);
        assignToRandomTiles(polygons);
    }
}


