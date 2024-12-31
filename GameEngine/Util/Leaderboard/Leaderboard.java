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

        File(13);
        AllC = true;
        AllD = true;
    }

    public void File(int Difficulty)
    {
        switch (Difficulty)
        {
            case 1:
                LeaderboardData = "Data/Science/EasyLeaderboard.txt";
                refreshLeaderboard();
                break;
            case 2:
                LeaderboardData = "Data/Science/NormalLeaderboard.txt";
                refreshLeaderboard();
                break;
            case 3:
                LeaderboardData = "Data/Science/HardLeaderboard.txt";
                refreshLeaderboard();
                break;
            case 4:
                LeaderboardData = "Data/Science/EasyLeaderboard.txt";
                getLeaderboard();
                LeaderboardData = "Data/Science/NormalLeaderboard.txt";
                getLeaderboard();
                LeaderboardData = "Data/Science/HardLeaderboard.txt";
                getLeaderboard();
                break;
            case 5:
                LeaderboardData = "Data/History/EasyLeaderboard.txt";
                refreshLeaderboard();
                break;
            case 6:
                LeaderboardData = "Data/History/NormalLeaderboard.txt";
                refreshLeaderboard();
                break;
            case 7:
                LeaderboardData = "Data/History/HardLeaderboard.txt";
                refreshLeaderboard();
                break;
            case 8:
                LeaderboardData = "Data/History/EasyLeaderboard.txt";
                getLeaderboard();
                LeaderboardData = "Data/History/NormalLeaderboard.txt";
                getLeaderboard();
                LeaderboardData = "Data/History/HardLeaderboard.txt";
                getLeaderboard();
                break;
            case 9:
                LeaderboardData = "Data/Math/EasyLeaderboard.txt";
                refreshLeaderboard();
                break;
            case 10:
                LeaderboardData = "Data/Math/NormalLeaderboard.txt";
                refreshLeaderboard();
                break;
            case 11:
                LeaderboardData = "Data/Math/HardLeaderboard.txt";
                refreshLeaderboard();
                break;
            case 12:
                LeaderboardData = "Data/Math/EasyLeaderboard.txt";
                getLeaderboard();
                LeaderboardData = "Data/Math/NormalLeaderboard.txt";
                getLeaderboard();
                LeaderboardData = "Data/Math/HardLeaderboard.txt";
                getLeaderboard();
                break;
            case 13:
                LeaderboardData = "Data/Science/EasyLeaderboard.txt";
                getLeaderboard();
                LeaderboardData = "Data/Science/NormalLeaderboard.txt";
                getLeaderboard();
                LeaderboardData = "Data/Science/HardLeaderboard.txt";
                getLeaderboard();

                LeaderboardData = "Data/History/EasyLeaderboard.txt";
                getLeaderboard();
                LeaderboardData = "Data/History/NormalLeaderboard.txt";
                getLeaderboard();
                LeaderboardData = "Data/History/HardLeaderboard.txt";
                getLeaderboard();

                LeaderboardData = "Data/Math/EasyLeaderboard.txt";
                getLeaderboard();
                LeaderboardData = "Data/Math/NormalLeaderboard.txt";
                getLeaderboard();
                LeaderboardData = "Data/Math/HardLeaderboard.txt";
                getLeaderboard();
                break;
            case 14:
                LeaderboardData = "Data/Science/EasyLeaderboard.txt";
                getLeaderboard();

                LeaderboardData = "Data/History/EasyLeaderboard.txt";
                getLeaderboard();

                LeaderboardData = "Data/Math/EasyLeaderboard.txt";
                getLeaderboard();
                break;
            case  15:
                LeaderboardData = "Data/Science/NormalLeaderboard.txt";
                getLeaderboard();

                LeaderboardData = "Data/History/NormalLeaderboard.txt";
                getLeaderboard();

                LeaderboardData = "Data/Math/NormalLeaderboard.txt";
                getLeaderboard();
                break;
            case 16:
                LeaderboardData = "Data/Science/HardLeaderboard.txt";
                getLeaderboard();

                LeaderboardData = "Data/History/HardLeaderboard.txt";
                getLeaderboard();

                LeaderboardData = "Data/Math/HardLeaderboard.txt";
                getLeaderboard();
                break;
        }
    }

    void UpdateLeaderBoard()
    {
        if (Science) {
            if (Easy) {
                File(1);
            }
            if (Mid) {
                File(2);
            }
            if (Hard) {
                File(3);
            }
            if (AllD)
            {
                players.clear();
                File(4);
            }
        }
        if (History){
            if (Easy){
                File(5);
            }
            if (Mid){
                File(6);
            }
            if (Hard){
                File(7);
            }
            if (AllD){
                players.clear();
                File(8);
            }
        }
        if (Math){
            if (Easy){
                File(9);
            }
            if (Mid){
                File(10);
            }
            if (Hard){
                File(11);
            }
            if (AllD){
                players.clear();
                File(12);
            }
        }
        if (AllC) {
            if (AllD) {
                players.clear();
                File(13);
            }
            if (Easy){
                players.clear();
                File(14);
            }
            if (Mid){
                players.clear();
                File(15);
            }
            if (Hard) {
                players.clear();
                File(16);
            }
        }
    }

    public void getLeaderboard() {
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
    public void WriteData(String name, int time, String level, String category) {
        try {
            fileWriter = new FileWriter(LeaderboardData, true);
            fileWriter.write(name + ", " + time + ", "+ level + ", " + category + "\n" + "///" + "\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getLeaderboard();
    }
    public void ClearData() {
        String HistoryLeaderboardEasy = "Data/History/EasyLeaderboard.txt";
        String HistoryLeaderboardNormal = "Data/History/NormalLeaderboard.txt";
        String HistoryLeaderboardHard = "Data/History/HardLeaderboard.txt";

        String ScienceLeaderboardEasy = "Data/Science/EasyLeaderboard.txt";
        String ScienceLeaderboardNormal = "Data/Science/NormalLeaderboard.txt";
        String ScienceLeaderboardHard = "Data/Science/HardLeaderboard.txt";

        String MathLeaderboardEasy = "Data/Math/EasyLeaderboard.txt";
        String MathLeaderboardNormal = "Data/Math/NormalLeaderboard.txt";
        String MathLeaderboardHard = "Data/Math/HardLeaderboard.txt";

        try {
            PrintWriter writer = new PrintWriter(new File(HistoryLeaderboardEasy));
            writer.print("///" + "\n");
            writer.close(); // This will clear the contents of the file
            System.out.println("File contents deleted.");

            PrintWriter writer1 = new PrintWriter(new File(HistoryLeaderboardNormal));
            writer1.print("///" + "\n");
            writer1.close(); // This will clear the contents of the file
            System.out.println("File contents deleted.");

            PrintWriter writer2 = new PrintWriter(new File(HistoryLeaderboardHard));
            writer2.print("///" + "\n");
            writer2.close(); // This will clear the contents of the file
            System.out.println("File contents deleted.");

            PrintWriter writer3 = new PrintWriter(new File(ScienceLeaderboardEasy));
            writer3.print("///" + "\n");
            writer3.close(); // This will clear the contents of the file
            System.out.println("File contents deleted.");

            PrintWriter writer4 = new PrintWriter(new File(ScienceLeaderboardNormal));
            writer4.print("///" + "\n");
            writer4.close(); // This will clear the contents of the file
            System.out.println("File contents deleted.");

            PrintWriter writer5 = new PrintWriter(new File(ScienceLeaderboardHard));
            writer5.print("///" + "\n");
            writer5.close(); // This will clear the contents of the file
            System.out.println("File contents deleted.");

            PrintWriter writer6 = new PrintWriter(new File(MathLeaderboardEasy));
            writer6.print("///" + "\n");
            writer6.close(); // This will clear the contents of the file
            System.out.println("File contents deleted.");

            PrintWriter writer7 = new PrintWriter(new File(MathLeaderboardNormal));
            writer7.print("///" + "\n");
            writer7.close(); // This will clear the contents of the file
            System.out.println("File contents deleted.");

            PrintWriter writer8 = new PrintWriter(new File(MathLeaderboardHard));
            writer8.print("///" + "\n");
            writer8.close(); // This will clear the contents of the file
            System.out.println("File contents deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        refreshLeaderboard();
    }

    public void refreshLeaderboard() {
        players.clear();
        getLeaderboard();
    }
    void sort() {
        // -- SORTS THE LEADERBOARD --
        players.sort(Comparator.comparingInt(player -> player.score));
    }
    
    public void update()
    {
        inCategoryButton = assetManager.inButtonCollision(TextBoxLevelX, TextBoxLevelY, offsetX, offsetY, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Level");
        inDifficultyButton = assetManager.inButtonCollision(BoxDifficultyX, BoxDifficultyY, offsetX, offsetY, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Level");
    }

    public void input()
    {
        ActiveCategory = inCategoryButton;
        ActiveDif = inDifficultyButton;

        if (!inDifficultyPopUp)
        {
            CategoryInput();
        }

        if (!inCategoryPopUp)
        {
            DifficultyInput();
        }

    }

    public void render(Graphics2D g)
    {
        g.drawImage(AssetManager.Shade, 0, 0, assetManager.getScreenWidth(), assetManager.getScreenHeight(), null);
        g.drawImage(AssetManager.Leaderboard, 20, 50, 1240, 620, null);

        assetManager.Button(380, -40, 550, 250, g);
        g.drawImage(AssetManager.Star, 600, -20, 110, 110, null);
        g.drawImage(AssetManager.Star, 560, 2, 80, 80, null);
        g.drawImage(AssetManager.Star, 670, 2, 80, 80, null);

        assetManager.drawRoundRect(70, 590, 300, 50, g);

        g.setColor(new Color(73, 29, 0));
        assetManager.PrintText("LEADERBOARD", 490, 120, 0, 70, false, g);

        // -- HEADERS --
        assetManager.PrintText("RANK", 160, 190, 0, 50, true, g);
        assetManager.PrintText("NAME", 380, 190, 0, 50, true, g);
        assetManager.PrintText("LEVEL", 610, 190, 0, 50, true, g);
        assetManager.PrintText("CATEGORY", 790, 190, 0, 50, true, g);
        assetManager.PrintText("TIME", 1030, 190, 0, 50, true, g);

        // -- OUTPUTS THE LEADERBOARD --
        List<Player> playersCopy = new ArrayList<>(players);
        for (Player player : playersCopy)
        {
            if (players.indexOf(player) < 10)
            {
                name = player.name;
                score = String.valueOf(player.score);
                level = player.level;
                category = player.category;

                // -- PRINTS THE LEADERBOARD --
                Medals(player, g);

                // -- NAME --
                PlayersList(player, g);

                // -- LEVEL --
                LevelList(player, g);

                // -- CATEGORY --
                CategoryList(player, g);

                // -- TIME --
                TimeList(player, g);
            }
        }

        g.setColor(new Color(0, 0, 0));
        assetManager.PrintText("Filters:", 78, 588, 0, 25, true, g);

        CategoryFilterPrint(g);
        // -- TEXTBOX (DIFFICULTY CHOICES) --
        if (inCategoryButton || ActiveCategory || inCategoryPopUp)
        {
            CategoryPopUP(g);
        }

        DifficultyFilterPrint(g);
        assetManager.PrintText("+", 210, 630, 0, 35, true, g);
        if (inDifficultyButton || ActiveDif || inDifficultyPopUp)
        {
            DifficultyPopup(g);
        }
    }

    void Medals(Player player,Graphics2D g) {
        if (players.indexOf(player) == 0)
        {
            g.drawImage(assetManager.Medal1, 188, 200, 40, 40, null);
        }
        if (players.indexOf(player) == 1)
        {
            g.drawImage(assetManager.Medal2, 188, 240, 40, 40, null);
        }
        if (players.indexOf(player) == 2)
        {
            g.drawImage(assetManager.Medal3, 188, 280, 40, 40, null);
        }
        if (players.indexOf(player) >= 3)
        {
            // -- RANK --
            assetManager.PrintText((players.indexOf(player) + 1) + "th", 184, 240 + 37 * players.indexOf(player), 0, fontSize, true, g);
        }
    }
    void PlayersList(Player player, Graphics2D g) {
        if (name.length() > 10)
        {
            assetManager.PrintText(name, 350, 240 + 37 * players.indexOf(player), 0, fontSize, true, g);
        }
        else if (name.length() <= 4)
        {
            assetManager.PrintText(name, 400, 240 + 37 * players.indexOf(player), 0, fontSize, true, g);
        }
        else
        {
            assetManager.PrintText(name, 380, 240 + 37 * players.indexOf(player), 0, fontSize, true, g);
        }
    }
    void LevelList(Player player, Graphics2D g) {
        if (level.equals("Normal"))
        {
            assetManager.PrintText(level, 610, 240 + 37 * players.indexOf(player), 0, fontSize, true, g);
        }
        else
        {
            assetManager.PrintText(level, 620, 240 + 37 * players.indexOf(player), 0, fontSize, true, g);
        }
    }
    void CategoryList(Player player, Graphics2D g) {
        assetManager.PrintText(category, 820, 240 + 37 * players.indexOf(player), 0, fontSize, true, g);

    }
    void TimeList(Player player, Graphics2D g) {
        if (score.length() == 1)
        {
            assetManager.PrintText(score, 1065, 240 + 37 * players.indexOf(player), 0, fontSize, true, g);
        }
        else
        {
            assetManager.PrintText(score, 1055, 240 + 37 * players.indexOf(player), 0, fontSize, true, g);
        }
    }

    // -- CATEGORY FILTER --
    void CategoryFilterPrint(Graphics2D g) {
        if (AllC)
        {
            g.setColor(new Color(40, 40, 40));
            assetManager.PrintText("All", 120, 630, 0, 35, false, g);
        }
        if (Science)
        {
            g.setColor(new Color(255, 153, 0));
            assetManager.PrintText("Science", 94, 630, 0, 35, false, g);
        }
        if (History)
        {
            g.setColor(new Color(190, 1, 51));
            assetManager.PrintText("History", 98, 630, 0, 35, false, g);
        }
        if (Math)
        {
            g.setColor(new Color(42, 150, 2));
            assetManager.PrintText("Math", 112, 630, 0, 35, false, g);
        }
    }
    void CategoryPopUP(Graphics2D g) {
        inAllC = assetManager.inButtonCollision(AllCX, AllCY, offsetX - 10, offsetY, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Easy");
        inScience = assetManager.inButtonCollision(ScienceX, ScienceY,offsetX - 10, offsetY, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Easy");
        inHistory = assetManager.inButtonCollision(HistoryX, HistoryY,offsetX - 10, offsetY, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Medium");
        inMath = assetManager.inButtonCollision(MathX, MathY, offsetX -10, offsetY, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Hard");

        inCategoryPopUp = assetManager.inButtonCollision(70, 468, 50, 0, 140, 380, false, "Difficulty");

        assetManager.drawRoundRect(70, 320, 140, 265, g);
        CategoryButtons(g);
    }
    void CategoryInput(){
        if (inAllC) {
            AllC = true;
            Science = false;
            History = false;
            Math = false;

            UpdateLeaderBoard();
        }
        if (inScience) {
            AllC = false;
            Science = true;
            History = false;
            Math = false;

            UpdateLeaderBoard();
        }
        if (inHistory) {
            AllC = false;
            Science = false;
            History = true;
            Math = false;

            UpdateLeaderBoard();
        }
        if (inMath) {
            AllC = false;
            Science = false;
            History = false;
            Math = true;

            UpdateLeaderBoard();
        }
    }
    void CategoryButtons(Graphics2D g) {
        assetManager.PrintText("All", 120, 370, 0, 35, false, g);

        assetManager.PrintText("Science", 94, 430, 0, 35, false, g);

        assetManager.PrintText("History", 98, 490, 0, 35, false, g);

        assetManager.PrintText("Math", 112, 550, 0, 35, false, g);


        // -- DIFFICULTY HOVER --
        if (inAllC || AllC)
        {
            g.setColor(new Color(161, 161, 161, 94));
            g.fillRoundRect(AllCX + 10, AllCY + 3, TextBoxSizeX - 20, TextBoxSizeY - 6, 10, 10);
            g.setColor(new Color(255, 255, 255));
            assetManager.PrintText("All", 120, 370, 0, 35, false, g);
            g.drawImage(AssetManager.Select1, AllCX, AllCY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inScience || Science)
        {
            g.setColor(new Color(161, 161, 161, 94));
            g.fillRoundRect(ScienceX + 10, ScienceY + 3, TextBoxSizeX - 20, TextBoxSizeY - 6, 10, 10);
            g.setColor(new Color(255, 153, 0));
            assetManager.PrintText("Science", 94, 430, 0, 35, false, g);
            g.drawImage(AssetManager.Select1, ScienceX, ScienceY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inHistory || History)
        {
            g.setColor(new Color(161, 161, 161, 94));
            g.fillRoundRect(HistoryX + 10, HistoryY + 3, TextBoxSizeX - 20, TextBoxSizeY - 6, 10, 10);
            g.setColor(new Color(190, 1, 51));
            assetManager.PrintText("History", 98, 490, 0, 35, false, g);
            g.drawImage(AssetManager.Select1, HistoryX, HistoryY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inMath || Math)
        {
            g.setColor(new Color(161, 161, 161, 94));
            g.fillRoundRect(MathX + 10, MathY + 3, TextBoxSizeX - 20, TextBoxSizeY - 6, 10, 10);
            g.setColor(new Color(42, 150, 2));
            assetManager.PrintText("Math", 112, 550, 0, 35, false, g);
            g.drawImage(AssetManager.Select1, MathX, MathY, TextBoxSizeX, TextBoxSizeY, null);
        }
    }

    // -- DIFFICULTY FILTER --
    void DifficultyFilterPrint(Graphics2D g) {
        if (AllD)
        {
            g.setColor(new Color(40, 40, 40));
            assetManager.PrintText("All", 278, 630, 0, 35, false, g);
        }
        if (Easy)
        {
            g.setColor(new Color(95, 171, 73));
            assetManager.PrintText("Easy", 250, 630, 0, 35, false, g);
        }
        if (Mid)
        {
            g.setColor(new Color(199, 105, 0));
            assetManager.PrintText("Normal", 250, 630, 0, 35, false, g);
        }
        if (Hard)
        {
            g.setColor(new Color(255, 0, 0));
            assetManager.PrintText("Hard", 250, 630, 0, 35, false, g);
        }
    }
    void DifficultyPopup(Graphics2D g) {
        inAll = assetManager.inButtonCollision(AllX, AllY,offsetX + 10, offsetY, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Easy");
        inEasy = assetManager.inButtonCollision(EasyX, EasyY,offsetX + 10, offsetY, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Easy");
        inMid = assetManager.inButtonCollision(MediumX, MediumY,offsetX + 10, offsetY, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Medium");
        inHard = assetManager.inButtonCollision(HardX, HardY, offsetX + 10, offsetY, TextBoxSizeX + 30, TextBoxSizeY + 100, false, "Hard");

        inDifficultyPopUp = assetManager.inButtonCollision(228, 468, 50, 0, 140, 380, false, "Difficulty");

        assetManager.drawRoundRect(228, 320, 140, 265, g);
        DifficultyButtons(g);
    }
    void DifficultyInput(){
        if (inAll)
        {
            AllD = true;
            Easy = false;
            Mid = false;
            Hard = false;

            UpdateLeaderBoard();
        }
        if (inEasy)
        {
            assetManager.playSE(1);

            AllD = false;
            Easy = true;
            Mid = false;
            Hard = false;

            UpdateLeaderBoard();
        }
        else if (inMid)
        {
            assetManager.playSE(1);

            AllD = false;
            Easy = false;
            Mid = true;
            Hard = false;

            UpdateLeaderBoard();
        }
        else if (inHard)
        {
            assetManager.playSE(1);

            AllD = false;
            Easy = false;
            Mid = false;
            Hard = true;

            UpdateLeaderBoard();
        }
    }
    void DifficultyButtons(Graphics2D g) {
        assetManager.PrintText("All", 278, 370, 0, 35, false, g);

        assetManager.PrintText("Easy", 265, 430, 0, 35, false, g);

        assetManager.PrintText("Normal", 250, 490, 0, 35, false, g);

        assetManager.PrintText("Hard", 265, 550, 0, 35, false, g);


        // -- DIFFICULTY HOVER --
        if (inAll || AllD)
        {
            g.setColor(new Color(161, 161, 161, 94));
            g.fillRoundRect(AllX + 10, AllY + 3, TextBoxSizeX - 20, TextBoxSizeY - 6, 10, 10);
            g.setColor(new Color(255, 255, 255));
            assetManager.PrintText("All", 278, 370, 0, 35, false, g);
            g.drawImage(AssetManager.Select1, AllX, AllY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inEasy || Easy)
        {
            g.setColor(new Color(161, 161, 161, 94));
            g.fillRoundRect(EasyX + 10, EasyY + 3, TextBoxSizeX - 20, TextBoxSizeY - 6, 10, 10);
            g.setColor(new Color(95, 171, 73));
            assetManager.PrintText("Easy", 265, 430, 0, 35, false, g);
            g.drawImage(AssetManager.Select1, EasyX, EasyY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inMid || Mid)
        {
            g.setColor(new Color(161, 161, 161, 94));
            g.fillRoundRect(MediumX + 10, MediumY + 3, TextBoxSizeX - 20, TextBoxSizeY - 6, 10, 10);
            g.setColor(new Color(199, 105, 0));
            assetManager.PrintText("Normal", 250, 490, 0, 35, false, g);
            g.drawImage(AssetManager.Select1, MediumX, MediumY, TextBoxSizeX, TextBoxSizeY, null);
        }
        if (inHard || Hard)
        {
            g.setColor(new Color(161, 161, 161, 94));
            g.fillRoundRect(HardX + 10, HardY + 3, TextBoxSizeX - 20, TextBoxSizeY - 6, 10, 10);
            g.setColor(new Color(255, 0, 0));
            assetManager.PrintText("Hard", 265, 550, 0, 35, false, g);
            g.drawImage(AssetManager.Select1, HardX, HardY, TextBoxSizeX, TextBoxSizeY, null);
        }
    }

    // -- CATEGORY BUTTONS --
    boolean inCategoryPopUp;
    boolean inCategoryButton;
    boolean inAllC, inScience, inHistory, inMath;
    boolean AllC, Science, History, Math;
    boolean ActiveCategory;

    int AllCX = 65;
    int AllCY = 330;

    int ScienceX = 65;
    int ScienceY = 392;

    int HistoryX = 65;
    int HistoryY = 452;

    int MathX = 65;
    int MathY = 512;

    int offsetX = 70;
    int offsetY = 15;
    int fontSize = 40;

    boolean inAll;
    boolean AllD;
    int AllX = 225;
    int AllY = 330;

    // -- DIFFICULTY BUTTONS --
    boolean inDifficultyPopUp;
    boolean inDifficultyButton;
    int BoxDifficultyX = 225;
    int BoxDifficultyY = 590;
    boolean ActiveDif;

    int TextBoxLevelX = 70;
    int TextBoxLevelY = 590;

    public boolean inEasy, inMid, inHard;

    int TextBoxSizeX = 150;
    int TextBoxSizeY = 50;

    // -- DIFFICULTY BUTTONS --
    int DTextBoxY = 620;

    public boolean Easy;
    int EasyX = 225;
    int EasyY = 392;

    public boolean Mid;
    int MediumX = 225;
    int MediumY = 452;

    public boolean Hard;
    int HardX = 225;
    int HardY = 512;
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