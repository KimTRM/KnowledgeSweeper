package GameEngine.Graphics;

import GameEngine.GamePanel;
import GameEngine.States.GameStateManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameBoardAnimation
{
    GameStateManager gsm;

    public GameBoardAnimation(GameStateManager gsm)
    {
        this.gsm = gsm;
    }

    private int spriteNum = 1;
    private int spriteCounter = 0;

    public void Update()
    {
        spriteCounter++;
        if(spriteCounter > 15)
        {
            if(spriteNum == 1)
            {
                spriteNum = 2;
            }
            else if(spriteNum == 2)
            {
                spriteNum = 3;
            }
            else if(spriteNum == 3)
            {
                spriteNum = 4;
            }
            else if(spriteNum == 4)
            {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void Render(Graphics2D g)
    {
        if (gsm.level.Easy)
        {
            Easy(g);
        }
        if (gsm.level.Mid)
        {
            Normal(g);
        }
        if (gsm.level.Hard)
        {
            Hard(g);
        }

    }

    void Easy(Graphics2D g)
    {
        BufferedImage image = null;
        if(spriteNum == 1) {
            image = AssetManager.EasyBG1;
        }
        if(spriteNum == 2) {
            image = AssetManager.EasyBG2;
        }
        if(spriteNum == 3) {
            image = AssetManager.EasyBG3;
        }
        if(spriteNum == 4) {
            image = AssetManager.EasyBG4;
        }
        g.drawImage(image, 0, 0, GamePanel.screenWidth, GamePanel.screenHeight, null);
    }
    void Normal(Graphics2D g)
    {
        BufferedImage image = null;
        if(spriteNum == 1) {
            image = AssetManager.NormalBG1;
        }
        if(spriteNum == 2) {
            image = AssetManager.NormalBG2;
        }
        if(spriteNum == 3) {
            image = AssetManager.NormalBG3;
        }
        if(spriteNum == 4) {
            image = AssetManager.NormalBG4;
        }
        g.drawImage(image, 0, 0, GamePanel.screenWidth, GamePanel.screenHeight, null);
    }
    void Hard(Graphics2D g)
    {
        BufferedImage image = null;
        if(spriteNum == 1) {
            image = AssetManager.HardBG1;
        }
        if(spriteNum == 2) {
            image = AssetManager.HardBG2;
        }
        if(spriteNum == 3) {
            image = AssetManager.HardBG3;
        }
        if(spriteNum == 4) {
            image = AssetManager.HardBG4;
        }
        g.drawImage(image, 0, 0, GamePanel.screenWidth, GamePanel.screenHeight, null);
    }
}
