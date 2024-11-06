package GameEngine.Util.End;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;

import java.awt.*;

public class LossEnd
{
    GameStateManager gp;
    AssetManager assetManager;
    public LossEnd(AssetManager assetManager, GameStateManager gp)
    {
        this.assetManager = assetManager;
        this.gp = gp;
    }

    public boolean inRestart, inBacktoStart;
    public void update()
    {

        inRestart = assetManager.ArrowCollision(bButtonX, bButtonY, 28, 15, 90, 90, false);
        inBacktoStart = assetManager.ArrowCollision(aButtonX, aButtonY, 28,15, 90, 90, false);
    }

    public void input()
    {
        if (inRestart)
        {
            assetManager.playSE(1);
            gp.gameBoard.ResetAll(gp.gameBoard.TotalBoxes);
        }

        if (inBacktoStart)
        {
            assetManager.playSE(1);

            gp.gameBoard.ResetAll(gp.gameBoard.TotalBoxes);
            gp.quizManager.clear();
        }
    }

    public void render(Graphics2D g)
    {
        g.drawImage(assetManager.Shade, 0, 0, assetManager.getScreenWidth(), assetManager.getScreenHeight(), null);
        // -- BOX --
        g.drawImage(assetManager.Button, 20, -200, 1200, 1000, null);
        g.drawImage(assetManager.Defeat, 130, 50, 1000, 500, null);

        // -- RESTART GAME --
        g.drawImage(AssetManager.Reset, bButtonX, bButtonY, 80, 80, null);

        // -- HOME BUTTON --
        g.drawImage(AssetManager.Home, aButtonX, aButtonY, 80,80,null);


        if (inRestart)
        {
            g.drawImage(AssetManager.Reset, bButtonX - 5, bButtonY -5, 90, 90, null);
        }

        if (inBacktoStart)
        {
            g.drawImage(AssetManager.Home, aButtonX - 5, aButtonY -5, 90,90,null);
        }

    }

    // BACK TO GAME
    int bButtonX = 500;
    int bButtonY = 550;

    // BACK TO MAIN
    int aButtonX = 660;
    int aButtonY = 550;
}
