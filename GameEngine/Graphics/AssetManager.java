package GameEngine.Graphics;

import GameEngine.GamePanel;
import GameEngine.Util.MouseHandler;

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
            Grass, RevGrass,
            Select, Select1,
            ActiveBlock, DeactBlock;

    // --- MUSIC PLAYER ---
    File[] file = new File[10];

    // --- FONT ---
    public Font Pixel;

    private GamePanel gamePanel;
    protected MusicPlayer Music = new MusicPlayer();
    protected MusicPlayer SE = new MusicPlayer();

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
            Bomb = ImageIO.read(getClass().getResourceAsStream("res/icon/Bomb.png"));
            CamhiLogo = ImageIO.read(getClass().getResourceAsStream("res/icon/CamhiLogo.png"));
            Button = ImageIO.read(getClass().getResourceAsStream("res/icon/Button.png"));
            BackButton = ImageIO.read(getClass().getResourceAsStream("res/icon/BackButton.png"));
            Start = ImageIO.read(getClass().getResourceAsStream("res/icon/StartButton.png"));
            Restart = ImageIO.read(getClass().getResourceAsStream("res/icon/RestartButton.png"));
            TextBox = ImageIO.read(getClass().getResourceAsStream("res/icon/TextBox.png"));
            Settings = ImageIO.read(getClass().getResourceAsStream("res/icon/Settings.png"));
            RightArrow = ImageIO.read(getClass().getResourceAsStream("res/icon/RightArrow.png"));
            LeftArrow = ImageIO.read(getClass().getResourceAsStream("res/icon/LeftArrow.png"));

            // --- BACKGROUND ---
            B1 = ImageIO.read(getClass().getResourceAsStream("res/background/Background1.png"));
            B2 = ImageIO.read(getClass().getResourceAsStream("res/background/Background2.png"));
            B3 = ImageIO.read(getClass().getResourceAsStream("res/background/Background3.png"));
            B4 = ImageIO.read(getClass().getResourceAsStream("res/background/Background4.png"));
            Shade = ImageIO.read(getClass().getResourceAsStream("res/background/shade.png"));

            // --- TEXT IMAGE ---
            Title = ImageIO.read(getClass().getResourceAsStream("res/title/Title.png"));
            Defeat = ImageIO.read(getClass().getResourceAsStream("res/title/Defeat.png"));
            Victory = ImageIO.read(getClass().getResourceAsStream("res/title/Victory.png"));

            // --- SPRITES ---
            Life = ImageIO.read(getClass().getResourceAsStream("res/icon/Life.png"));
            NoLife = ImageIO.read(getClass().getResourceAsStream("res/icon/NoLife.png"));

            Grass = ImageIO.read(getClass().getResourceAsStream("res/icon/Grass.png"));
            RevGrass = ImageIO.read(getClass().getResourceAsStream("res/icon/RevGrass.png"));

            Select = ImageIO.read(getClass().getResourceAsStream("res/icon/Select.png"));
            Select1 = ImageIO.read(getClass().getResourceAsStream("res/icon/Select2.png"));

            ActiveBlock = ImageIO.read(getClass().getResourceAsStream("res/icon/ActiveBlock.png"));
            DeactBlock = ImageIO.read(getClass().getResourceAsStream("res/icon/DeactBlock.png"));

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
    }
    public class MusicPlayer
    {
        Clip clip;
        FloatControl fc;
        int volumeScale = 3;
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
    public void getFont()
    {

        try
        {
            InputStream is = getClass().getResourceAsStream("res/font/x12y16pxMaruMonica.ttf");
            Pixel = Font.createFont(Font.TRUETYPE_FONT, is);
        }
        catch (FontFormatException | IOException e)
        {

            e.printStackTrace();
        }

    }

    // ----- MUSIC PLAYER -----
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

    // ----- QUICK DRAW -----
    public void Button(int x, int y, int width, int height, Graphics2D g)
    {
        g.drawImage(Button, x, y, width, height, null);

        int x2 = x + 110;
        int y2 = y + 115;
        int width2 = width - 50;
        int height2 = height - 138;
        g.drawRect(x2 - width2/2, y2 - height2 /2, width2, height2);
    }

    public boolean inButtonCollision(int x, int y, int width, int height)
    {
        int mx = gamePanel.getMX();
        int my = gamePanel.getMY();

        int x2 = x + 110;
        int y2 = y + 115;
        int width2 = width - 50;
        int height2 = height - 138;

        if (mx >= x2 - width2 / 2 && mx < x2 + width2 / 2 && my >= y2 - height2 / 2 && my < y2 + height2 / 2)
        {
            System.out.println("Collision");
            return true;
        }
        return false;
    }

    public void Start(int x, int y, int width, int height, Graphics2D g)
    {
        g.drawImage(Start, x, y, width, height, null);
    }

    public void TextBox(int x, int y, int width, int height, Graphics2D g)
    {
        g.drawImage(TextBox, x, y, width, height, null);
    }



}
