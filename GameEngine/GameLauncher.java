package GameEngine;

import javax.swing.*;

public class GameLauncher
{
    public  static JFrame window;

    public static void main(String[] args)
    {
        window = new JFrame("Knowledge Sweeper");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(new GamePanel());
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}