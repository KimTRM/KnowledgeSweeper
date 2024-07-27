package GameEngine;

import javax.swing.*;
import java.awt.*;

public class GameLauncher
{
    public  static JFrame window;

    public static void main(String[] args)
    {
        window = new JFrame("Knowledge Sweeper");
        GamePanel gamePanel = new GamePanel();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        window.setUndecorated(true);
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(true);

        gamePanel.startGameThread();
        window.setPreferredSize(new Dimension(gamePanel.getScreenWidth2(), gamePanel.getScreenHeight2()));
    }
}