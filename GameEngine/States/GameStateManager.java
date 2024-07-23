package GameEngine.States;

import GameEngine.GamePanel;
import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStates.*;
import GameEngine.Util.KeyHandler;
import GameEngine.Util.MouseHandler;

import java.awt.*;

public class GameStateManager
{
    private State[] states;
    private AssetManager assetManager;

    public static final int STARTING = 0;
    public static final int GAMEOPTIONS = 1;
    public static final int GAME = 2;
    public static final int QUIZ = 3;
    public static final int ENDING = 4;
    public static final int LEADERBOARD = 5;

    public GameStateManager()
    {
        states = new State[6];
        assetManager = new AssetManager(new GamePanel());
        addState(STARTING);

        assetManager.Music();
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
                states[GAMEOPTIONS] = new GameOptionsState(this, assetManager);
                break;
            case GAME:
                states[GAME] = new GameState(this, assetManager);
                break;
            case QUIZ:
                states[QUIZ] = new QuizState(this, assetManager);
                break;
            case ENDING:
                states[ENDING] = new EndingState(this, assetManager);
                break;
            case LEADERBOARD:
                states[LEADERBOARD] = new LeaderboardState(this, assetManager);
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

    public boolean isStateActive(int state)
    {
        return states[state] != null;
    }

    public void Update(double time)
    {
        for (int state = 0; state < states.length; state++)
        {
            if (states[state] != null)
            {
                states[state].update(time);
            }
        }
    }

    public void Input(MouseHandler mouse, KeyHandler key)
    {
        for (int state = 0; state < states.length; state++)
        {
            if (states[state] != null)
            {
                states[state].input(mouse, key);
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

