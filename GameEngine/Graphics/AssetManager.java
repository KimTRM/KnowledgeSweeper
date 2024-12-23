package GameEngine.Graphics;

import GameEngine.GamePanel;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class AssetManager {

    public static BufferedImage
    // --- ICON ---
   Bomb, CamhiLogo, Button, BackButton, Start,
   TextBox, Restart, Settings, RightArrow, LeftArrow,

    // --- BACKGROUND ---
    B1,B2,B3,B4, Shade,

    // --- TEXT IMAGE ---
    Title, Defeat, Victory,

    // --- SPRITES ---
    Life, NoLife,
    Grass, RevGrass, NumBlock,
    Select, Select1,
    ActiveBlock, DeactBlock,
    KLTL_Logo, Timer,
    Reset, ResetHover, Home, Exit, HoverExit,

    // --- MEDALS ---
    Medal1, Medal2, Medal3,
    Leaderboard,
    Mouse_Left, Mouse_Right, Star,

    EasyBG1, EasyBG2, EasyBG3, EasyBG4,
    NormalBG1, NormalBG2, NormalBG3, NormalBG4,
    HardBG1, HardBG2, HardBG3, HardBG4,

            BombExploded;

    // --- MUSIC PLAYER ---
    File[] file = new File[10];

    // --- FONT ---
    public Font Pixel;

    public GamePanel gamePanel;
    public MusicPlayer Music = new MusicPlayer();
    public MusicPlayer SE = new MusicPlayer();

    public AssetManager(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;

        getImage();
        getAudio();
        getFont();
    }

    public void getImage()
    {
        try {
            // --- ICON ---
            Bomb = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Bomb.png")));
            CamhiLogo = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/CamhiLogo.png")));
            Button = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Button.png")));
            BackButton = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/BackButton.png")));
            Start = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/StartButton.png")));
            Restart = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/RestartButton.png")));
            TextBox = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/TextBox.png")));
            Settings = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Settings.png")));
            RightArrow = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/RightArrow.png")));
            LeftArrow = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/LeftArrow.png")));

            // --- BACKGROUND ---
            B1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/Background1.png")));
            B2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/Background2.png")));
            B3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/Background3.png")));
            B4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/Background4.png")));
            Shade = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/shade.png")));

            // --- TEXT IMAGE ---
            Title = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/title/Title.png")));
            Defeat = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/title/Defeat.png")));
            Victory = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/title/Victory.png")));

            // --- SPRITES ---
            Life = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Life.png")));
            NoLife = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/NoLife.png")));

            Grass = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Grass.png")));
            RevGrass = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/RevGrass.png")));
            NumBlock = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/NumBlock.png")));

            Select = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Select.png")));
            Select1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Select2.png")));

            ActiveBlock = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/ActiveBlock.png")));
            DeactBlock = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/DeactBlock.png")));

            KLTL_Logo = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/KLTL Logo3.png")));
            Timer = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Timer.png")));

            Reset = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Restart.png")));
            ResetHover = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/RestartHover.png")));
            Home = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Home.png")));

            Medal1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Medal1.png")));
            Medal2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Medal2.png")));
            Medal3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Medal3.png")));

            Leaderboard = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Leaderboard.png")));

            Exit = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Exit.png")));
            HoverExit = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/HoverExit.png")));

            Mouse_Left = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Mouse_LeftClick.png")));
            Mouse_Right = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Mouse_RightClick.png")));

            Star = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/Star.png")));

            EasyBG1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/EasyBG1.png")));
            EasyBG2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/EasyBG2.png")));
            EasyBG3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/EasyBG3.png")));
            EasyBG4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/EasyBG4.png")));

            NormalBG1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/NormalBG1.png")));
            NormalBG2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/NormalBG2.png")));
            NormalBG3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/NormalBG3.png")));
            NormalBG4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/NormalBG4.png")));

            HardBG1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/HardBG1.png")));
            HardBG2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/HardBG2.png")));
            HardBG3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/HardBG3.png")));
            HardBG4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/background/HardBG4.png")));

            BombExploded = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("res/icon/BombExploded.png")));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void getAudio()
    {
        // --- BACKGROUND MUSIC ---
        file[0] = new File("GameEngine/Graphics/res/sounds/astral.wav");

        // --- SFX ---
        file[1] = new File("GameEngine/Graphics/res/sounds/Click.wav");
        file[2] = new File("GameEngine/Graphics/res/sounds/Correct.wav");
        file[3] = new File("GameEngine/Graphics/res/sounds/Wrong.wav");
        file[4] = new File("GameEngine/Graphics/res/sounds/Victory.wav");
        file[5] = new File("GameEngine/Graphics/res/sounds/Defeat.wav");
        file[6] = new File("GameEngine/Graphics/res/sounds/Reveal.wav");
        file[7] = new File("GameEngine/Graphics/res/sounds/Explosion.wav");
    }
    public class MusicPlayer
    {
        Clip clip;
        FloatControl fc;
        public int volumeScale = 3;
        float volume;

        public void getMusic(int i)
        {
            try {

                AudioInputStream ais = AudioSystem.getAudioInputStream(file[i]);
                clip = AudioSystem.getClip();
                clip.open(ais);
                fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            checkVolume();
        }

        // --- MUSIC PLAYER ---
        public void Start ()
        {
            clip.start();
        }
        public void Loop ()
        {

            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        public void Stop ()
        {
            clip.close();
        }

        public void checkVolume()
        {
            switch (volumeScale)
            {
                case 0 : volume = -80f; break;
                case 1 : volume = -20f; break;
                case 2 : volume = -12f; break;
                case 3 : volume = -5f; break;
                case 4 : volume = 1f; break;
                case 5 : volume = 4f; break;
            }
            fc.setValue(volume);
        }
    }

    // ----- MUSIC CONTROLS -----
    public void Music()
    {
        playMusic(0);
    }
    public void playMusic(int i)
    {
        Music.getMusic(i);
        Music.Start();
        Music.Loop();
    }
    public void playSE(int i)
    {
        SE.getMusic(i);
        SE.Start();
    }
    public void stopMusic()
    {
        Music.Stop();
    }

    public void getFont()
    {

        try
        {
            InputStream is = getClass().getResourceAsStream("res/font/x12y16pxMaruMonica.ttf");
            assert is != null;
            Pixel = Font.createFont(Font.TRUETYPE_FONT, is);
        }
        catch (FontFormatException | IOException e)
        {

            e.printStackTrace();
        }

    }

    // ----- VARIABLES TO GET -----
    public int getButton()
    {
        return gamePanel.getButton();
    }
    public int getScreenWidth()
    {
        return gamePanel.getScreenWidth2();
    }
    public int getScreenHeight()
    {
        return gamePanel.getScreenHeight2();
    }

    // ----- QUICK DRAW (TEXTBOX/BUTTON) -----
    public void PrintText (String text, int x, int y, int ln, int size, boolean ForceColor, Graphics2D g)
    {
        if (ForceColor)
        {
            g.setColor(new Color(73, 29, 0));
        }

        g.setFont(g.getFont().deriveFont(Font.BOLD, size));
        for (String line : text.split("\n"))
        {
            g.drawString(line, x, y);
            y += ln; // Adjust the increment as needed for your line spacing
        }
    }
    
    public void PrintTexts (String text, int x, int y, int ln, int size, boolean ForceColor, Graphics2D g)
    {
        if (ForceColor)
        {
            g.setColor(new Color(73, 29, 0));
        }

        g.setFont(g.getFont().deriveFont(Font.BOLD, size));
        for (String line : text.split("\\\\n"))
        {
            g.drawString(line, x, y);
            y += ln; // Adjust the increment as needed for your line spacing
        }
    }

    public void Button (int x, int y, int width, int height, Graphics2D g)
    {
        g.drawImage(Button, x, y, width, height, null);
    }
    public boolean inButtonCollision (int x, int y, int offSetX, int offSetY, int width, int height, boolean Debug, String Name)
    {
        int mx = gamePanel.getMX();
        int my = gamePanel.getMY();

        int x2 = x + offSetX;
        int y2 = y + offSetY;
        int width2 = width - 10;
        int height2 = height - 100;

        boolean collisionShape = mx >= x2 - width2 / 2 && mx < x2 + width2 / 2 && my >= y2 - height2 / 2 && my < y2 + height2 / 2;
        if(collisionShape)
        {
            if(Debug)
            {
                System.out.println("Inside: " + Name);
            }
        }
        return collisionShape;
    }

    public void Start(int x, int y, int width, int height, Graphics2D g)
    {
        g.drawImage(Start, x, y, width, height, null);
    }
    public boolean StartCollision(int x, int y, int width, int height, boolean Debug, String Name)
    {
        int mx = gamePanel.getMX();
        int my = gamePanel.getMY();

        int x2 = x + 100;
        int y2 = y + 100;
        int width2 = width - 20;
        int height2 = height - 100;

        boolean collisionShape = mx >= x2 - width2 / 2 && mx < x2 + width2 / 2 && my >= y2 - height2 / 2 && my < y2 + height2 / 2;
        if(collisionShape)
        {
            if(Debug)
            {
                System.out.println("Inside: " + Name);
            }
        }
        return collisionShape;
    }

    public void TextBox(int x, int y, int width, int height, Graphics2D g)
    {
        g.drawImage(TextBox, x, y, width, height, null);
    }
    public boolean TextBoxCollision(int x, int y, int offSetX, int offSetY, int width, int height, boolean Debug, String Name) 
    {
        int mx = gamePanel.getMX();
        int my = gamePanel.getMY();

        int x2 = x + offSetX;
        int y2 = y + offSetY;
        int width2 = width - 10;
        int height2 = height - 100;

        boolean collisionShape = mx >= x2 - width2 / 2 && mx < x2 + width2 / 2 && my >= y2 - height2 / 2 && my < y2 + height2 / 2;
        if(collisionShape)
        {
            if(Debug)
            {
                System.out.println("Inside: " + Name);
            }
        }
        return collisionShape;
    }

    public void LeftArrow(int x, int y, int width, int height, Graphics2D g)
    {
        g.drawImage(LeftArrow, x, y, width, height,null);
    }
    public void RightArrow(int x, int y, int width, int height, Graphics2D g)
    {
        g.drawImage(RightArrow, x, y, width, height,null);
    }
    public boolean ArrowCollision(int x, int y, int offSetX, int offSetY, int width, int height, boolean Debug) {
        int mx = gamePanel.getMX();
        int my = gamePanel.getMY();

        int x2 = x + offSetX;
        int y2 = y + offSetY;

        boolean collisionShape = mx >= x2 - width / 2 && mx < x2 + width / 2 && my >= y2 - height / 2 && my < y2 + height / 2;
        if(collisionShape)
        {
            if(Debug)
            {
                System.out.println("Inside: Arrow");
            }
        }
        return collisionShape;
    }

    public void drawSubWindow( int x, int y, int width, int height, Graphics2D g)
    {
        Color c = new Color(0,0,0, 161);  // R,G,B, alfa(opacity)
        g.setColor(c);
        g.fillRoundRect(x,y,width,height,35,35);

        c = new Color(255,255,255);
        g.setColor(c);
        g.setStroke(new BasicStroke(5));    // 5 = width of outlines of graphics
        g.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }

    public void drawRoundRect( int x, int y, int width, int height, Graphics2D g)
    {
        Color c = new Color(224, 207, 144, 195);  // R,G,B, alfa(opacity)
        g.setColor(c);
        g.fillRoundRect(x,y,width,height,35,35);

        c = new Color(51,29,15);
        g.setColor(c);
        g.setStroke(new BasicStroke(5));    // 5 = width of outlines of graphics
        g.drawRoundRect(x+5,y+5,width-10,height-10,25,25);
    }
}
