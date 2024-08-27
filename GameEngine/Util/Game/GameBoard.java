package GameEngine.Util.Game;

import GameEngine.Graphics.AssetManager;

import java.awt.*;
import java.util.Date;
import java.util.Random;

public class GameBoard
{
    AssetManager assetManager;

    public int[][] mines;
    public int[][] neighbours;
    public boolean[][] revealed;
    public boolean[][] AlreadyRevealed;
    public boolean[][] flagged;

    public int neighs;
    Random rend = new Random();
    Date startDate = new Date();

    public GameBoard(AssetManager assetManager)
    {
        this.assetManager = assetManager;

        BombRand();
    }

    int spacing = 2;
    int boxSize = 70;

    int paddingX;
    int paddingY;

    int cols;
    int rows;
    int NumBomb;
    public int TotalBoxes;
    public String Difficulty;
    int rButtonX = 550;
    int rButtonY = -10;
    public int life = 3;

    int timeX = 1090;
    int timeY = 40;

    int sec = 0;
    public int Score;

    public boolean Easy;
    public boolean Medium;
    public boolean Hard;

    int xLoc;
    int yLoc;

    // ----- GAME DIFFICULTY -----
    public void ChangeDifficulty(String difficulty, boolean activate)
    {
        if (activate)
        {
            System.out.println(difficulty + " mode activated");
            Difficulty = difficulty;
        }

        switch (difficulty) {
            case "Easy":
                if (Easy && !Medium && !Hard)
                {
                    EasyMode();
                }
                break;
            case "Medium":
                if (Medium && !Easy && !Hard)
                {
                    MediumMode();
                }
                break;
            case "Hard":
                if (Hard && !Medium && !Easy)
                {
                    HardMode();
                }
                break;
        }
    }
    public void EasyMode() {
        cols = 11;
        paddingX = 5;

        rows = 7;
        paddingY = 1;

        NumBomb = 15;
        TotalBoxes = 30;

        xLoc = 30;
        yLoc = 20;

        init();
    }
    public void MediumMode() {
        cols = 13;
        paddingX = 3;

        rows = 7;
        paddingY = 1;

        NumBomb = 20;
        TotalBoxes = 60;

        xLoc = 50;
        yLoc = 10;

        init();
    }
    public void HardMode() {
        cols = 15;
        paddingX = 1;

        rows = 7;
        paddingY = 1;

        NumBomb = 25;
        TotalBoxes = 80;

        xLoc = 50;
        yLoc = 20;

        init();
    }
    public void init() {
        mines = new int[cols][rows];
        neighbours = new int[cols][rows];
        AlreadyRevealed = new boolean[cols][rows];
        revealed = new boolean[cols][rows];
        flagged = new boolean[cols][rows];
    }

    boolean Reset;
    public void update()
    {
        Reset = assetManager.StartCollision(rButtonX, rButtonY - 20, 190, 170, false, "Reset");

    }

    public void input()
    {
        if (isActive)
        {
            if (boxX() != -1 && boxY() != -1 && !AlreadyRevealed[boxX()][boxY()])
            {
                if (flagger && !revealed[boxX()][boxY()])
                {
                    // -- PLACE BLOCKER --
                    // -- REMOVE BLOCKER --
                    flagged[boxX()][boxY()] = !flagged[boxX()][boxY()] && !AlreadyRevealed[boxX()][boxY()];
                }
                else
                {
                    // -- REVEAL BOX --
                    if (!flagged[boxX()][boxY()] && !AlreadyRevealed[boxX()][boxY()] && !revealed[boxX()][boxY()])
                    {
                        revealed[boxX()][boxY()] = true;
                        AlreadyRevealed[boxX()][boxY()] = true;

                        if (victory())
                        {
                            assetManager.playSE(4);
                        }
                    }
                }

                // -- SETS THE BOMB STATUS --
                if (revealed[boxX()][boxY()])
                {
                    if (mines[boxX()][boxY()] == 1)
                    {
                        assetManager.playSE(7);
                        bomb = true;
                    }
                    else
                    {
                        assetManager.playSE(6);
                    }
                }
            }

            // -- RESETS THE BOARD --
            if (Reset)
            {
                ResetAll(TotalBoxes);
            }

        }
    }

