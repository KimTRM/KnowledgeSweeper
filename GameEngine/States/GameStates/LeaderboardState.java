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
    }

    @Override
    public void render(Graphics2D g)
    {
        gameStateManager.leaderboard.render(g);
    }
}
