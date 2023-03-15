import ca.mcmaster.cas.se2aa4.a3.island.InputHandler;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        inputHandler.createMesh(args);
    }

}