    public void render(Graphics2D g)
    {
        for (int i = paddingX; i < cols; i++)
        {
            for (int j = paddingY; j < rows; j++)
            {
                int boxX = spacing + i * 80;
                int boxY = spacing + j * 80 + 80;
                g.drawImage(AssetManager.Grass, boxX, boxY, boxSize, boxSize, null);

                if (revealed[i][j])
                {
                    g.drawImage(AssetManager.RevGrass,boxX, boxY, boxSize, boxSize, null);

                    // - NEIGHBOURS -
                    if (mines[i][j] == 0 && neighbours[i][j] != 0)
                    {
                        setNeighbourColor(g, neighbours[i][j]);
                        g.setFont(g.getFont().deriveFont(Font.BOLD, 40F));
                        g.drawString(Integer.toString(neighbours[i][j]), boxX + 25, boxY + 50);
                    }

                    // - BOMBS -
                    else if (mines[i][j] == 1)
                    {
                        g.drawImage(AssetManager.Bomb, boxX, boxY, 70 - 2 * spacing, 70 - 2 * spacing, null);
                    }
                }

                // - FLAGGED -
                if (flagged[i][j])
                {
                    g.drawImage(AssetManager.ActiveBlock, boxX, boxY, boxSize, boxSize, null);
                }

                // - HOVER CURSOR -
                if (isActive)
                {
                    if (boxX() == i && boxY() == j)
                    {
                        g.drawImage(AssetManager.Select, boxX, boxY, boxSize, boxSize, null);
                    }
                }
            }
        }

        // -- RESTART GAME --
        g.drawImage(AssetManager.Restart, rButtonX, rButtonY, 190, 170, null);

        // -- HOVER --
        if (isActive)
        {
            if (Reset)
            {
                g.drawImage(AssetManager.Restart, rButtonX - 10, rButtonY - 5, 210, 185, null);
            }
        }
    }

    public boolean bomb;

    // ----- COLLISION DETECTION -----


    int BoxWidth = 80;
    int BoxHeight = 80;
    public int boxX()
    {
        int mx = assetManager.gamePanel.getMX();

        for (int i = paddingX; i < cols; i	++)
        {
            int boxLeft = spacing + i * BoxWidth;
            int boxRight = boxLeft + BoxHeight - spacing;

            if (mx >= boxLeft && mx < boxRight)
            {
                return i;
            }
        }
        return -1;
    }
    public int boxY()

    {
        int my = assetManager.gamePanel.getMY();

        for (int j = paddingY; j < rows; j++)
        {
            int boxTop = spacing + j * 80 + 80;
            int boxBottom = boxTop + 80 - spacing;

            if (my >= boxTop && my < boxBottom)
            {
                return j;
            }
        }
        return -1;
    }

    boolean flagger;
    public boolean isActive;

    private void setNeighbourColor(Graphics2D g, int neighbourCount)
    {
        switch (neighbourCount)
        {
            case 1:
                g.setColor(Color.BLUE);
                break;
            case 2:
                g.setColor(Color.GREEN);
                break;
            case 3:
                g.setColor(Color.RED);
                break;
            case 4:
                g.setColor(new Color(0, 0, 128));
                break;
            case 5:
                g.setColor(new Color(178, 34, 34));
                break;
            case 6:
                g.setColor(new Color(72, 209, 204));
                break;
            case 8:
                g.setColor(Color.DARK_GRAY);
                break;
            default:
                g.setColor(Color.BLACK);
                break;
        }
    }
    public void UI(Graphics2D g)
    {
        // -- LIFE --
        if (life == 3)
        {
            g.drawImage(AssetManager.Life, 0, 10, 80, 80, null);
            g.drawImage(AssetManager.Life, 70, 10, 80, 80, null);
            g.drawImage(AssetManager.Life, 140, 10, 80, 80, null);

        }
        else if (life == 2)
        {
            g.drawImage(AssetManager.Life, 0, 10, 80, 80, null);
            g.drawImage(AssetManager.Life, 70, 10, 80, 80, null);
            g.drawImage(AssetManager.NoLife, 140, 10, 80, 80, null);

        }
        else if (life == 1)
        {
            g.drawImage(AssetManager.Life, 0, 10, 80, 80, null);
            g.drawImage(AssetManager.NoLife, 70, 10, 80, 80, null);
            g.drawImage(AssetManager.NoLife, 140, 10, 80, 80, null);

        }
        else if (life <= 0)
        {
            g.drawImage(AssetManager.NoLife, 0, 10, 80, 80, null);
            g.drawImage(AssetManager.NoLife, 70, 10, 80, 80, null);
            g.drawImage(AssetManager.NoLife, 140, 10, 80, 80, null);

        }


        // -- TIMER --
        g.drawImage(AssetManager.Button, 1050, -10, 190, 170, null);

        if (!victory() && life != 0)
        {
            sec = (int) ((new Date().getTime()- startDate.getTime()) / 1000);
        }

        if (sec > 999)
        {
            sec = 999;
        }

        // -- TIMER COLOR --
        g.setColor(new Color(73, 29, 0));
        if (victory())
        {
            g.setColor(new Color(51, 126, 27));
            Score = sec;
        }
        else if (defeat())
        {
            g.setColor(new Color(173, 11, 11));
            Score = sec;
        }

        // -- TIMER TEXT --
        if (sec < 10)
        {
            assetManager.PrintText("00" + sec, timeX + 10, timeY + 68, 0, 80, false, g);
        }
        else if (sec < 100)
        {
            assetManager.PrintText("0" + sec, timeX + 10, timeY + 68, 0, 80, false, g);
        }
        else
        {
            assetManager.PrintText(Integer.toString(sec), timeX + 10, timeY + 68, 0, 80, false, g);
        }

    }

