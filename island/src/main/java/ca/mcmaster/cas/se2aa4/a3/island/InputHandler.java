package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;

public class InputHandler {

    public Mesh createMesh(String[] args){

        Options options = createOptions();

        // Parses options with arguments.
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
            return checkOptions(line, options);
        }
        catch (ParseException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            return null;
        }

    }

    private Options createOptions(){
        Options options = new Options();

        Option help = new Option("h", "display all possible inputs");
        Option input = Option.builder("i")
                .argName("input file")
                .hasArg()
                .desc("file to read mesh from")
                .build();
        Option output = Option.builder("o")
                .argName("output file")
                .hasArg()
                .desc("file to output mesh to")
                .build();

        // add all options
        options.addOption(help);
        options.addOption(input);
        options.addOption(output);

        return options;
    }

    private void displayHelp(Options options){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("island help", options);
    }

    private Mesh checkOptions(CommandLine line, Options options){

        // If they ask for help, displays options, and exits without generating a mesh.
        if (line.hasOption("h")){
            displayHelp(options);
     //       polyTrans = Integer.parseInt(line.getOptionValue("pa"));
            return null;
        }
        // If they ask for help, displays options, and exits without generating a mesh.
        if (line.hasOption("i")){
            displayHelp(options);
            //       polyTrans = Integer.parseInt(line.getOptionValue("pa"));
            return null;
        }
        // If they ask for help, displays options, and exits without generating a mesh.
        if (line.hasOption("o")){
            displayHelp(options);
            //       polyTrans = Integer.parseInt(line.getOptionValue("pa"));
            return null;
        }

        return null;
    }

}
