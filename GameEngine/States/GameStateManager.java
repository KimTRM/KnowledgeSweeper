package GameEngine.States;

import GameEngine.GamePanel;
import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStates.*;
import GameEngine.Util.Game.GameBoard;
import GameEngine.Util.Game.QuizManager;

import java.awt.*;

public class GameStateManager
{
    public State[] states;
    protected GamePanel gamePanel;
    protected AssetManager assetManager;
    public GameBoard gameBoard;
    public QuizManager quizManager;

    public static final int STARTING = 0;
    public static final int GAMEOPTIONS = 1;
    public static final int GAME = 2;
    public static final int QUIZ = 3;
    public static final int ENDING = 4;
    public static final int LEADERBOARD = 5;

    public static final int MENU = 6;
    public static final int GMENU = 7;

    public GameStateManager(GamePanel gamePanel)
    {
        states = new State[8];
        this.gamePanel = gamePanel;

        assetManager = new AssetManager(gamePanel);
        gameBoard = new GameBoard(assetManager);
        quizManager = new QuizManager(assetManager);

        addState(STARTING);

        assetManager.Music();
    }

    public boolean isStateActive(int state)
    {


        return states[state] != null;
    }
    public void addState(int state)
    {
        if (states[state] != null)
            return;

        switch (state)
        {
            case STARTING:
                states[STARTING] = new StartingState(this, assetManager);
                break;

            case GAMEOPTIONS:
                states[GAMEOPTIONS] = new GameOptionsState(this, assetManager, gameBoard, quizManager);
                break;

            case GAME:
                states[GAME] = new GameState(this, assetManager, gameBoard);
                break;

            case QUIZ:
                states[QUIZ] = new QuizState(this, assetManager, quizManager);
                break;
//
//            case ENDING:
//                states[ENDING] = new EndingState(this, assetManager);
//                break;
//
//            case LEADERBOARD:
//                states[LEADERBOARD] = new LeaderboardState(this, assetManager);
//                break;

            case MENU:
                states[MENU] = new MenuState(this, assetManager);
                break;

//            case GMENU:
//                states[GMENU] = new GMenuState(this, assetManager);
//                break;
        }
    }
    public void removeState(int state)
    {


        states[state] = null;
    }
    public void AddAndRemoveState(int AddState, int RemoveState)
    {
        addState(AddState);
        removeState(RemoveState);
    }

    public void Update()
    {
        for (int state = 0; state < states.length; state++)
        {
            if (states[state] != null)
            {
                states[state].update();
            }
        }
    }
    public void Input()
    {
        for (int state = 0; state < states.length; state++)
        {
            if (states[state] != null)
            {
                states[state].input();
            }
        }
    }
    public void Render(Graphics2D g)
    {
        g.setFont(assetManager.Pixel);
        for (int state = 0; state < states.length; state++)
        {
            if (states[state] != null)
            {
                states[state].render(g);
            }
        }
    }

}

