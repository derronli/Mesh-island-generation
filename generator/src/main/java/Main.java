import ca.mcmaster.cas.se2aa4.a2.generator.InputHandler;
import ca.mcmaster.cas.se2aa4.a2.io.MeshFactory;
import ca.mcmaster.cas.se2aa4.a2.io.Structs.Mesh;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        InputHandler inputHandler = new InputHandler();
        Mesh myMesh = inputHandler.createMesh(args);
        if (myMesh == null){
            return;
        }
        MeshFactory factory = new MeshFactory();
        factory.write(myMesh, args[0]);
    }

}
