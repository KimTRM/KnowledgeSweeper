package GameEngine.Util.Tutorial.Pages;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.Util.Tutorial.Page;

import java.awt.*;

public class Page1 extends Page
{
    public Page1(AssetManager assetManager, GameStateManager gameStateManager)
    {
        super(assetManager, gameStateManager);
    }

    @Override
    public void update()
    {

    }

    @Override
    public void input()
    {

    }

    @Override
    public void render(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        assetManager.PrintText("CONTROLS", 550, 120, 0, 50, false, g);

        g.drawImage(AssetManager.Mouse_Left, 100, 150, 300, 300, null);
        g.drawImage(AssetManager.RevGrass, 380, 220, 200, 200, null);

        assetManager.PrintText("Left Click to Reveal \n       a Square", 200, 500, 40, 50, false, g);

        g.drawImage(AssetManager.Mouse_Right, 650, 150, 300, 300, null);
        g.drawImage(AssetManager.Grass, 920, 220, 200, 200, null);
        g.drawImage(AssetManager.ActiveBlock, 920, 220, 200, 200, null);

        assetManager.PrintText("Right Click to Mark \n       a Square", 760, 500, 40, 50, false, g);
    }
}
