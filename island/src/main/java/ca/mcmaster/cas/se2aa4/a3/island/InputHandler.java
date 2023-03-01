package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;

import java.io.IOException;

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
        catch (ParseException | IOException exp) {
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

    private Mesh checkOptions(CommandLine line, Options options)  throws IOException {

        String inputFile = null, outputFile = null;

        // If they ask for help, displays options, and exits without generating a mesh.
        if (line.hasOption("h")){
            displayHelp(options);
            return null;
        }
        if (line.hasOption("i")){
            inputFile = line.getOptionValue("i");
        }
        if (line.hasOption("o")){
            outputFile = line.getOptionValue("o");
        }

        // Ensures we have an input and output file before creating island.
        if (!(inputFile == null || outputFile == null)){
            Mesh aMesh = new MeshFactory().read(inputFile);
            DoEverythingTemp d = new DoEverythingTemp();
            return d.makeMesh(aMesh);
        }

        return null;
    }

}
