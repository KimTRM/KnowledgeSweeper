package GameEngine.Util.Options;

import GameEngine.Graphics.AssetManager;

import java.awt.*;

public class Category
{
    AssetManager assetManager;
    public String category;

    public Category(AssetManager assetManager)
    {
        this.assetManager = assetManager;
    }

    public void update()
    {
        inSci = assetManager.inButtonCollision(TextBoxSciX, TextBoxSciY,110, 50,  TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Science");
        inHis = assetManager.inButtonCollision(TextBoxHisX, TextBoxHisY,110, 50,  TextBoxSizeX + 30, TextBoxSizeY + 100, false, "History");
        inMath = assetManager.inButtonCollision(TextBoxMathX, TextBoxMathY,110, 50,  TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Math");

    }

    public void input()
    {
        // --- SUBJECTS ---
        if (inSci)
        {
            assetManager.playSE(1);

            Science = true;
            History = false;
            Math = false;
            ConfirmCategory = true;

            category = "Science";
        }
        else if (inHis)
        {
            assetManager.playSE(1);

            History = true;
            Science = false;
            Math = false;
            ConfirmCategory = true;

            category = "History";
        }
        else if (inMath)
        {
            assetManager.playSE(1);

            Math = true;
            History = false;
            Science = false;
            ConfirmCategory = true;

            category = "Math";
        }
    }

    public void render(Graphics2D g)
    {
        // -- TEXT BOX (DIRECTIONS) --
        g.setColor(new Color(0, 0, 0));
        assetManager.PrintText("Choose a Category", 358, 200, 0, 80, false, g);

        g.setColor(new Color(0, 0, 0));
        // -- TEXTBOX (SUBJECT CHOICES) --
        assetManager.TextBox(TextBoxSciX, TextBoxSciY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("Science", TextBoxSciX + 35, TextBoxSciY + 80, 0, 50, false, g);

        assetManager.TextBox(TextBoxHisX, TextBoxHisY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("History", TextBoxHisX + 45, TextBoxHisY + 80, 0, 50, false, g);

        assetManager.TextBox(TextBoxMathX, TextBoxMathY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("Math", TextBoxMathX + 65, TextBoxMathY + 80, 0, 50, false, g);

        // -- SUBJECT HOVER --
        if (inSci || Science)
        {
            g.setColor(new Color(255, 153, 0));
            assetManager.PrintText("Science", TextBoxSciX + 35, TextBoxSciY + 80, 0, 50, false, g);
            g.drawImage(AssetManager.Select1, TextBoxSciX, TextBoxSciY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inHis || History)
        {
            g.setColor(new Color(190, 1, 51));
            assetManager.PrintText("History", TextBoxHisX + 45, TextBoxHisY + 80, 0, 50, false, g);
            g.drawImage(AssetManager.Select1, TextBoxHisX, TextBoxHisY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inMath || Math)
        {
            g.setColor(new Color(42, 150, 2));
            assetManager.PrintText("Math", TextBoxMathX + 65, TextBoxMathY + 80, 0, 50, false, g);
            g.drawImage(AssetManager.Select1, TextBoxMathX, TextBoxMathY, TextBoxSizeX, TextBoxSizeY, null);
        }
    }
    public boolean inSci, inHis, inMath;
    public boolean ConfirmCategory;

    int TextBoxSizeX = 220;
    int TextBoxSizeY = 120;

    // -- CATEGORY BUTTONS --
    int CTextBoxY = 350;

    public boolean Science;
    int TextBoxSciX = 230;
    int TextBoxSciY = CTextBoxY;

    public boolean History;
    int TextBoxHisX = 530;
    int TextBoxHisY = CTextBoxY;

    public boolean Math;
    int TextBoxMathX = 830;
    int TextBoxMathY = CTextBoxY;
}
