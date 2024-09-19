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
        inBack = assetManager.inButtonCollision(BackX, BackY, 100, 100,  BackButtonW - 20, BackButtonH - 20, false, "Back");
        inPlay = assetManager.inButtonCollision(PlayX, PlayY, 140, 140,  PlayButtonW + 30, PlayButtonH + 100, false, "Play");

        if (!gameStateManager.category.ConfirmCategory)
        {
            gameStateManager.category.update();
        }
        else
        {
            gameStateManager.level.update();
        }

        gameBoard.isActive = !gameStateManager.isStateActive(GameStateManager.QUIZ)
                && !gameStateManager.isStateActive(GameStateManager.ENDING)
                && !gameStateManager.isStateActive(GameStateManager.GAMEOPTIONS)
                && !gameStateManager.isStateActive(GameStateManager.STARTING);
    }

    @Override
    public void input()
    {
        if (!gameStateManager.category.ConfirmCategory)
        {
            gameStateManager.category.input();
        }
        else
        {
            gameStateManager.level.input();
        }

        // --- SUBJECTS ---
        if (gameStateManager.category.inSci)
        {
            quizManager.Science = true;
            quizManager.History = false;
            quizManager.Math = false;

            quizManager.clear();
            quizManager.ChangeSubject("Science");
        }
        else if (gameStateManager.category.inHis)
        {
            quizManager.History = true;
            quizManager.Science = false;
            quizManager.Math = false;

            quizManager.clear();
            quizManager.ChangeSubject("History");
        }
        else if (gameStateManager.category.inMath)
        {
            assetManager.playSE(1);

            quizManager.Math = true;
            quizManager.Science = false;
            quizManager.History = false;

            quizManager.clear();
            quizManager.ChangeSubject("Math");
        }

        // --- DIFFICULTY --
        if (gameStateManager.level.inEasy)
        {
            gameBoard.Easy = true;
            gameBoard.Medium = false;
            gameBoard.Hard = false;

            gameBoard.ChangeDifficulty("Easy", true);
        }
        else if (gameStateManager.level.inMid)
        {
            gameBoard.Easy = false;
            gameBoard.Medium = true;
            gameBoard.Hard = false;

            gameBoard.ChangeDifficulty("Medium", true);
        }
        else if (gameStateManager.level.inHard)
        {
            gameBoard.Easy = false;
            gameBoard.Medium = false;
            gameBoard.Hard = true;

            gameBoard.ChangeDifficulty("Hard", true);
        }

        if (inBack)
        {
            assetManager.playSE(1);
            gameStateManager.category.ConfirmCategory = false;
        }

        // --- PLAY ---
        if (inPlay && SubjectCheck() && DifficultyCheck())
        {
            assetManager.playSE(1);

            gameBoard.ResetAll(gameBoard.TotalBoxes);
            gameStateManager.AddAndRemoveState(GameStateManager.GAME, GameStateManager.GAMEOPTIONS);
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        assetManager.TextBox(250, 100, 800, 200, g);

        assetManager.drawSubWindow(1060, 10, 200, 50, g);
        assetManager.PrintText("How to Play?", 1090, 45, 0, 30, false, g);

        // -- SUBJECT CHOOSEN --
        if (!gameStateManager.category.ConfirmCategory)
        {
            gameStateManager.category.render(g);
        }
        else
        {
            gameStateManager.level.render(g);
        }

        // -- SUBJECT CHOOSEN --
        if (gameStateManager.category.Science)
        {
            g.setColor(new Color(255, 153, 0));
            g.setFont(g.getFont().deriveFont(Font.BOLD, 50));
            g.drawString("Science", 455, 260);
        }
        if (gameStateManager.category.History)
        {
            g.setColor(new Color(190, 1, 51));
            g.setFont(g.getFont().deriveFont(Font.BOLD, 50));
            g.drawString("History", 455, 260);
        }
        if (gameStateManager.category.Math)
        {
            g.setColor(new Color(42, 150, 2));
            g.setFont(g.getFont().deriveFont(Font.BOLD, 50));
            g.drawString("Math", 500, 260);
        }

        g.setColor(new Color(0, 0, 0));
        g.drawString("+", 640, 260);

        // -- DIFFICULTY CHOOSEN --
        if (gameStateManager.level.Easy)
        {
            g.setColor(new Color(95, 171, 73));
            g.setFont(g.getFont().deriveFont(Font.BOLD, 50));
            g.drawString("Easy", 695, 260);
        }
        if (gameStateManager.level.Mid)
        {
            g.setColor(new Color(199, 105, 0));
            g.setFont(g.getFont().deriveFont(Font.BOLD, 50));
            g.drawString("Normal", 695, 260);
        }
        if (gameStateManager.level.Hard)
        {
            g.setColor(new Color(255, 0, 0));
            g.setFont(g.getFont().deriveFont(Font.BOLD, 50));
            g.drawString("Hard", 695, 260);
        }

        if (gameStateManager.category.ConfirmCategory)
        {
            // -- BACK BUTTON --
            assetManager.Button(BackX, BackY, BackButtonW, BackButtonH, g);
            assetManager.PrintText("Back", BackX + 48, BackY + 110, 0, 50, true, g);

            // -- BACK HOVER --
            if (inBack) {
                assetManager.Button(BackX - 10, BackY - 15, BackButtonW + 20, BackButtonH + 30, g);
                assetManager.PrintText("Back", BackX + 35, BackY + 115, 0, 60, true, g);
            }
        }

        // -- PLAY BUTTON --
        if (SubjectCheck() && DifficultyCheck())
        {
            // -- TEXTBOX (PLAY) --
            assetManager.Button(PlayX, PlayY, PlayButtonW, PlayButtonH, g);
            assetManager.PrintText("Play", PlayX + 58, PlayY + 140, 0, 70, true, g);

            // -- PLAY HOVER --
            if (inPlay && SubjectCheck() && DifficultyCheck())
            {
                assetManager.Button(PlayX - 10, PlayY - 15, PlayButtonW + 20, PlayButtonH + 30, g);
                assetManager.PrintText("Play", PlayX + 50, PlayY + 145, 0, 80, true, g);
            }
        }
        g.drawImage(assetManager.KLTL_Logo, 1120, 570, 200, 200, null);

        g.setColor(new Color(0xB9B9B9));
        assetManager.PrintText(gameStateManager.version, 20, 700, 0, 20, false, g);
    }

    // -- SUBJECTS & DIFFICULTY CHECKER --
    public boolean SubjectCheck() {
        return gameStateManager.category.Science || gameStateManager.category.History || gameStateManager.category.Math;
    }
    public boolean DifficultyCheck() {
        return gameStateManager.level.Easy || gameStateManager.level.Mid || gameStateManager.level.Hard;
    }

    // -- BUTTONS --
    boolean inBack;

    int BackX = 10;
    int BackY = -20;
    int BackButtonW = 180;
    int BackButtonH = 180;

    boolean inPlay;

    int PlayX = 528;
    int PlayY = 530;

    int PlayButtonW = 220;
    int PlayButtonH = 220;
}
