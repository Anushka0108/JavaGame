package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import package1.GamePanel;
import package1.KeyHandler;



public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey=0;
    public Player(GamePanel gp,KeyHandler keyH)
    {
        this.gp=gp;
        this.keyH=keyH;

        screenX=gp.screenWidth/2-(gp.tileSize/2);
        screenY=gp.screenHeight/2-(gp.tileSize/2);

        solidArea=new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=30;
        solidArea.height=27;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues()
    {
        worldX=gp.tileSize*26;
        worldY=gp.tileSize*25;
        speed=4;
        direction="down";
    }

    public void getPlayerImage()
    {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_down_2.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_up_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/assets/player/boy_right_2.png"));


            System.out.println("Images loaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load images.");
        }

    }
    public void update()
    {
        if(keyH.upPressed==true||keyH.downPressed==true||keyH.leftPressed==true||keyH.rightPressed==true)
        {
           if(keyH.upPressed==true)
        {
           direction="up";
            
        }
        else if(keyH.downPressed==true)
        {
            direction="down";
            
        }
        else if(keyH.leftPressed==true){
           direction ="left";
           
        }
        else if(keyH.rightPressed==true)
        {
            direction="right";
          
        }

        collisionOn=false;
        gp.cchecker.checkTile(this);
        int objIndex = gp.cchecker.checkObject(this, true);
        pickUpObject(objIndex);

        if(collisionOn==false)
        {
            switch(direction)
            {
                case "up":
                worldY-=speed;
                break;
                case "down":
                worldY+=speed;
                break;
                case "left":
                worldX-=speed;
                break;
                case "right":
                worldX+=speed;
                break;
                
            }
           
        }

        spriteCounter++;
        if(spriteCounter>12)
        {
            if(spriteNum==1)
            {
                spriteNum=2;
            }
            else if(spriteNum==2)
            {
                spriteNum=1;
            }
            spriteCounter=0;
        } 
        }

        
    }

    public void pickUpObject(int i)
    {
        if(i!=999)
        {
           String objectName=gp.obj[i].name;
           switch(objectName)
           {
            case "key":
            hasKey++;
            gp.obj[i]=null;
            gp.ui.showmessage("You got a key");
            break;
            case "door":
            if(hasKey>0)
            {
                gp.obj[i]=null;
                gp.ui.showmessage("You opened the door!");
            }
            else 
            {
                gp.ui.showmessage("You need a key!");
            }
            break;
            case "Chest":
            if(hasKey==3)
            {
                gp.ui.GameFinished=true;
                
            }
            else 
            {
                gp.ui.showmessage("you need 3 keys!!");
            }
            break;
           }

        }
    }
    public void draw(Graphics2D g2)
    {
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image=null;
        switch(direction)
        {
            case "up":
            if(spriteNum==1)
            {
               image=up1; 
            }
            if(spriteNum==2)
            {
                image=up2;
            }
            
            break;
            case "down":
            if(spriteNum==1)
            {
                image=down1;
            }
            if(spriteNum==2)
            {
                image=down2;
            }
            
            break;
            case "left":
            if(spriteNum==1)
            {
                image=left1;
            }
            if(spriteNum==2)
            {
                image=left2;
            }
            
            break;
            case "right":
            if(spriteNum==1)
            {
                image=right1;
            }
            if(spriteNum==2)
            {
                image=right2;
            }
            
            break;
        }
        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }
}
