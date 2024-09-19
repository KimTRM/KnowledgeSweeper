package GameEngine;

import javax.swing.*;

public class GameLauncher
{
    public static JFrame window;

    public static void main(String[] args)
    {
        window = new JFrame("Knowledge Sweeper");
        GamePanel gamePanel = new GamePanel();

        ImageIcon icon = new ImageIcon("GameEngine/Graphics/res/icon/Bomb.png");
        window.setIconImage(icon.getImage());

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        window.setUndecorated(true);
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);

        gamePanel.startGameThread();
    }
}