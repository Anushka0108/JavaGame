package package1;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize=16;
    final int scale= 3;
    public final int tileSize =originalTileSize * scale;
    public final int maxScreenCol=16;
    public final int maxScreenRow=12;
    public final int screenWidth= tileSize*maxScreenCol;
    public final int screenHeight= tileSize*maxScreenRow;

   public final int maxWorldCol=50;
   public final int maxWorldRow=50;
   public final int worldWidth=tileSize*maxScreenCol;
   public final int worldHeight=tileSize*maxScreenRow;

    int FPS =60;

    TileManager tileM= new TileManager(this);
    KeyHandler keyH=new KeyHandler();
    
    public collisionchecker cchecker =new collisionchecker(this);
    public AssetSetter aSetter= new AssetSetter(this);
    public UI ui= new UI(this);

    Thread gameThread;
    public Player player=new Player(this,keyH);
    public SuperObject obj[]=new SuperObject[10];


    public GamePanel()
    {
        this.setPreferredSize(new  Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocusInWindow();

         this.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            GamePanel.this.mousePressed(e); // call your method
        }
    });
    }

    public void  setupGame()
    {
    aSetter.setObject();
    }

    public void startGameThread()
    {
        gameThread =new Thread(this);
        gameThread.start();
        
    }
    //sleep method for game time loop
    // public void run()
    // {

    //     double drawInterval=1000000000/FPS;
    //     double nextDrawTime=System.nanoTime()+drawInterval;
    //     while(gameThread!=null)
    //     {
            
    //         update();
    //         repaint();
           
    //         try {
    //              double remainingTime=nextDrawTime - System.nanoTime();
    //              remainingTime=remainingTime/1000000;

    //              if(remainingTime<0)
    //              {
    //                 remainingTime=0;
    //              }
    //              Thread.sleep((long)remainingTime); 
    //              nextDrawTime+=drawInterval;
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }

    //delta method for game time loop
    public void run()
    {
        double drawInterval=1000000000/FPS;
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;

        while(gameThread!=null)
        {
            currentTime=System.nanoTime();
            delta+=(currentTime - lastTime)/drawInterval;
            lastTime=currentTime;
            if(delta>=1)
            {
               update();
            repaint(); 
            delta--;
            }
            
        }
    }
    public void update()
    {
       player.update();

    }

    public void mousePressed(MouseEvent e){
        int mx=e.getX();
        int my=e.getY();

        if(ui.GameFinished){
            if(ui.playAgainButton.contains(mx,my)){
                restartGame();
                player.worldX=tileSize*26;
                player.worldY=tileSize*25;
            }
        }
    }
    public void restartGame()
    {
        ui.GameFinished=false;
        ui.playTime=0;
        player.hasKey=0;
        startGameThread();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;

        tileM.draw(g2);
        for(int i=0; i<obj.length;i++){
            if(obj[i]!=null)
            {
                obj[i].draw(g2,this);
            }
        }
      player.draw(g2);

      ui.draw(g2);
        g2.dispose();
    }
}
