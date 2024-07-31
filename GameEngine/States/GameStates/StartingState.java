package GameEngine.States.GameStates;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.States.State;

import java.awt.*;

public class StartingState extends State {

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

    public StartingState(GameStateManager gameStateManager, AssetManager assetManager) {
        super(gameStateManager, assetManager);
    }

    boolean Start,Settings,Exit;

    @Override
    public void update()
    {
        Start = assetManager.StartCollision(sButtonX, sButtonY, sbuttonWidth, sbuttonHeight,false, "Start");
        Settings = assetManager.inButtonCollision(stButtonX, stButtonY, 240, 215, stbuttonWidth, stbuttonHeight, false, "Settings");
        Exit = assetManager.inButtonCollision(ExtButtonX, ExtButtonY, 280, 215, sbuttonWidth, sbuttonHeight, false, "Exit");
    }

    @Override
    public void input()
    {
        if (!gameStateManager.isStateActive(GameStateManager.MENU))
        {
            if (Start) {
                assetManager.playSE(1);
//                gameStateManager.AddAndRemoveState(GameStateManager.ENDING, GameStateManager.STARTING);
                gameStateManager.AddAndRemoveState(GameStateManager.GAMEOPTIONS, GameStateManager.STARTING);

            }

            if (Settings) {
                assetManager.playSE(1);
                gameStateManager.addState(GameStateManager.MENU);
            }

            if (Exit) {
                assetManager.playSE(1);
                System.exit(0);
            }

        }
    }

    @Override
    public void render(Graphics2D g) {
        // - Title -
        g.drawImage(assetManager.Title, 240, -50, 850, 450, null);

        // - CamhiLogo -
        g.drawImage(assetManager.CamhiLogo,5, 5, 150, 150, null);

        // - START BUTTON -
        assetManager.Start(sButtonX, sButtonY, sbuttonWidth, sbuttonHeight, g);

        // - SETTINGS BUTTON -
        assetManager.Button(stButtonX, stButtonY, stbuttonWidth, stbuttonHeight, g);
        assetManager.PrintText("SETTINGS", 580, 535, 0, 50, true, g);

        // - EXIT BUTTON -
        assetManager.Button(ExtButtonX, ExtButtonY, sbuttonWidth, sbuttonHeight, g);
        assetManager.PrintText("EXIT", 826, 535, 0, 50, true, g);

        // - SELECTION HOVER -
        if (!gameStateManager.isStateActive(GameStateManager.MENU))
        {
            if (Start) {
                assetManager.Start(sButtonX - 10, sButtonY - 12, sbuttonWidth + 20, sbuttonHeight + 20, g);
            }
            if (Settings) {
                assetManager.Button(stButtonX - 10, stButtonY - 12, stbuttonWidth + 20, stbuttonHeight + 20, g);
                assetManager.PrintText("SETTINGS", 569, 535, 0, 56, true, g);
            }
            if (Exit) {
                assetManager.Button(ExtButtonX - 10, ExtButtonY - 12, sbuttonWidth + 20, sbuttonHeight + 20, g);
                g.setColor(new Color(0x8F0000));
                assetManager.PrintText("EXIT", 824, 535, 0, 56, false, g);
            }
        }
    }
}