    // ----- CHECKS THE STATUS OF THE GAME -----
    public int totalMines ()
    {
        int total = 0;
        for (int i = paddingX; i < cols; i++)
        {
            for (int j = paddingY; j < rows; j++)
            {
                if (mines[i][j] == 1) {
                    total++;
                }
            }
        }
        return total;
    }
    public int totalBoxesRevealed ()
    {
        int total = 0;
        for (int i = paddingX; i < cols; i++)
        {
            for (int j = paddingY; j < rows; j++)
            {
                if (revealed[i][j])
                {
                    total++;
                }
            }
        }
        return total;
    }
    public boolean victory () {
        return totalBoxesRevealed() >= TotalBoxes - totalMines();
    }
    public boolean defeat() {
        return life == 0;
    }

    // ----- RESETS ALL -----
    public void ResetAll(int TotalBoxes)
    {
        this.TotalBoxes = TotalBoxes;

        flagger = false;

        startDate = new Date();

        life = 3;

        BombRand();
        BombCheck();
    }

    // ----- RANDOMIZES THE BOMBS -----
    public void BombRand()
    {
        for (int i = paddingX; i < cols; i++)
        {
            for (int j = paddingY; j < rows; j++)
            {
                if ( rend.nextInt(100) < NumBomb )
                {
                    if (mines != null) {
                        mines[i][j] = 1;
                    }
                }
                else
                {
                    if (mines != null)
                    {
                        mines[i][j] = 0;
                    }
                }

                if (revealed != null && AlreadyRevealed != null)
                {
                    revealed[i][j] = false;
                    AlreadyRevealed[i][j] = false;
                }
            }
        }
    }

    // ----- BOMB CHECKER WHEN CLICKING -----
    public boolean isBomb()
    {
        return bomb;
    }

    // ----- CHECKS EVERY DIRECTION -----
    public void BombCheck()
    {
        for (int i = paddingX; i < cols; i++)
        {
            for (int j = paddingY; j < rows; j++)
            {
                neighs = 0;
                for (int m = paddingX; m < cols; m++) {
                    for (int n = paddingY; n < rows; n++) {
                        if (!(m == i && n == j)) {
                            if (isN(i, j, m, n)) {
                                neighs++;
                            }
                        }
                    }
                    neighbours[i][j] = neighs;
                }
            }
        }
    }
    public boolean isN(int mX, int mY, int cX, int cY)
    {
        if (cX >= 0 && cX < mines.length && cY >= 0 && cY < mines[0].length)
        {
            // Check if the cell (cX, cY) is within one cell distance from (mX, mY)
            if (Math.abs(mX - cX) <= 1 && Math.abs(mY - cY) <= 1 && mines[cX][cY] == 1)
            {
                // Ensure we are not checking the same cell
                return mX != cX || mY != cY;
            }
        }
        return false;
    }
}

