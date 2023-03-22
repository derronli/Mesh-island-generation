package ca.mcmaster.cas.se2aa4.a2.visualizer;

import org.apache.commons.cli.*;

import java.io.IOException;

public class InputParser {

    public void readInput(String[] args){

        Options options = createOptions();

        // Parses options with arguments.
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
            checkOptions(line, options);
        }
        catch (ParseException | IOException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
        }



    }

    private Options createOptions(){
        Options options = new Options();

        Option help = new Option("h", "display all possible inputs");
        Option debug = new Option("X", "Enter debug mode (A2)");
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
        options.addOption(debug);
        options.addOption(input);
        options.addOption(output);

        return options;
    }

    private void displayHelp(Options options){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("visualizer help", options);
    }

    private void checkOptions(CommandLine line, Options options) throws IOException {

        String inputFile = null, outputFile = null, debug = null;

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
        if (line.hasOption("debug")){
            debug = line.getOptionValue("debug");
        }

        InputHandler handler = new InputHandler();

        // Ensures we have an input and output file before visualizing island.
        if (!(inputFile == null || outputFile == null)){
            handler.visualizeMesh(inputFile, outputFile, debug);
        }

    }

}
