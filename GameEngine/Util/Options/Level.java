package GameEngine.Util.Options;

import GameEngine.Graphics.AssetManager;

import java.awt.*;

public class Level
{
    AssetManager assetManager;
    public String Level;

    public Level(AssetManager assetManager)
    {
        this.assetManager = assetManager;
    }

    public void update()
    {
        inEasy = assetManager.inButtonCollision(EasyX, EasyY,100, 50, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Easy");
        inMid = assetManager.inButtonCollision(MediumX, MediumY,100, 50, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Medium");
        inHard = assetManager.inButtonCollision(HardX, HardY,100, 50, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Hard");

    }

    public void input()
    {
        // --- DIFFICULTY --
        if (inEasy)
        {
            assetManager.playSE(1);

            Easy = true;
            Mid = false;
            Hard = false;

            Level = "Easy";
        }
        else if (inMid)
        {
            assetManager.playSE(1);

            Easy = false;
            Mid = true;
            Hard = false;

            Level = "Normal";
        }
        else if (inHard)
        {
            assetManager.playSE(1);

            Easy = false;
            Mid = false;
            Hard = true;

            Level = "Hard";
        }
    }

    public void render(Graphics2D g)
    {
        // -- TEXT BOX (DIRECTIONS) --
        g.setColor(new Color(0, 0, 0));
        assetManager.PrintText("Choose a Difficulty", 358, 200, 0, 80, false, g);

        g.setColor(new Color(0, 0, 0));
        // -- TEXTBOX (DIFFICULTY CHOICES) --
        assetManager.TextBox(EasyX, EasyY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("Easy", EasyX + 65, EasyY + 80, 0, 50, false, g);

        assetManager.TextBox(MediumX, MediumY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("Normal", MediumX + 35, MediumY + 80, 0, 50, false, g);

        assetManager.TextBox(HardX, HardY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("Hard", HardX + 70, HardY + 80, 0, 50, false, g);

        // -- DIFFICULTY HOVER --
        if (inEasy || Easy)
        {
            assetManager.TextBox(EasyX, EasyY - 50, TextBoxSizeX, TextBoxSizeY + 200, g);
            g.setColor(new Color(95, 171, 73));
            assetManager.PrintText("Easy", EasyX + 65, EasyY + 80, 0, 50, false, g);
            assetManager.PrintText("6x6", EasyX + 74, EasyY + 210, 0, 50, false, g);
//            g.drawImage(AssetManager.Select1, EasyX, EasyY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inMid || Mid)
        {
            assetManager.TextBox(MediumX, MediumY - 50, TextBoxSizeX, TextBoxSizeY + 200, g);
            g.setColor(new Color(199, 105, 0));
            assetManager.PrintText("Normal", MediumX + 35, MediumY + 80, 0, 50, false, g);
            assetManager.PrintText("10x6", MediumX + 70, MediumY + 210, 0, 50, false, g);
//            g.drawImage(AssetManager.Select1, MediumX, MediumY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inHard || Hard)
        {
            assetManager.TextBox(HardX, HardY - 50, TextBoxSizeX, TextBoxSizeY + 200, g);
            g.setColor(new Color(255, 0, 0));
            assetManager.PrintText("Hard", HardX + 70, HardY + 80, 0, 50, false, g);
            assetManager.PrintText("14x6", HardX + 70, HardY + 210, 0, 50, false, g);
//            g.drawImage(AssetManager.Select1, HardX, HardY - 50, TextBoxSizeX, TextBoxSizeY + 200, null);
        }
    }
    public boolean inEasy, inMid, inHard;

    int TextBoxSizeX = 220;
    int TextBoxSizeY = 120;

    // -- DIFFICULTY BUTTONS --
    int DTextBoxY = 350;

    public boolean Easy;
    int EasyX = 230;
    int EasyY = DTextBoxY;

    public boolean Mid;
    int MediumX = 530;
    int MediumY = DTextBoxY;

    public boolean Hard;
    int HardX = 830;
    int HardY = DTextBoxY;

}
