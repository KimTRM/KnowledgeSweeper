package GameEngine.Util.Leaderboard;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Leaderboard
{
    GameStateManager gameStateManager;
    AssetManager assetManager;

    FileWriter fileWriter;
    File file;

    public Leaderboard(AssetManager assetManager, GameStateManager gameStateManager)
    {
        this.assetManager = assetManager;
        this.gameStateManager = gameStateManager;

        getLeaderboard();
    }

    public void getLeaderboard()
    {
        file = new File("GameEngine/Graphics/res/files/Leaderboard.txt");
        Scanner data;

        String name;
        String time;

        try
        {
            data = new Scanner(file);

            while (data.hasNextLine())
            {
                String line = data.nextLine();
//                String[] token = line.split(",");
//
//                name = token[0];
//                time = token[1];


//                System.out.println(line);
            }
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void WriteData(String name, int time)
    {
        try
        {
            fileWriter = new FileWriter("GameEngine/Graphics/res/files/Leaderboard.txt", true);

            fileWriter.write(name + ", " + time + "\n" + "///" + "\n");

            fileWriter.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void update()
    {

    }
    public void input()
    {

    }
    public void render(Graphics2D g)
    {

    }

}
