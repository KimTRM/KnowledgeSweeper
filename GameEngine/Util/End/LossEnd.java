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
        inRestart = assetManager.inButtonCollision(bButtonX, bButtonY, 100, 100, 200, 200, false, "Restart");
        inBacktoStart = assetManager.inButtonCollision(aButtonX, aButtonY, 100, 100, 200, 200, false, "Back to Start");
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
        g.drawImage(assetManager.Restart, bButtonX, bButtonY, 200, 200, null);

        // -- BACK TO MAIN --
        g.drawImage(assetManager.Button, aButtonX, aButtonY, 200, 200, null);
        assetManager.PrintText("GO BACK\nTO START", aButtonX + 40, aButtonY + 105,33, 40, true, g);

        // -- HOVER --
        if (inRestart)
        {
            g.drawImage(assetManager.Restart, bButtonX -10 , bButtonY - 10, 220, 220, null);

        }
        if (inBacktoStart)
        {
            g.drawImage(assetManager.Button, aButtonX - 10, aButtonY - 10, 220, 220, null);
            assetManager.PrintText("GO BACK\nTO START", aButtonX + 30, aButtonY + 105,38, 45, true, g);
        }

    }

    // BACK TO GAME
    int bButtonX = 400;
    int bButtonY = 500;

    // BACK TO MAIN
    int aButtonX = 650;
    int aButtonY = 500;
}
