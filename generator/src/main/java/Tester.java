import ca.mcmaster.cas.se2aa4.a2.generator.MySegment;
import ca.mcmaster.cas.se2aa4.a2.generator.MyVertex;
import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.Arrays;

public class Tester {

    public static void main(String[] args){

        
        // Tests vertex class
        /**
        MyVertex v1 = new MyVertex(4,5);
        MyVertex v2 = new MyVertex(46,15);

        // Tests changing colour method
        System.out.println("Current colour: " + Arrays.toString(v1.getColour()));

        for(Structs.Property p: v1.getPropertiesList()){
            System.out.println(p.getKey() + ": " + p.getValue());
        }

        System.out.println("\n\nChanging colour\n\n");

        v1.setColour("232,64,05");
        System.out.println("Current colour: " + Arrays.toString(v1.getColour()));
        for(Structs.Property p: v1.getPropertiesList()){
            System.out.println(p.getKey() + ": " + p.getValue());
        }

         */

        //////////////////////////////////////////////////////////////////////////////////////////////

        // Tests segments class
        /**
        MySegment s1 = new MySegment(v1, v2);

        // Tests changing colour method
        System.out.println("Current colour: " + Arrays.toString(s1.getColour()));

        for(Structs.Property p: s1.getPropertiesList()){
            System.out.println(p.getKey() + ": " + p.getValue());
        }

        System.out.println("\n\nChanging colour\n\n");

        s1.setColour("232,64,5");
        System.out.println("Current colour: " + Arrays.toString(v1.getColour()));
        for(Structs.Property p: v1.getPropertiesList()){
            System.out.println(p.getKey() + ": " + p.getValue());
        }

         */

    }

}
