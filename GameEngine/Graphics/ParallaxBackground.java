package GameEngine.Graphics;

import GameEngine.GamePanel;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParallaxBackground implements ActionListener
{
    GamePanel panel;

    // Position of the background
    private int backgroundX = 0;

    private Timer timer;
    private Image background;

    private final int width = GamePanel.screenWidth;
    private final int height = GamePanel.screenHeight;

    public ParallaxBackground() {
        // -- Load the background image --
        background = new ImageIcon("GameEngine/Graphics/res/background/Bg.jpg").getImage();

        // -- Timer for the game loop, fires every 16 ms (~60 FPS) --
        timer = new Timer(16, this);
        timer.start();
    }

    public void Render(Graphics g)
    {
        // -- Draw the background image and loop it by drawing a second copy --
        g.drawImage(background, backgroundX, 0, width, height, null);
        g.drawImage(background, backgroundX + width, 0, width, height, null);  // Draw the second copy right after the first one
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // -- Move the background --
        backgroundX -= 1;  // Speed of the background movement (adjust for faster or slower)

        // -- Reset position when the image has fully moved off-screen --
        if (backgroundX <= - width) {
            backgroundX = 0;
        }
    }
}
