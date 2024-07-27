package GameEngine.States.GameStates;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.States.State;
import GameEngine.Util.Game.GameBoard;
import GameEngine.Util.Game.QuizManager;

import java.awt.*;

public class GameOptionsState extends State {

    GameBoard gameBoard;
    QuizManager quizManager;

    public GameOptionsState(GameStateManager gameStateManager, AssetManager assetManager, GameBoard gameBoard, QuizManager quizManager)
    {
        super(gameStateManager, assetManager);

        this.gameBoard = gameBoard;
        this.quizManager = quizManager;
    }

    @Override
    public void update()
    {
        inSci = assetManager.inButtonCollision(TextBoxSciX, TextBoxSciY,170, 120,  TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Science");
        inHis = assetManager.inButtonCollision(TextBoxHisX, TextBoxHisY,230, 120,  TextBoxSizeX + 30, TextBoxSizeY + 100, false, "History");
        inMath = assetManager.inButtonCollision(TextBoxMathX, TextBoxMathY,300, 120,  TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Math");

        inEasy = assetManager.inButtonCollision(EasyX, EasyY,170, 150, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Easy");
        inMid = assetManager.inButtonCollision(MediumX, MediumY,230, 150, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Medium");
        inHard = assetManager.inButtonCollision(HardX, HardY,300, 150, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Hard");

        inPlay = assetManager.inButtonCollision(PlayX, PlayY, 250, 300,  PlayButtonW + 30, PlayButtonH + 100, false, "Play");
    }

    @Override
    public void input()
    {
        // --- SUBJECTS ---
        if (inSci == true)
        {
            assetManager.playSE(1);
            Science = true;
            History = false;
            Math = false;
        }
        else if (inHis == true)
        {
            assetManager.playSE(1);
            History = true;
            Science = false;
            Math = false;
        }
        else if (inMath == true)
        {
            assetManager.playSE(1);
            Math = true;
            Science = false;
            History = false;
        }

        // --- DIFFICULTY --
        if (inEasy == true)
        {
            assetManager.playSE(1);
            gameBoard.ChangeDifficulty("Easy", true);
            Easy = true;
            Mid = false;
            Hard = false;
        }
        else if (inMid == true)
        {
            assetManager.playSE(1);
            gameBoard.ChangeDifficulty("Medium", true);
            Easy = false;
            Mid = true;
            Hard = false;
        }
        else if (inHard == true)
        {
            assetManager.playSE(1);
            gameBoard.ChangeDifficulty("Hard", true);
            Easy = false;
            Mid = false;
            Hard = true;
        }

        // --- PLAY ---
        if (inPlay == true && SubjectCheck() == true && DifficultyCheck() == true)
        {
            assetManager.playSE(1);

            gameBoard.ResetAll(gameBoard.TotalBoxes);
            gameStateManager.AddAndRemoveState(GameStateManager.GAME, GameStateManager.GAMEOPTIONS);

        }

    }

    @Override
    public void render(Graphics2D g)
    {
        // -- TEXT BOX (DIRECTIONS) --
        g.setColor(new Color(0, 0, 0));
        assetManager.TextBox(250, 10, 800, 200, g);
        assetManager.PrintText("Choose a Category & Difficulty", 355, 100, 0, 50, false, g);

        // -- SUBJECT CHOOSEN --
        if (Science == true)
        {
            g.setColor(new Color(255, 153, 0));
            g.drawString("Science", 455, 160);
        }
        if (History == true)
        {
            g.setColor(new Color(153, 102, 0));
            g.drawString("History", 455, 160);
        }
        if (Math == true)
        {
            g.setColor(new Color(255, 102, 0));
            g.drawString("Math", 500, 160);
        }
        g.drawString("+", 640, 160);

        // -- DIFFICULTY CHOOSEN --
        if (Easy == true)
        {
            g.setColor(Color.GREEN);
            g.drawString("Easy", 695, 160);
        }
        if (Mid == true)
        {
            g.setColor(Color.BLUE);
            g.drawString("Medium", 695, 160);
        }
        if (Hard == true)
        {
            g.setColor(Color.RED);
            g.drawString("Hard", 695, 160);
        }

        g.setColor(new Color(0, 0, 0));
        // -- TEXTBOX (SUBJECT CHOICES) --
        assetManager.TextBox(TextBoxSciX, TextBoxSciY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("Science", TextBoxSciX + 35, TextBoxSciY + 80, 0, 50, false, g);

        assetManager.TextBox(TextBoxHisX, TextBoxHisY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("History", TextBoxHisX + 45, TextBoxHisY + 80, 0, 50, false, g);

        assetManager.TextBox(TextBoxMathX, TextBoxMathY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("Math", TextBoxMathX + 65, TextBoxMathY + 80, 0, 50, false, g);

        // -- TEXTBOX (DIFFICULTY CHOICES) --
        assetManager.TextBox(EasyX, EasyY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("Easy", EasyX + 65, EasyY + 80, 0, 50, false, g);

        assetManager.TextBox(MediumX, MediumY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("Medium", MediumX + 35, MediumY + 80, 0, 50, false, g);

        assetManager.TextBox(HardX, HardY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("Hard", HardX + 70, HardY + 80, 0, 50, false, g);

        // -- TEXTBOX (PLAY) --
        assetManager.Button(PlayX, PlayY, PlayButtonW, PlayButtonH, g);
        assetManager.PrintText("Play", PlayX + 58, PlayY + 140, 0, 70, true, g);

        // -- PLAY HOVER --
        if(inPlay == true && SubjectCheck() == true && DifficultyCheck() == true)
        {
            assetManager.Button(PlayX - 10, PlayY - 15, PlayButtonW + 20, PlayButtonH + 30, g);
            assetManager.PrintText("Play", PlayX + 50, PlayY + 145, 0, 80, true, g);
        }

        // -- SUBJECT HOVER --
        if (inSci == true || Science == true)
        {
            g.drawImage(assetManager.Select1, TextBoxSciX, TextBoxSciY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inHis == true || History == true)
        {
            g.drawImage(assetManager.Select1, TextBoxHisX, TextBoxHisY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inMath == true || Math == true)
        {
            g.drawImage(assetManager.Select1, TextBoxMathX, TextBoxMathY, TextBoxSizeX, TextBoxSizeY, null);
        }

        // -- DIFFICULTY HOVER --
        if (inEasy == true || Easy == true)
        {
            g.drawImage(assetManager.Select1, EasyX, EasyY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inMid == true || Mid == true)
        {
            g.drawImage(assetManager.Select1, MediumX, MediumY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inHard == true || Hard == true)
        {
            g.drawImage(assetManager.Select1, HardX, HardY, TextBoxSizeX, TextBoxSizeY, null);
        }

    }


    // -- SUBJECTS & DIFFICULTY CHECKER --
    public boolean SubjectCheck() {
        if(Science == true || History == true || Math == true)
        {
            return true;
        }
        return false;
    }
    public boolean DifficultyCheck() {
        if(Easy == true || Mid == true || Hard == true)
        {
            return true;
        }
        return false;
    }

    // -- CATEGORY BUTTONS --
    int CTextBoxY = 250;

    public boolean Science;
    int TextBoxSciX = 230;
    int TextBoxSciY = CTextBoxY;

    public boolean History;
    int TextBoxHisX = 530;
    int TextBoxHisY = CTextBoxY;

    public boolean Math;
    int TextBoxMathX = 830;
    int TextBoxMathY = CTextBoxY;


    // -- DIFFICULTY BUTTONS --
    int DTextBoxY = 420;

    public boolean Easy;
    int EasyX = 230;
    int EasyY = DTextBoxY;

    public boolean Mid;
    int MediumX = 530;
    int MediumY = DTextBoxY;

    public boolean Hard;
    int HardX = 830;
    int HardY = DTextBoxY;

    public boolean Play;
    int PlayX = 528;
    int PlayY = 520;

    int PlayButtonW = 220;
    int PlayButtonH = 220;

    int TextBoxSizeX = 220;
    int TextBoxSizeY = 120;

    boolean inSci, inHis, inMath, inEasy, inMid, inHard, inPlay;
}
