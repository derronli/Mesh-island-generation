package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;

public class InputHandler {

    private final float VERTEXTHICKNESS = 3f;
    private final float POLYSEGTHICKNESS = 0.5f;
    private final int alpha = 255;
    private final int numPolygons = 100;
    private final int relaxation = 0;

    public void visualizeMesh(String[] args){

        Options options = createOptions();

        // Parses options with arguments.
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
            checkOptions(line, options);
        }
        catch (ParseException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
        }

    }

    private Options createOptions(){
        Options options = new Options();

        Option help = new Option("h", "display all possible inputs");
        Option type = new Option("ir", "creates an irregular mesh if used, grid if not used");
        Option pa = Option.builder("pa")
                .argName("transparency")
                .hasArg()
                .desc("define polygon transparency (value from 0 to 255) (default = 255)")
                .build();
        Option sa = Option.builder("sa")
                .argName("transparency")
                .hasArg()
                .desc("define segment transparency (value from 0 to 255) (default = 255)")
                .build();
        Option va = Option.builder("va")
                .argName("transparency")
                .hasArg()
                .desc("define vertex transparency (value from 0 to 255) (default = 255)")
                .build();
        Option pt = Option.builder("pt")
                .argName("thickness")
                .hasArg()
                .desc("define polygon thickness (value from 0 to 255) (default = 255)")
                .build();
        Option st = Option.builder("st")
                .argName("thickness")
                .hasArg()
                .desc("define segment thickness (value from 0 to 255) (default = 255)")
                .build();
        Option vt = Option.builder("vt")
                .argName("thickness")
                .hasArg()
                .desc("define vertex thickness (value from 0 to 255) (default = 255)")
                .build();
        Option np = Option.builder("np")
                .argName("number of polygons")
                .hasArg()
                .desc("Choose number of polygons in irregular mesh (default = 30)")
                .build();
        Option rl = Option.builder("rl")
                .argName("relaxation level")
                .hasArg()
                .desc("Choose number of lloyd relaxations to apply in irregular mesh (default = 0)")
                .build();

        // add all options
        options.addOption(help);
        options.addOption(type);
        options.addOption(pa);
        options.addOption(sa);
        options.addOption(va);
        options.addOption(pt);
        options.addOption(st);
        options.addOption(vt);
        options.addOption(np);
        options.addOption(rl);

        return options;
    }

    private void displayHelp(Options options){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("generator help", options);
    }

    private void checkOptions(CommandLine line, Options options){

        String inputFile = null, outputFile = null, mode = null;

        // If they ask for help, displays options, and exits without generating a mesh.
        if (line.hasOption("h")){
            displayHelp(options);
            return;
        }
        if (line.hasOption("i")){
            inputFile = line.getOptionValue("i");
        }
        if (line.hasOption("o")){
            outputFile = line.getOptionValue("o");
        }
        if (line.hasOption("mode")){
            mode = line.getOptionValue("mode");
        }

        return;


    }

}
