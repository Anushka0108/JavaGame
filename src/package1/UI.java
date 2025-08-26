package package1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import object.OBJ_Key;

public class UI {
    GamePanel gp;
    Font arial_40,arial_80;
    BufferedImage keyImage;
    public boolean messageOn= false;
    public String message="";
    int messageCounter;
    public boolean GameFinished=false;

    double playTime;
    DecimalFormat dformat= new DecimalFormat("#0.00");
    
    public Rectangle playAgainButton= new Rectangle();
    public boolean playAgainHovered=false;

    public UI(GamePanel gp)
    {
        this.gp= gp;
        arial_40=new Font("Arial",Font.PLAIN,40);
        arial_80=new Font("Arial",Font.BOLD,80);
        OBJ_Key key= new OBJ_Key();
        keyImage= key.image;

        int buttonWidth=250;
        int buttonHeight=60;
        int x =gp.screenWidth/2-buttonWidth/2;
        int y=gp.screenHeight/2+gp.tileSize*4;
        playAgainButton.setBounds(x,y,buttonWidth,buttonHeight);

    }
    public void showmessage(String text)
    {
        message =text;
        messageOn= true;
    }
    public void draw (Graphics2D g2)
    {
        if(GameFinished==true)
        {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            
            String text;
            int textlength;
            int x;
            int y;

            text ="you found the treasure";
            textlength=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
             x= gp.screenWidth/2 - textlength/2;
             y= gp.screenHeight/2 - (gp.tileSize*3);
             g2.drawString(text,x,y);

             text ="Your Time is:"+dformat.format(playTime)+"!";
            textlength=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
             x= gp.screenWidth/2 - textlength/2;
             y= gp.screenHeight/2 - (gp.tileSize*4);
             g2.drawString(text,x,y);

             g2.setFont(arial_80);
             g2.setColor(Color.yellow);

             text ="Congratulations!";
            textlength=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
             x= gp.screenWidth/2 - textlength/2;
             y= gp.screenHeight/2 + (gp.tileSize*2);
             g2.drawString(text,x,y);

             g2.setFont(arial_40);
            g2.setColor(playAgainHovered ? Color.orange : Color.green);
            g2.fill(playAgainButton);
            g2.setColor(Color.black);
            g2.draw(playAgainButton);

            g2.setColor(Color.white);
            String buttonText = "Play Again";
            int btnTextLength = (int) g2.getFontMetrics().getStringBounds(buttonText, g2).getWidth();
            int btnX = playAgainButton.x + (playAgainButton.width - btnTextLength) / 2;
            int btnY = playAgainButton.y + (playAgainButton.height / 2) + 12;
            g2.drawString(buttonText, btnX, btnY);
             
             gp.gameThread=null;
        }
        else{
            g2.setFont(arial_40);
            g2.setColor(Color.white);
        g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
        g2.drawString("x "+gp.player.hasKey, 74, 50);

        playTime+=(double)1/60;
        g2.drawString("Time:"+dformat.format(playTime),gp.tileSize*11,65);

        if( messageOn==true)
        {
           g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message,gp.tileSize/2,gp.tileSize*5);

            messageCounter++;

            if(messageCounter>120)
            {
                messageCounter=0;
                messageOn=false;
            } 
        }
       
        }
    }

}
