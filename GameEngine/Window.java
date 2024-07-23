package GameEngine;

import javax.swing.JFrame;

public class Window extends JFrame
{
    public Window()
    {
        setTitle("Knowledge Sweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1280, 720));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}