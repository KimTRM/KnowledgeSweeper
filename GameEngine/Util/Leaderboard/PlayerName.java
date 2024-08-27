package GameEngine.Util.Leaderboard;

import GameEngine.Graphics.AssetManager;

import javax.swing.*;
import java.awt.*;

public class PlayerName
{
    AssetManager assetManager;

    JTextField playerName;

    public PlayerName(AssetManager assetManager)
    {
        this.assetManager = assetManager;
    }


    public void update()
    {

    }

    public void input()
    {

    }

    public void render(Graphics2D g)
    {
        assetManager.Button(0, 0, 100, 100, g);
        playerName = new JTextField();
        playerName.setPreferredSize(new Dimension(300, 300));
        playerName.setFont(new Font("Arial", Font.BOLD, 30));
        playerName.setText("username");

        System.out.println("Player name: " + playerName.getText());
    }


}
