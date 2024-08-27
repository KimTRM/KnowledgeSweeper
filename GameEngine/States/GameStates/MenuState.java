package GameEngine.States.GameStates;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.States.State;

import java.awt.*;

public class MenuState extends State
{
    public MenuState(GameStateManager gameStateManager, AssetManager assetManager)
    {
        super(gameStateManager, assetManager);
    }

    boolean MusicLeftArrow, MusicRightArrow, SFXLeftArrow, SFXRightArrow, Confirm;
    @Override
    public void update()
    {
        MusicLeftArrow = assetManager.ArrowCollision(ArwLVolX, ArwLVolY, 20, 10, ArwWidth, ArwHeight,false);
        MusicRightArrow = assetManager.ArrowCollision(ArwRVolX, ArwRVolY, 20, 10, ArwWidth, ArwHeight,false);

        SFXLeftArrow = assetManager.ArrowCollision(ArwLSndEfctX, ArwLSndEfctY, 20, 10, ArwWidth, ArwHeight,false);
        SFXRightArrow = assetManager.ArrowCollision(ArwRSndEfctX, ArwRSndEfctY, 20, 10, ArwWidth, ArwHeight,false);

        Confirm = assetManager.inButtonCollision(ConfirmButtonX, ConfirmButtonY, 80, 90, ConfirmBtnW, ConfirmBtnH, false, "Confirm");
    }

    @Override
    public void input()
    {
        // - MUSIC VOLUME CONTROLS -
        if (MusicLeftArrow)
        {
            assetManager.playSE(1);
            --assetManager.Music.volumeScale;

            if (assetManager.Music.volumeScale <= 0)
            {
                assetManager.Music.volumeScale = 0;
            }

            assetManager.Music.checkVolume();
        }
        if (MusicRightArrow)
        {
            assetManager.playSE(1);
            ++assetManager.Music.volumeScale;

            if (assetManager.Music.volumeScale >= 5)
            {
                assetManager.Music.volumeScale = 6;
            }

            assetManager.Music.checkVolume();
        }

        // - SFX VOLUME CONTROLS -
        if (SFXLeftArrow)
        {
            assetManager.playSE(1);
            --assetManager.SE.volumeScale;
            if (assetManager.SE.volumeScale <= 0)
            {
                assetManager.SE.volumeScale = 0;
            }
        }
        if (SFXRightArrow)
        {
            assetManager.playSE(1);
            ++assetManager.SE.volumeScale;
            if (assetManager.SE.volumeScale >= 5)
            {
                assetManager.SE.volumeScale = 6;
            }
        }

        // -- CONFIRM --
        if (Confirm)
        {
            assetManager.playSE(1);
            gameStateManager.removeState(GameStateManager.MENU);

        }
    }

    @Override
    public void render(Graphics2D g)
    {

        g.drawImage(assetManager.Shade, 0, 0, assetManager.getScreenWidth(), assetManager.getScreenHeight(), null);
        assetManager.TextBox(400, 0, 530, 500, g);

        g.setColor(new Color(0, 0, 0));
        assetManager.PrintText("SETTINGS", 588, 110,0, 40, false, g);

        // -- MUSIC VOLUME CONTROLS --
        int MvolumeWidth = 40 * assetManager.Music.volumeScale;
        assetManager.drawSubWindow(MBoxX, MBoxY, MBtnWidth, MBtnHeight, g);
        g.fillRect(MBoxX + 8, MBoxY + 8, MvolumeWidth, CBtnHeight - 13);
        assetManager.PrintText("MUSIC", 470, 208,0, 40, true, g);

        assetManager.LeftArrow(ArwLVolX, ArwLVolY, ArwWidth, ArwHeight, g);
        if (MusicLeftArrow)
        {
            assetManager.LeftArrow(ArwLVolX - 2, ArwLVolY - 5, ArwWidth + 10, ArwHeight + 9, g);
        }
        assetManager.RightArrow(ArwRVolX, ArwRVolY, ArwWidth, ArwHeight, g);
        if (MusicRightArrow)
        {
            assetManager.RightArrow(ArwRVolX - 5, ArwRVolY - 5, ArwWidth + 10, ArwHeight + 9, g);
        }

        // -- SFX VOLUME CONTROLS --
        int SvolumeWidth = 40 * assetManager.SE.volumeScale;
        assetManager.drawSubWindow(SBoxX, SBoxY, SBtnWidth, SBtnHeight, g);
        g.fillRect(SBoxX + 8, SBoxY + 8, SvolumeWidth, CBtnHeight - 13);
        assetManager.PrintText("Sound \nEffects", 480, 250, 25, 30, true, g);
        assetManager.LeftArrow(ArwLSndEfctX, ArwLSndEfctY, ArwWidth, ArwHeight, g);
        assetManager.RightArrow(ArwRSndEfctX, ArwRSndEfctY, ArwWidth, ArwHeight, g);

        if (SFXLeftArrow)
        {
            assetManager.LeftArrow(ArwLSndEfctX - 2, ArwLSndEfctY - 5, ArwWidth + 10, ArwHeight + 9, g);
        }
        if (SFXRightArrow)
        {
            assetManager.RightArrow(ArwRSndEfctX - 5, ArwRSndEfctY - 5, ArwWidth + 10, ArwHeight + 9, g);
        }


        // - CONFIRM BUTTON -
        assetManager.Button(ConfirmButtonX, ConfirmButtonY, ConfirmBtnW, ConfirmBtnH, g);
        assetManager.PrintText("CONFIRM", 595, 388,0, 40, true, g);

        if (Confirm)
        {
            assetManager.Button(ConfirmButtonX - 10, ConfirmButtonY - 10, ConfirmBtnW + 20, ConfirmBtnH + 20, g);
            assetManager.PrintText("CONFIRM", 588, 390,0, 45, true, g);
        }
    }

    // -- CHECK BOX FOR FULL SCREEN --
    int CBoxX = 580;
    int CBoxY = 115;
    int CBtnWidth = 50;
    int CBtnHeight = 50;

    // -- FOR BUTTON DITECTION (CHECK BOX) --
    boolean Active;
    public boolean FullScreenButton;
    int tCenterBX = CBoxX + 20;
    int tCenterBY = CBoxY + 20;

    // -- MUSIC VOLUME --
    int MBoxX = 590;
    int MBoxY = 170;
    int MBtnWidth = 250;
    int MBtnHeight = 50;

    int ArwWidth = 40;
    int ArwHeight = 40;

    int ArwLVolX = 588;
    int ArwLVolY = 175;

    int ArwRVolX = 802;
    int ArwRVolY = 175;


    // -- SOUND EFFECTS VOLUME --
    int SBoxX = 590;
    int SBoxY = 230;

    int SBtnWidth = 250;
    int SBtnHeight = 50;

    int ArwLSndEfctX = 588;
    int ArwLSndEfctY = 235;

    int ArwRSndEfctX = 802;
    int ArwRSndEfctY = 235;

    // -- CONFIRM BUTTON --
    int ConfirmButtonX = 570;
    int ConfirmButtonY = 280;
    int ConfirmBtnH = 180;
    int ConfirmBtnW = 180;
}
