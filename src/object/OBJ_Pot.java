package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Pot extends SuperObject {
    public OBJ_Pot()
    {
        name="Pot";
        try {
        image=ImageIO.read(getClass().getResourceAsStream("/assets/objects/pot.png"));

    } catch (IOException e) {
        e.printStackTrace();
    }
    collision=true;
    solidArea.x=5;
    }
}
