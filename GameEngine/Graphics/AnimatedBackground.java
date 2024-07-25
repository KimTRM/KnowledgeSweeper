package GameEngine.Graphics;

import GameEngine.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedBackground
{

    private final GamePanel gamePanel;
    private AssetManager assetManager;

    private int spriteNum = 1;
    private int spriteCounter = 0;

    public AnimatedBackground(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }

    public void Update()
    {
        spriteCounter++;
        if(spriteCounter > 15) {
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if(spriteNum == 2) {
                spriteNum = 3;
            }
            else if(spriteNum == 3) {
                spriteNum = 4;
            }
            else if(spriteNum == 4) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void Render(Graphics2D g)
    {
        BufferedImage image = null;
        if(spriteNum == 1) {
            image = AssetManager.B1;
        }
        if(spriteNum == 2) {
            image = AssetManager.B2;
        }
        if(spriteNum == 3) {
            image = AssetManager.B3;
        }
        if(spriteNum == 4) {
            image = AssetManager.B4;
        }
        g.drawImage(image, 0, 0, GamePanel.screenWidth, GamePanel.screenHeight, null);
    }
}
