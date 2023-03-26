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
        Option elevation = Option.builder("elevation")
                .argName("elevation type")
                .hasArg()
                .desc("Choose which type of elevation is produced from an island")
                .build();
        Option seed = Option.builder("seed")
                .argName("long seed")
                .hasArg()
                .desc("Enter the seed for what map generation you want")
                .build();
        Option aquifer = Option.builder("aquifer")
                .argName("aquifer")
                .hasArg()
                .desc("Enter the number of aquifers you want generated")
                .build();
        Option lake = Option.builder("lake")
                .argName("aquifer")
                .hasArg()
                .desc("Enter the number of lakes you want generated")
                .build();
        Option river = Option.builder("river")
                .argName("river")
                .hasArg()
                .desc("Enter the number of rivers you want generated")
        Option soil = Option.builder("soil")
                .argName("soil profile")
                .hasArg()
                .desc("Enter the soil profile you want used")
                .build();

        // add all options
        options.addOption(help);
        options.addOption(input);
        options.addOption(output);
        options.addOption(mode);
        options.addOption(heatmap);
        options.addOption(elevation);
        options.addOption(seed);
        options.addOption(aquifer);
        options.addOption(lake);
        options.addOption(river);
        options.addOption(soil);

        return options;
    }

    private void displayHelp(Options options){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("island help", options);
    }

    private void checkOptions(CommandLine line, Options options)  throws IOException {

        String inputFile = null, outputFile = null, mode = "default", heatmap = null, elevation = null, stringSeed = null;
        String aquifer = null, soil = null, lake = null, river = null;
        long seed = -1;

        int aquiferNumber = 0;
        int numLakes = 0;
        int numRivers = 0;
        
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
        if (line.hasOption("elevation")){
            elevation = line.getOptionValue("elevation");
        }
        if (line.hasOption("soil")){
            soil = line.getOptionValue("soil");
        }
        if (line.hasOption("seed")){
            stringSeed = line.getOptionValue("seed");
            try {
                seed = Long.parseLong(stringSeed);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid format for seed, using random seed instead");
            }
        }
        if (line.hasOption("aquifer")){
            aquifer = line.getOptionValue("aquifer");
            try {
                aquiferNumber = Integer.parseInt(aquifer);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid format for the number of aquifers, map now has 0 aquifers");
            }
        }
        if (line.hasOption("lake")){
            lake = line.getOptionValue("lake");
            try {
                numLakes = Integer.parseInt(lake);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid format for the number of lakes, map now has 0 lakes");
            }
        }
        if (line.hasOption("river")){
            river = line.getOptionValue("river");
            try {
                numRivers = Integer.parseInt(river);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid format for the number of lakes, map now has 0 lakes");
            }
        }

        // Ensures we have an input and output file before creating island.
        if (!(inputFile == null || outputFile == null)){
            Mesh aMesh = new MeshFactory().read(inputFile);

            InputHandler handler = new InputHandler(mode);

            // Makes mesh factory and writes to it.
            MeshFactory factory = new MeshFactory();
            if (heatmap != null){
                aMesh = handler.makeMesh(aMesh, heatmap, elevation, seed, aquiferNumber, soil, numLakes, numRivers);
            }
            else{
                aMesh = handler.makeMesh(aMesh, elevation, seed, aquiferNumber, soil, numLakes, numRivers);
            }
            factory.write(aMesh, outputFile);
            
        }

    }

}
