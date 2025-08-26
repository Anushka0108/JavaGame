package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_seed extends SuperObject{
    public OBJ_seed()
    {
        name="seed";
        try {
            image=ImageIO.read(getClass().getResourceAsStream("/assets/objects/seed.png"));
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        solidArea.x=5;
    }
 
}
