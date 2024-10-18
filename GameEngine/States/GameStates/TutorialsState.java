package GameEngine.States.GameStates;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.States.State;
import GameEngine.Util.Tutorial.PageManager;

import java.awt.*;

public class TutorialsState extends State
{

   PageManager pageManager;
    public TutorialsState(GameStateManager gameStateManager, AssetManager assetManager)
    {
        super(gameStateManager, assetManager);

        pageManager = new PageManager(assetManager, gameStateManager);
    }

    boolean Exit;
    int sButtonX = 1160;
    int sButtonY = 60;

    @Override
    public void update()
    {
        pageManager.update();

        Exit = assetManager.ArrowCollision(sButtonX, sButtonY, 28,5, 60, 60, false);

        pageManager.addPage(PageManager.PAGE1);

    }

    @Override
    public void input()
    {
        pageManager.input();

        if (Exit)
        {
            assetManager.playSE(1);

            gameStateManager.removeState(GameStateManager.TUTORIALS);
        }

    }

    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(assetManager.Shade, 0, 0, assetManager.getScreenWidth(), assetManager.getScreenHeight(), null);
        g.drawImage(assetManager.Leaderboard, 20, 20, 1240, 600, null);

        g.drawImage(AssetManager.Exit, sButtonX + 5, sButtonY, 50,50,null);

        if (Exit)
        {
            g.drawImage(AssetManager.HoverExit, sButtonX, sButtonY - 5, 60,60,null);
        }

        pageManager.render(g);
    }
}
