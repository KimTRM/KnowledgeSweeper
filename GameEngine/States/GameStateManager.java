package GameEngine.States;

import GameEngine.GamePanel;
import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStates.*;
import GameEngine.Util.End.LossEnd;
import GameEngine.Util.Game.GameBoard;
import GameEngine.Util.Game.QuizManager;
import GameEngine.Util.Leaderboard.Leaderboard;
import GameEngine.Util.Leaderboard.PlayerName;
import GameEngine.Util.Options.Category;
import GameEngine.Util.Options.Level;

import java.awt.*;

public class GameStateManager
{
    // ------ GAME OBJECTS ------
    protected GamePanel gamePanel;
    protected AssetManager assetManager;
    public Category category;
    public Level level;
    public GameBoard gameBoard;
    public QuizManager quizManager;
    public LossEnd lossEnd;
    public Leaderboard leaderboard;
    public PlayerName player;

    // ------ SCENES ------
    public State[] states;
    public static final int STARTING = 0;
    public static final int GAMEOPTIONS = 1;
    public static final int GAME = 2;
    public static final int QUIZ = 3;
    public static final int ENDING = 4;
    public static final int LEADERBOARD = 5;
    public static final int MENU = 6;
    public static final int GMENU = 7;
    public static final int PLAYERNAME = 8;
    public static final int TUTORIALS = 9;

    public String version = "1.5";

    public GameStateManager(GamePanel gamePanel)
    {
        states = new State[10];
        this.gamePanel = gamePanel;

        assetManager = new AssetManager(gamePanel);

        category = new Category(assetManager);
        level = new Level(assetManager);
        gameBoard = new GameBoard(assetManager, this);
        quizManager = new QuizManager(assetManager, gameBoard);
        lossEnd = new LossEnd(assetManager, this);
        player = new PlayerName(assetManager);
        leaderboard = new Leaderboard(assetManager, this);

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
            case ENDING:
                states[ENDING] = new EndingState(this, assetManager);
                break;
            case LEADERBOARD:
                states[LEADERBOARD] = new LeaderboardState(this, assetManager);
                break;
            case MENU:
                states[MENU] = new MenuState(this, assetManager);
                break;
            case GMENU:
                states[GMENU] = new GMenuState(this, assetManager);
                break;
            case PLAYERNAME:
                states[PLAYERNAME] = new PlayerNameState(this, assetManager, player);
                break;
            case TUTORIALS:
                states[TUTORIALS] = new TutorialsState(this, assetManager);
                break;
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
        for (State value : states)
        {
            if (value != null)
            {
                value.update();
            }
        }
    }
    public void Input()
    {
        for (State value : states)
        {
            if (value != null)
            {
                value.input();
            }
        }
    }
    public void Render(Graphics2D g)
    {
        g.setFont(assetManager.Pixel);
        
        for (State value : states)
        {
            if (value != null)
            {
                value.render(g);
            }
        }
    }
}

