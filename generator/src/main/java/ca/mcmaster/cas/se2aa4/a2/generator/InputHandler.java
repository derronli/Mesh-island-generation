package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;

public class InputHandler {

    private final int VERTEXTHICKNESS = 3;
    private final float POLYSEGTHICKNESS = 0.5f;
    private final int alpha = 255;
    private final int numPolygons = 30;
    private final int relaxation = 1;

    public Mesh createMesh(String[] args){

        Options options = new Options();



        Option help = new Option("-help", "display all possible inputs");
        Option pa = new Option("pa", "define polygon transparency (value from 0 to 255) (default = 255)");


        // Parses options with arguments.
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
        }
        catch (ParseException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
        }

        // logic to see which options were used and set variables accordingly.
        // initialize all necessary variables first, then check, and set to input if valid, default if not


        DotGen generator = new DotGen();
        return generator.generate(alpha, alpha, alpha, POLYSEGTHICKNESS, POLYSEGTHICKNESS, VERTEXTHICKNESS);
    }

}
