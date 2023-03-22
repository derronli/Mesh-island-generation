package ca.mcmaster.cas.se2aa4.a3.island.InputFactories;

import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;

import java.io.IOException;

public class InputParser {

    public void createMesh(String[] args){

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
        Option mode = Option.builder("mode")
                .argName("island type")
                .hasArg()
                .desc("Island shape to use / 'lagoon' for lagoon mode")
                .build();
        Option heatmap = Option.builder("heatmap")
                .argName("heatmap type")
                .hasArg()
                .desc("Choose which type of heatmap is produced from an island")
                .build();

        // add all options
        options.addOption(help);
        options.addOption(input);
        options.addOption(output);
        options.addOption(mode);
        options.addOption(heatmap);

        return options;
    }

    private void displayHelp(Options options){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("island help", options);
    }

    private void checkOptions(CommandLine line, Options options)  throws IOException {

        String inputFile = null, outputFile = null, mode = "default", heatmap = null;

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
        if (line.hasOption("heatmap")){
            heatmap = line.getOptionValue("heatmap");
        }

        // Ensures we have an input and output file before creating island.
        if (!(inputFile == null || outputFile == null)){
            Mesh aMesh = new MeshFactory().read(inputFile);

            InputHandler handler = new InputHandler(mode);

            // Makes mesh factory and writes to it.
            MeshFactory factory = new MeshFactory();
            if (heatmap != null){
                aMesh = handler.makeMesh(aMesh, heatmap);
            }
            else{
                aMesh = handler.makeMesh(aMesh);
            }
            factory.write(aMesh, outputFile);
            
        }

    }

}
