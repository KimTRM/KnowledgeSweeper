package GameEngine.States.GameStates;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.States.State;
import GameEngine.Util.Leaderboard.PlayerName;
import java.awt.*;

public class PlayerNameState extends State
{
    PlayerName player;

    public PlayerNameState(GameStateManager gameStateManager, AssetManager assetManager, PlayerName player)
    {
        super(gameStateManager, assetManager);
        this.player = player;
    }

    @Override
    public void update()
    {
        if (player.Confirm)
        {
            gameStateManager.leaderboard.WriteData(player.playerName, gameStateManager.gameBoard.Score, gameStateManager.level.Level, gameStateManager.category.category);
            gameStateManager.leaderboard.refreshLeaderboard();

            player.Confirm = false;
            gameStateManager.gameBoard.bomb = false;
            gameStateManager.gameBoard.Name = false;

            Reset();

            gameStateManager.removeState(GameStateManager.PLAYERNAME);
            gameStateManager.removeState(GameStateManager.GAME);
            gameStateManager.removeState(GameStateManager.QUIZ);
            gameStateManager.removeState(GameStateManager.ENDING);
            gameStateManager.addState(GameStateManager.STARTING);
        }

        player.update();
    }

    @Override
    public void input()
    {
       player.input();

        if (gameStateManager.gameBoard.Name)
        {
            gameStateManager.gameBoard.Name = false;

            if (!player.Activated)
            {
                player.Add();
            }
            else if (player.Activated)
            {
                player.Activated = false;
                player.setVisible(true);
            }
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        player.render(g);
        gameStateManager.gameBoard.UI(g);
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

