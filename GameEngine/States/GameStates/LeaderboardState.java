package GameEngine.States.GameStates;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.States.State;

import java.awt.*;

public class LeaderboardState extends State
{
    public LeaderboardState(GameStateManager gameStateManager, AssetManager assetManager)
    {
        super(gameStateManager, assetManager);
    }

    @Override
    public void update()
    {
        gameStateManager.leaderboard.update();
    }

    @Override
    public void input()
    {
        gameStateManager.leaderboard.input();
       if (gameStateManager.leaderboard.Active)
       {
           gameStateManager.gameBoard.bomb = false;
           gameStateManager.gameBoard.Name = false;

           Reset();
           gameStateManager.leaderboard.Active = false;

           gameStateManager.removeState(GameStateManager.GAME);
           gameStateManager.removeState(GameStateManager.QUIZ);
           gameStateManager.removeState(GameStateManager.ENDING);
           gameStateManager.removeState(GameStateManager.LEADERBOARD);
           gameStateManager.addState(GameStateManager.STARTING);
       }
    }

    boolean inLeader = false;

    @Override
    public void render(Graphics2D g)
    {
        gameStateManager.leaderboard.render(g);
    }

    void Reset()
    {
        gameStateManager.category.Math = false;
        gameStateManager.category.History = false;
        gameStateManager.category.Science = false;

        gameStateManager.category.inMath = false;
        gameStateManager.category.inHis = false;
        gameStateManager.category.inSci = false;

        gameStateManager.level.Easy = false;
        gameStateManager.level.Mid = false;
        gameStateManager.level.Hard = false;

        gameStateManager.level.inEasy = false;
        gameStateManager.level.inMid = false;
        gameStateManager.level.inHard = false;
        gameStateManager.category.ConfirmCategory = false;
    }
}
