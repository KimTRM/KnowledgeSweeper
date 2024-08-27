package GameEngine;

import GameEngine.Graphics.AnimatedBackground;
import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.Util.Input.KeyHandler;
import GameEngine.Util.Input.MouseHandler;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable
{
    // ----- SCREEN SETTINGS -----
    public static int screenWidth = 1280;
    public static int screenHeight = 720;

    public int screenWidth2 = screenWidth;
    public int screenHeight2 = screenHeight;

    private Thread thread;
    private BufferedImage image;
    public Graphics2D g;

    // ----- FRAME UPDATE SETTINGS -----
    public static int oldFrameCount;
    public static int oldTickCount;
    public static int tickCount;

    private boolean running = false;

    // ----- IMPORTED CLASSES -----
    protected GameStateManager gsm = new GameStateManager(this);
    private KeyHandler key;
    private final AnimatedBackground background = new AnimatedBackground(this);
    protected MouseHandler mouse = new MouseHandler(gsm);
    protected AssetManager assetManager = new AssetManager(this);

    public GamePanel()
    {
        setPreferredSize(new Dimension(screenWidth2, screenHeight2));
        setBackground(Color.DARK_GRAY);
        setFocusable(true);
        requestFocus();

        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        addKeyListener((KeyListener) key);
    }

    public int getScreenWidth2()
    {
        return screenWidth2;
    }
    public int getScreenHeight2()
    {
        return screenHeight2;
    }

    public void startGameThread()
    {
        thread = new Thread(this, "GameThread");
        thread.start();

        image = new BufferedImage(screenWidth2, screenHeight2, BufferedImage.TYPE_INT_ARGB );
        g = (Graphics2D)image.getGraphics();

//        setFullScreen();
    }

    public void addNotify()
    {
        super.addNotify();

        if (thread == null)
        {
            Initialize();
        }
    }

    public void Initialize()
    {
        running = true;

    }

    public void setFullScreen()
    {
        // <- GETS THE DEVICE SCREEN SIZE ->
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(GameLauncher.window);

        // <- GETS THE FULL SCREEN SIZE (WIDTH & HIEGHT) ->
        screenWidth2 = GameLauncher.window.getWidth();
        screenHeight2 = GameLauncher.window.getHeight();
    }

    @Override
    public void run()
    {

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
            while (((now - lastUpdateTime) > TBU) && (updateCount < MUBR))
            {
                Update();
                lastUpdateTime += TBU;
                updateCount++;
                tickCount++;
                // (^^^^) We use this varible for the soul purpose of displaying it
            }

            if ((now - lastUpdateTime) > TBU) {
                lastUpdateTime = now - TBU;
            }

            Render();
            Draw();

            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime)
            {
                if (frameCount != oldFrameCount)
                {
//                    System.out.println("NEW SECOND " + thisSecond + " Frame Count: " + frameCount);
                    oldFrameCount = frameCount;
                }

                if (tickCount != oldTickCount)
                {
//                    System.out.println("NEW SECOND (T) " + thisSecond + " Tick Count: " + tickCount);
                    oldTickCount = tickCount;
                }
                tickCount = 0;
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while (now - lastRenderTime < TTBR && now - lastUpdateTime < TBU)
            {
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
    public int getButton()
    {
        return button;
    }

    int button;
    public void Update()
    {
        button = mouse.getButton();
        background.Update();
        gsm.Update();
    }

    public void Render()
    {
        if (g != null)
        {
            background.Render(g);
            gsm.Render(g);
        }
    }
    public void Draw()
    {
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

}