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

    public abstract void update(double delta);
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g);
}
