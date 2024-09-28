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

    String LeaderboardData;

    public Leaderboard(AssetManager assetManager, GameStateManager gameStateManager)
    {
        this.assetManager = assetManager;
        this.gameStateManager = gameStateManager;

        File(2);
        Mid = true;
    }

    public void File(int Difficulty)
    {
        switch (Difficulty)
        {
            case 1:
                LeaderboardData = "Data/EasyLeaderboard.txt";
                refreshLeaderboard();
                break;
            case 2:
                LeaderboardData = "Data/NormalLeaderboard.txt";
                refreshLeaderboard();
                break;
            case 3:
                LeaderboardData = "Data/HardLeaderboard.txt";
                refreshLeaderboard();
                break;
        }

    }

    public void getLeaderboard()
    {
        file = new File(LeaderboardData);

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
            fileWriter = new FileWriter(LeaderboardData, true);
            fileWriter.write(name + ", " + time + ", "+ level + ", " + category + "\n" + "///" + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getLeaderboard();
    }

    public void ClearData()
    {
        String LeaderboardEasy = "Data/EasyLeaderboard.txt";
        String LeaderboardNormal = "Data/NormalLeaderboard.txt";
        String LeaderboardHard = "Data/HardLeaderboard.txt";

        try {
            PrintWriter writer = new PrintWriter(new File(LeaderboardEasy));
            writer.print("///" + "\n");
            writer.close(); // This will clear the contents of the file
            System.out.println("File contents deleted.");

            PrintWriter writer1 = new PrintWriter(new File(LeaderboardNormal));
            writer1.print("///" + "\n");
            writer1.close(); // This will clear the contents of the file
            System.out.println("File contents deleted.");

            PrintWriter writer2 = new PrintWriter(new File(LeaderboardHard));
            writer2.print("///" + "\n");
            writer2.close(); // This will clear the contents of the file
            System.out.println("File contents deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        refreshLeaderboard();
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
        inEasy = assetManager.inButtonCollision(EasyX, EasyY,100, 50, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Easy");
        inMid = assetManager.inButtonCollision(MediumX, MediumY,100, 50, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Medium");
        inHard = assetManager.inButtonCollision(HardX, HardY,100, 50, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Hard");
    }

    public void input()
    {
        if (inEasy)
        {
            assetManager.playSE(1);

            Easy = true;
            Mid = false;
            Hard = false;

            File(1);
        }
        else if (inMid)
        {
            assetManager.playSE(1);

            Easy = false;
            Mid = true;
            Hard = false;

            File(2);
        }
        else if (inHard)
        {
            assetManager.playSE(1);

            Easy = false;
            Mid = false;
            Hard = true;

            File(3);
        }
    }

    public void render(Graphics2D g)
    {
        g.drawImage(assetManager.Shade, 0, 0, assetManager.getScreenWidth(), assetManager.getScreenHeight(), null);
        g.drawImage(assetManager.Leaderboard, 20, 20, 1240, 600, null);
        g.setColor(Color.BLACK);
        assetManager.PrintText("LEADERBOARD", 490, 120, 0, 70, false, g);

        // -- HEADERS --
        assetManager.PrintText("RANK", 180, 190, 0, 50, true, g);
        assetManager.PrintText("NAME", 380, 190, 0, 50, true, g);
        assetManager.PrintText("LEVEL", 580, 190, 0, 50, true, g);
        assetManager.PrintText("CATEGORY", 760, 190, 0, 50, true, g);
        assetManager.PrintText("SCORE", 980, 190, 0, 50, true, g);

        // -- OUTPUTS THE LEADERBOARD --
        for (Player player : players)
        {
            if (players.indexOf(player) < 10)
            {
                int fontSize = 40;
                name = player.name;
                score = String.valueOf(player.score);
                level = player.level;
                category = player.category;

                // -- PRINTS THE LEADERBOARD --
                if (players.indexOf(player) == 0)
                {
                    g.drawImage(assetManager.Medal1, 208, 200, 40, 40, null);
                }
                if (players.indexOf(player) == 1)
                {
                    g.drawImage(assetManager.Medal2, 208, 240, 40, 40, null);
                }
                if (players.indexOf(player) == 2)
                {
                    g.drawImage(assetManager.Medal3, 208, 280, 40, 40, null);
                }
                if (players.indexOf(player) >= 3)
                {
                    // -- RANK --
                    assetManager.PrintText((players.indexOf(player) + 1) + "th", 200, 240 + 37 * players.indexOf(player), 0, fontSize, true, g);
                }

                assetManager.PrintText(name, 380, 240 + 37 * players.indexOf(player), 0, fontSize, true, g);
                assetManager.PrintText(level, 590, 240 + 37 * players.indexOf(player), 0, fontSize, true, g);
                assetManager.PrintText(category, 800, 240 + 37 * players.indexOf(player), 0, fontSize, true, g);
                assetManager.PrintText(score, 1010, 240 + 37 * players.indexOf(player), 0, fontSize, true, g);
            }
        }

        g.setColor(new Color(0, 0, 0));
        // -- TEXTBOX (DIFFICULTY CHOICES) --
        assetManager.TextBox(EasyX, EasyY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("Easy", EasyX + 60, EasyY + 60, 0, 50, false, g);

        assetManager.TextBox(MediumX, MediumY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("Normal", MediumX + 30, MediumY + 60, 0, 50, false, g);

        assetManager.TextBox(HardX, HardY, TextBoxSizeX, TextBoxSizeY, g);
        assetManager.PrintText("Hard", HardX + 60, HardY + 60, 0, 50, false, g);

        // -- DIFFICULTY HOVER --
        if (inEasy || Easy)
        {
            g.setColor(new Color(95, 171, 73));
            assetManager.PrintText("Easy", EasyX + 60, EasyY + 60, 0, 50, false, g);
            g.drawImage(AssetManager.Select1, EasyX, EasyY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inMid || Mid)
        {
            g.setColor(new Color(199, 105, 0));
            assetManager.PrintText("Normal", MediumX + 30, MediumY + 60, 0, 50, false, g);
            g.drawImage(AssetManager.Select1, MediumX, MediumY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inHard || Hard)
        {
            g.setColor(new Color(255, 0, 0));
            assetManager.PrintText("Hard", HardX + 60, HardY + 60, 0, 50, false, g);
            g.drawImage(AssetManager.Select1, HardX, HardY, TextBoxSizeX, TextBoxSizeY, null);
        }
    }

    public boolean inEasy, inMid, inHard;

    int TextBoxSizeX = 200;
    int TextBoxSizeY = 90;

    // -- DIFFICULTY BUTTONS --
    int DTextBoxY = 620;

    public boolean Easy;
    int EasyX = 230;
    int EasyY = DTextBoxY;

    public boolean Mid;
    int MediumX = 530;
    int MediumY = DTextBoxY;

    public boolean Hard;
    int HardX = 830;
    int HardY = DTextBoxY;
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