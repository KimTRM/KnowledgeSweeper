package GameEngine.Util.Tutorial;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;

import java.awt.*;

public abstract class Page
{
    protected AssetManager assetManager;
    protected GameStateManager gameStateManager;

    public Page( AssetManager assetManager, GameStateManager gameStateManager)
    {
        this.assetManager = assetManager;
        this.gameStateManager = gameStateManager;
    }

    public abstract void update();
    public abstract void input();
    public abstract void render(Graphics2D g);
}