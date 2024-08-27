package GameEngine.States.GameStates;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.States.State;
import GameEngine.Util.Leaderboard.PlayerName;

import java.awt.*;

public class PlayerNameState extends State
{
    public PlayerNameState(GameStateManager gameStateManager, AssetManager assetManager)
    {
        super(gameStateManager, assetManager);

        new PlayerName(assetManager);
    }

    @Override
    public void update()
    {
        gameStateManager.playerName.update();
    }

    @Override
    public void input()
    {
        gameStateManager.playerName.input();
    }

    @Override
    public void render(Graphics2D g)
    {
        gameStateManager.playerName.render(g);
    }
}

