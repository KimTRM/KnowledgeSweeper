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
        int ExtButtonX = 750;
        int ExtButtonY = 400;
        int sbuttonWidth = 220;
        int sbuttonHeight = 220;
//        assetManager.inButtonCollision(ExtButtonX, ExtButtonY, sbuttonWidth, sbuttonHeight);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key)
    {
        // --- LEFT CLICK ---
        if (mouse.getButton() == 1)
        {
            gameStateManager.removeState(GameStateManager.STARTING);
            assetManager.playSE(1);
            System.out.println("Left Clicked");
        }

        // --- RIGHT CLICK ---
        if (mouse.getButton() == 3)
        {
            assetManager.playSE(2);
            System.out.println("Right Clicked");
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        // -- START BUTTON --
        int sButtonX = 350;
        int sButtonY = 400;

        int sbuttonWidth = 220;
        int sbuttonHeight = 220;

        // -- SETTINGS BUTTON --
        int stButtonX = 550;
        int stButtonY = sButtonY;

        int stbuttonWidth = sbuttonWidth;
        int stbuttonHeight = sbuttonHeight;

        // -- EXIT BUTTON --
        int ExtButtonX = 750;
        int ExtButtonY = sButtonY;
        // - Title -

        g.drawImage(assetManager.Title,240, -50, 850, 450, null);

        // - CamhiLogo -
        g.drawImage(assetManager.CamhiLogo,5, 5, 150, 150, null);

        // - START BUTTON -
        g.drawImage(assetManager.Start, sButtonX, sButtonY, sbuttonWidth, sbuttonHeight, null);

        // - SETTINGS BUTTON -
        assetManager.Button(stButtonX, stButtonY, stbuttonWidth, stbuttonHeight, g);

        g.setColor(new Color(73, 29, 0));
        g.setFont(g.getFont().deriveFont(Font.BOLD, 50F));
        g.drawString("SETTINGS", 580, 535);

        // - EXIT BUTTON -
        assetManager.Button(ExtButtonX, ExtButtonY, sbuttonWidth, sbuttonHeight, g);

        g.setFont(g.getFont().deriveFont(Font.BOLD, 50F));
        g.drawString("EXIT", 826, 535);
    }
}
