package GameEngine.States.GameStates;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.States.State;

import java.awt.*;

@SuppressWarnings("all")
public class StartingState extends State {

    // -- START BUTTON --
    int sButtonX = 480;
    int sButtonY = 180;

    int sbuttonWidth = 350;
    int sbuttonHeight = 220;

    // -- LEADERBOARD BUTTON --
    int leaderboardX = sButtonX;
    int leaderboardY = 280;

    int leaderboardWidth = sbuttonWidth;
    int leaderboardHeight = sbuttonHeight;

    // -- SETTINGS BUTTON --
    int stButtonX = sButtonX;
    int stButtonY = 380;

    int stbuttonWidth = sbuttonWidth;
    int stbuttonHeight = sbuttonHeight;

    // -- EXIT BUTTON --
    int ExtButtonX = sButtonX;
    int ExtButtonY = 480;

    public StartingState(GameStateManager gameStateManager, AssetManager assetManager) {
        super(gameStateManager, assetManager);
    }

    boolean Start, LeaderBoard,Settings,Exit;

    @Override
    public void update()
    {
        Start = assetManager.StartCollision(sButtonX, sButtonY, sbuttonWidth, sbuttonHeight -20,false, "Start");
        LeaderBoard = assetManager.inButtonCollision(leaderboardX, leaderboardY, 100, 100, leaderboardWidth, leaderboardHeight - 20, false, "Leaderboard");
        Settings = assetManager.inButtonCollision(stButtonX, stButtonY, 100, 100, stbuttonWidth, stbuttonHeight - 20, false, "Settings");
        Exit = assetManager.inButtonCollision(ExtButtonX, ExtButtonY, 100, 100, sbuttonWidth, sbuttonHeight - 20, false, "Exit");
    }

    @Override
    public void input()
    {
        if (!gameStateManager.isStateActive(GameStateManager.MENU))
        {
            if (Start) {
                assetManager.playSE(1);
                gameStateManager.AddAndRemoveState(GameStateManager.GAMEOPTIONS, GameStateManager.STARTING);
            }

            if (LeaderBoard) {
                assetManager.playSE(1);
                gameStateManager.addState(GameStateManager.LEADERBOARD);
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
    public void render(Graphics2D g)
    {
        // - Title -
        g.drawImage(assetManager.Title, 110, -150, 1050, 550, null);

        // - CamhiLogo -
        g.drawImage(assetManager.CamhiLogo,1080, 620, 90, 90, null);

        g.setColor(new Color(96, 95, 95));
        // - START BUTTON -
        if (!Start) {
//        assetManager.Button(sButtonX, sButtonY, sbuttonWidth, sbuttonHeight, g);
            assetManager.PrintText("START", sButtonX + 130, sButtonY + 130, 0, 50, false, g);
        }
        // - LEADERBOARD BUTTON -
        if (!LeaderBoard) {
//        assetManager.Button(leaderboardX,  leaderboardY, leaderboardWidth, leaderboardHeight, g);
            assetManager.PrintText("LEADERBOARD", leaderboardX + 50, leaderboardY + 130, 0, 50, false, g);
        }
        // - SETTINGS BUTTON -
        if (!Settings) {
//        assetManager.Button(stButtonX, stButtonY, stbuttonWidth, stbuttonHeight, g);
            assetManager.PrintText("SETTINGS", stButtonX + 100, stButtonY + 130, 0, 50, false, g);
        }
        // - EXIT BUTTON -
        if (!Exit) {
//        assetManager.Button(ExtButtonX, ExtButtonY, sbuttonWidth, sbuttonHeight, g);
            assetManager.PrintText("EXIT", ExtButtonX + 143, ExtButtonY + 130, 0, 50, false, g);
        }
        // - SELECTION HOVER -
        if (!gameStateManager.isStateActive(GameStateManager.MENU))
        {
            if (Start) {
//                assetManager.Button(sButtonX - 10, sButtonY - 12, sbuttonWidth + 20, sbuttonHeight + 20, g);
                assetManager.PrintText("START", sButtonX + 125,sButtonY + 130, 0, 56, true, g);
            }
            if (LeaderBoard) {
//                assetManager.Button(leaderboardX - 10, leaderboardY - 12, leaderboardWidth + 20, leaderboardHeight + 20, g);
                assetManager.PrintText("LEADERBOARD", leaderboardX + 38, leaderboardY + 130, 0, 56, true, g);
            }
            if (Settings) {
//                assetManager.Button(stButtonX - 10, stButtonY - 12, stbuttonWidth + 20, stbuttonHeight + 20, g);
                assetManager.PrintText("SETTINGS", stButtonX + 88, stButtonY + 130, 0, 56, true, g);
            }
            if (Exit) {
//                assetManager.Button(ExtButtonX - 10, ExtButtonY - 12, sbuttonWidth + 20, sbuttonHeight + 20, g);
                g.setColor(new Color(0x8F0000));
                assetManager.PrintText("EXIT", ExtButtonX + 140, ExtButtonY + 130, 0, 56, false, g);
            }
        }

        g.drawImage(assetManager.KLTL_Logo, 1120, 570, 200, 200, null);

        g.setColor(new Color(0xB9B9B9));
        assetManager.PrintText(gameStateManager.version, 20, 700, 0, 20, false, g);
    }
}
