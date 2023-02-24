package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class InputHandler {

    private final int VERTEXTHICKNESS = 3;
    private final float POLYSEGTHICKNESS = 0.5f;

    public Mesh createMesh(String[] args){

        Options options = new Options();

        Option help = new Option("-help", "display all possible inputs");
        Option pa = new Option("pa", "define polygon transparency (value from 0 to 255) (default = 255)");

        // logic to get options.
        DotGen generator = new DotGen();
        return generator.generate();
    }

}
