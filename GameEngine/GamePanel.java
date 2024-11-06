package GameEngine;

import GameEngine.Graphics.AnimatedBackground;
import GameEngine.Graphics.AssetManager;
import GameEngine.Graphics.ParallaxBackground;
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

    // ----- IMPORTED CLASSES -----
    protected GameStateManager gsm = new GameStateManager(this);
    private KeyHandler key = new KeyHandler();
    protected AssetManager assetManager = new AssetManager(this);
    private final AnimatedBackground background = new AnimatedBackground(this);
    private final ParallaxBackground parallax = new ParallaxBackground();
    protected MouseHandler mouse = new MouseHandler(gsm);


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

    public void setFullScreen()
    {
        // <- GETS THE DEVICE SCREEN SIZE ->
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(GameLauncher.window);

        // <- GETS THE FULL SCREEN SIZE (WIDTH & HEIGHT) ->
        screenWidth2 = GameLauncher.window.getWidth();
        screenHeight2 = GameLauncher.window.getHeight();
    }

    @Override
    public void run()
    {
        int FPS = 60;

        float drawInterval = (float) 1000000000 /FPS; // 0.01666 seconds
        float delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while(thread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                Update();
                Render();
                Draw();
                delta--;
            }

            if(timer >= 1000000000) {
                timer = 0;
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
        parallax.Render(g);

        if (!gsm.isStateActive(GameStateManager.GAME))
        {
            background.Render(g);
        }
        gsm.Render(g);
    }
    public void Draw()
    {
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

}