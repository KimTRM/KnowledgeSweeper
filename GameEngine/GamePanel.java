package GameEngine;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable
{
    public static int Width;
    public static int Height;

    private Thread thread;
    private boolean running = false;

    private BufferedImage image;
    private Graphics2D g;


   public GamePanel(int Width, int Height)
   {
       GamePanel.Width = Width;
       GamePanel.Height = Height;
        setPreferredSize(new Dimension(Width, Height));
        setFocusable(true);
        requestFocus();
   }

   public void addNotify()
   {
       super.addNotify();

       if (thread == null)
       {
           thread = new Thread(this, "GameThread");
           thread.start();
       }
   }

   public void Initialize()
   {
       running = true;

       image = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
       g = (Graphics2D) image.getGraphics();

   }

    @Override
    public void run()
    {
        Initialize();

        int FPS = 60;
        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (running)
        {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1)
            {
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000)
            {
                 System.out.println("FPS: " + drawCount);
                timer = 0;
                drawCount = 0;
            }

            Update();
            Inputs();
            Render();
            Draw();
        }
    }

    public void Update()
    {

    }

    public void Inputs()
    {

    }

    public void Render()
    {
        if (g != null) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, Width, Height);
        }
    }
    public void Draw()
    {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

}