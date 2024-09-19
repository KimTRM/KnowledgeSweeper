package GameEngine.Util.Leaderboard;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Leaderboard
{
    GameStateManager gameStateManager;
    AssetManager assetManager;

    FileWriter fileWriter;
    File file;

    List<Player> players = new ArrayList<>();
    String name;
    String score;
    String level;
    String category;

    public Leaderboard(AssetManager assetManager, GameStateManager gameStateManager)
    {
        this.assetManager = assetManager;
        this.gameStateManager = gameStateManager;

        refreshLeaderboard();
    }

    public void getLeaderboard()
    {
        file = new File("GameEngine/Graphics/res/files/Leaderboard.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading/trailing spaces and ///
                if (!line.equals("///")) {
                    // Split name and score by ","
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        String name = parts[0].trim();
                        int score = Integer.parseInt(parts[1].trim());
                        String level = parts[2].trim();
                        String category = parts[3].trim();

                        // Add player to the list
                        players.add(new Player(name, score, level, category));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sort();
    }

    // Write data to the leaderboard file
    public void WriteData(String name, int time, String level, String category)
    {
        try {
            fileWriter = new FileWriter("GameEngine/Graphics/res/files/Leaderboard.txt", true);
            fileWriter.write(name + ", " + time + ", "+ level + ", " + category + "\n" + "///" + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getLeaderboard();
    }

    public void refreshLeaderboard()
    {
        players.clear();
        getLeaderboard();
    }

    void sort()
    {
        // -- SORTS THE LEADERBOARD --
        players.sort(Comparator.comparingInt(player -> player.score));
    }

    public boolean Active;
    public void update()
    {

    }

    public void input()
    {
//        Active = true;
    }

    public void render(Graphics2D g)
    {
        g.drawImage(assetManager.Shade, 0, 0, assetManager.getScreenWidth(), assetManager.getScreenHeight(), null);
        assetManager.TextBox(250, 10, 800, 700, g);
        g.setColor(Color.BLACK);
        assetManager.PrintText("LEADERBOARD", 490, 165, 0, 70, false, g);

        // -- OUTPUTS THE LEADERBOARD --
        for (Player player : players)
        {
            name = player.name;
            score = String.valueOf(player.score);
            level = player.level;
            category = player.category;

            assetManager.PrintText(name + ": " + " - " + level + " - " + category + " - " + score, 500, 250 + 30 * players.indexOf(player), 0, 30, false, g);
        }
    }
}

// Player class to store name and score
class Player
{
    String name;
    int score;
    String level;
    String category;

    Player(String name, int score, String level, String category)
    {

        this.name = name;
        this.score = score;
        this.level = level;
        this.category = category;
    }
}