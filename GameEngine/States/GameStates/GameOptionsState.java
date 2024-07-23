package GameEngine.States.GameStates;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.States.State;
import GameEngine.Util.KeyHandler;
import GameEngine.Util.MouseHandler;

import java.awt.*;

public class GameOptionsState extends State {
    public GameOptionsState(GameStateManager gameStateManager, AssetManager assetManager) {
        super(gameStateManager, assetManager);
    }

    @Override
    public void update(double delta)
    {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key)
    {

    }

    @Override
    public void render(Graphics2D g)
    {
        assetManager.Button(500, 500, 100, 100, g);
    }
}
