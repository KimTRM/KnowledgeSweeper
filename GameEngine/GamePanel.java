package GameEngine;

import GameEngine.States.GameStateManager;
import GameEngine.Util.KeyHandler;
import GameEngine.Util.MouseHandler;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable
{
    public static int Width;
    public static int Height;

    public static int oldFrameCount;
    public static int oldTickCount;
    public static int tickCount;

    private Thread thread;
    private boolean running = false;

    private GameStateManager gsm;

    private MouseHandler mouse;
    private KeyHandler key;

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

       gsm = new GameStateManager();

       mouse = new MouseHandler();
       key = new KeyHandler();
   }

    @Override
    public void run()
    {
        Initialize();

        final double GAME_HERTZ = 60.0;
        final double TBU = 1000000000 / GAME_HERTZ; // Time Before Update

        final int MUBR = 3; // Must Update before render

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TTBR = 1000000000 / TARGET_FPS; // Total time before render

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        oldFrameCount = 0;

        tickCount = 0;
        oldTickCount = 0;

        while (running) {

            double now = System.nanoTime();
            int updateCount = 0;
            while (((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
                Update(now);
                Inputs(mouse, key);
                lastUpdateTime += TBU;
                updateCount++;
                tickCount++;
                // (^^^^) We use this varible for the soul purpose of displaying it
            }

            if ((now - lastUpdateTime) > TBU) {
                lastUpdateTime = now - TBU;
            }

            Inputs(mouse, key);
            Render();
            Draw();

            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                if (frameCount != oldFrameCount) {
                    System.out.println("NEW SECOND " + thisSecond + " Frame Count: " + frameCount);
                    oldFrameCount = frameCount;
                }

                if (tickCount != oldTickCount) {
                    System.out.println("NEW SECOND (T) " + thisSecond + " Tick Count: " + tickCount);
                    oldTickCount = tickCount;
                }
                tickCount = 0;
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while (now - lastRenderTime < TTBR && now - lastUpdateTime < TBU) {
                Thread.yield();

                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    System.out.println("ERROR: yielding thread");
                }

                now = System.nanoTime();
            }

        }
    }

    public void Update(double delta)
    {
        gsm.Update(delta);
    }

    public void Inputs(MouseHandler mouse, KeyHandler key)
    {
        gsm.Input(mouse, key);
    }

    public void Render()
    {
        if (g != null) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, Width, Height);

            gsm.Render(g);
        }
    }
    public void Draw()
    {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

}