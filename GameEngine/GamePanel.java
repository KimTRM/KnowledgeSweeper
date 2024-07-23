package GameEngine;

import GameEngine.Graphics.AnimatedBackground;
import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.Util.KeyHandler;
import GameEngine.Util.MouseHandler;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable
{
    public static int Width = 1280;
    public static int Height = 720;

    public static int oldFrameCount;
    public static int oldTickCount;
    public static int tickCount;

    private Thread thread;
    private boolean running = false;

    private GameStateManager gsm;

    protected MouseHandler mouse = new MouseHandler();
    private KeyHandler key;
    private final AnimatedBackground background = new AnimatedBackground(this);

    private BufferedImage image;
    private Graphics2D g;

   public GamePanel()
   {
        setPreferredSize(new Dimension(Width, Height));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);
        requestFocus();

        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        addKeyListener((KeyListener) key);
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

       AssetManager assetManager = new AssetManager(this);
   }

    @Override
    public void run()
    {
        Initialize();

        final double GAME_HERTZ = 64.0;
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

        while (running)
        {
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
            Draw();
            Render();

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

    public int getMX()
    {
        return mouse.getX();
    }

    public int getMY()
    {
        return mouse.getY();
    }

    public void Update(double delta)
    {
//        System.out.println(getMX() + " " + getMY());
        background.Update();
        gsm.Update(delta);
    }

    public void Inputs(MouseHandler mouse, KeyHandler key)
    {
        gsm.Input(mouse, key);
    }

    public void Render()
    {
        if (g != null) {
            background.Render(g);
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