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

    boolean Exit;
    int sButtonX = 1160;
    int sButtonY = 90;

    @Override
    public void update()
    {
        gameStateManager.leaderboard.update();
        Exit = assetManager.ArrowCollision(sButtonX, sButtonY, 28,5, 60, 60, false);
    }

    @Override
    public void input()
    {
        gameStateManager.leaderboard.input();

       if (Exit)
       {
           assetManager.playSE(1);
           gameStateManager.gameBoard.bomb = false;
           gameStateManager.gameBoard.Name = false;

           Reset();

           gameStateManager.removeState(GameStateManager.GAME);
           gameStateManager.removeState(GameStateManager.QUIZ);
           gameStateManager.removeState(GameStateManager.ENDING);
           gameStateManager.removeState(GameStateManager.LEADERBOARD);
           gameStateManager.addState(GameStateManager.STARTING);
       }
    }

    @Override
    public void render(Graphics2D g)
    {
        gameStateManager.leaderboard.render(g);

        g.drawImage(AssetManager.Exit, sButtonX + 5, sButtonY, 50,50,null);

        if (Exit)
        {
            g.drawImage(AssetManager.HoverExit, sButtonX, sButtonY - 5, 60,60,null);
        }

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
