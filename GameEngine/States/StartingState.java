package GameEngine.States;

import GameEngine.Graphics.AssetManager;
import GameEngine.Util.KeyHandler;
import GameEngine.Util.MouseHandler;

import java.awt.*;

public class StartingState extends State {
    public StartingState(GameStateManager gameStateManager, AssetManager assetManager) {
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
        g.drawImage(assetManager.Bomb, 50,50, null);
    }
}
