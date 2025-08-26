package package1;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp=gp;
    }

    public void setObject(){
        gp.obj[0]= new OBJ_Key();
        gp.obj[0].worldX=31*gp.tileSize;
        gp.obj[0].worldY=13*gp.tileSize;

        gp.obj[1]=new OBJ_Key();
        gp.obj[1].worldX=12*gp.tileSize;
        gp.obj[1].worldY=39*gp.tileSize;

        gp.obj[2]=new OBJ_Key();
        gp.obj[2].worldX=5*gp.tileSize;
        gp.obj[2].worldY=6*gp.tileSize;

        gp.obj[3]=new OBJ_Door();
        gp.obj[3].worldX=6*gp.tileSize;
        gp.obj[3].worldY=8*gp.tileSize;

        gp.obj[4]=new OBJ_Door();
        gp.obj[4].worldX=15*gp.tileSize;
        gp.obj[4].worldY=37*gp.tileSize;

        gp.obj[5]=new OBJ_Chest();
        gp.obj[5].worldX=40*gp.tileSize;
        gp.obj[5].worldY=45*gp.tileSize;

        
    }
}
