package GameEngine.States;

import GameEngine.Graphics.AssetManager;
import GameEngine.Util.KeyHandler;
import GameEngine.Util.MouseHandler;

import java.awt.Graphics2D;

public abstract class State {

    protected GameStateManager gameStateManager;
    protected AssetManager assetManager;

    public State(GameStateManager gameStateManager, AssetManager assetManager)
    {
        this.gameStateManager = gameStateManager;
        this.assetManager = assetManager;
    }

    public abstract void update();
    public abstract void input();
    public abstract void render(Graphics2D g);
}
