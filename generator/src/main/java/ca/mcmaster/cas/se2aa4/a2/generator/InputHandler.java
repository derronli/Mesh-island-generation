package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.*;

public class InputHandler {

    private final int VERTEXTHICKNESS = 3;
    private final float POLYSEGTHICKNESS = 0.5f;
    private final int alpha = 255;
    private final int numPolygons = 30;
    private final int relaxation = 0;

    public Mesh createMesh(String[] args){

        Options options = createOptions();

        // If they ask for help, displays options, and exits without generating a mesh.
        if (args.length > 0 && (args[0].equals("-h") || args[0].equals("--help"))){
            displayHelp(options);
            return null;
        }

        // Parses options with arguments.
        CommandLineParser parser = new DefaultParser();
        try {
            // parse the command line arguments
            CommandLine line = parser.parse(options, args);
            return checkOptions(line);
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

    private Mesh checkOptions(CommandLine line){
        // Setting parameters for dotgen to default values.
        int polyTrans = alpha;
        int segTrans = alpha;
        int vertexTrans = alpha;
        float polyThick = POLYSEGTHICKNESS;
        float segThick = POLYSEGTHICKNESS;
        int vertexThick = VERTEXTHICKNESS;
        int numPoly = numPolygons;
        int relax = relaxation;

        // logic to see which options were used and set variables accordingly.
        if(line.hasOption("pa")) {
            // initialise the member variable
            polyTrans = Integer.parseInt(line.getOptionValue("pa"));
        }
        if(line.hasOption("sa")) {
            // initialise the member variable
            segTrans = Integer.parseInt(line.getOptionValue("sa"));
        }
        if(line.hasOption("va")) {
            // initialise the member variable
            vertexTrans = Integer.parseInt(line.getOptionValue("va"));
        }
        if(line.hasOption("pt")) {
            // initialise the member variable
            polyThick = Integer.parseInt(line.getOptionValue("pt"));
        }
        if(line.hasOption("st")) {
            // initialise the member variable
            segThick = Integer.parseInt(line.getOptionValue("st"));
        }
        if(line.hasOption("vt")) {
            // initialise the member variable
            vertexThick = Integer.parseInt(line.getOptionValue("vt"));
        }
        if(line.hasOption("np")) {
            // initialise the member variable
            numPoly = Integer.parseInt(line.getOptionValue("np"));
        }
        if(line.hasOption("rl")) {
            // initialise the member variable
            relax = Integer.parseInt(line.getOptionValue("rl"));
        }

        DotGen generator = new DotGen();

        // If using an irregular mesh, passes in more arguments.
        if(line.hasOption("ir")){
            return generator.generate(polyTrans, segTrans, vertexTrans, polyThick, segThick, vertexThick, numPoly, relax);
        }

        // If using grid mesh, passes in regular arguments.
        return generator.generate(polyTrans, segTrans, vertexTrans, polyThick, segThick, vertexThick);


    }

}
