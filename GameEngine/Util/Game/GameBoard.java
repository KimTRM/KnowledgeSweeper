package GameEngine.Util.Game;

import GameEngine.Graphics.AssetManager;

import java.awt.*;
import java.util.Date;
import java.util.Random;

public class GameBoard {
    AssetManager assetManager;

    public int mines[][];
    public int neighbours[][];
    public boolean revealed[][];
    public boolean AlreadyRevealed[][];
    public boolean flagged[][];

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
                if (activate) {
                    EasyMode();
                }
                break;
            case "Medium":
                if (activate) {
                    MediumMode();
                }
                break;
            case "Hard":
                if (activate) {
                    HardMode();
                }
                break;
        }
    }
    public void EasyMode() {
        cols = 12;
        paddingX = 6;

        rows = 7;
        paddingY = 2;

        NumBomb = 15;
        TotalBoxes = 30;

        init();
    }
    public void MediumMode() {
        cols = 15;
        paddingX = 3;

        rows = 8;
        paddingY = 2;

        NumBomb = 20;
        TotalBoxes = 60;

        init();
    }
    public void HardMode() {
        cols = 16;
        paddingX = 2;

        rows = 8;
        paddingY = 2;

        NumBomb = 25;
        TotalBoxes = 80;

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
        Reset = assetManager.StartCollision(rButtonX, rButtonY - 120, 190, 170, false, "Reset");
    }

    public void input()
    {
        if (isActive)
        {
            if (boxX() != -1 && boxY() != -1)
            {
                if (!AlreadyRevealed[boxX()][boxY()])
                {
                    if (flagger && !revealed[boxX()][boxY()])
                    {
                        // -- PLACE BLOCKER --
                        if (!flagged[boxX()][boxY()] && !AlreadyRevealed[boxX()][boxY()])
                        {
                            flagged[boxX()][boxY()] = true;
                        }
                        // -- REMOVE BLOCKER --
                        else
                        {
                            flagged[boxX()][boxY()] = false;
                        }
                    }
                    else
                    {
                        // -- REVEAL BOX --
                        if (!flagged[boxX()][boxY()] &&  !AlreadyRevealed[boxX()][boxY()] && !revealed[boxX()][boxY()])
                        {
                            assetManager.playSE(6);
                            revealed[boxX()][boxY()] = true;
                            AlreadyRevealed[boxX()][boxY()] = true;

                        }
                    }

                    // -- SETS THE BOMB STATUS --
                    if (revealed[boxX()][boxY()] && mines[boxX()][boxY()] == 1)
                    {
                       bomb = true;
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
        for (int i = paddingX; i < cols; i++) {
            for (int j = paddingY; j < rows; j++) {
                int boxX = spacing + i * (boxSize + spacing);
                int boxY = spacing + j * (boxSize + spacing);
                g.drawImage(assetManager.Grass, boxX, boxY, boxSize, boxSize, null);

                if (revealed[i][j])
                {
                    g.drawImage(assetManager.RevGrass,boxX, boxY, boxSize, boxSize, null);

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
                        g.drawImage(assetManager.Bomb, boxX, boxY, 70 - 2 * spacing, 70 - 2 * spacing, null);
                    }
                }

                // - FLAGGED -
                if (flagged[i][j])
                {
                    g.drawImage(assetManager.ActiveBlock, boxX, boxY, boxSize, boxSize, null);
                }

                // - HOVER CURSOR -
                if (isActive)
                {
                    if (boxX() == i && boxY() == j) {
                        g.drawImage(assetManager.Select, boxX, boxY, boxSize, boxSize, null);
                    }
                }
            }
        }

        g.drawImage(assetManager.Restart, rButtonX, rButtonY, 190, 170, null);

        if (Reset) {
            g.drawImage(assetManager.Restart, rButtonX - 10, rButtonY - 5, 210, 185, null);
        }
    }

    public boolean bomb;

    // ----- COLLISION DETECTION -----
    public boolean BoxCollision() {
        int mx = assetManager.gamePanel.getMX();
        int my = assetManager.gamePanel.getMY();

        for (int i = paddingX; i < cols + paddingX; i++) {
            for (int j = paddingY; j < rows + paddingY; j++) {
                int boxLeft = spacing + i * (boxSize + spacing);
                int boxRight = boxLeft + boxSize - spacing;
                int boxTop = spacing + j * (boxSize + spacing);
                int boxBottom = boxTop + boxSize - spacing;

                boolean collisionShape = mx >= boxLeft && mx < boxRight && my >= boxTop && my < boxBottom;

                if (collisionShape) {
                    System.out.println("Collision detected with box: (" + i + ", " + j + ")");
                    return true;
                }
            }
        }
        return false;
    }
    public int boxX() {
        int mx = assetManager.gamePanel.getMX();

        for (int i = paddingX; i < cols; i++) {
            int BoxSize = boxSize + 20;
            int x = 30;
            int boxLeft = spacing + i * (BoxSize + spacing) - x;
            int boxRight = boxLeft + BoxSize - spacing;

            if (mx >= boxLeft && mx < boxRight) {
                return i;
            }
        }
        return -1;
    }
    public int boxY() {
        int my = assetManager.gamePanel.getMY();

        for (int j = paddingY; j < rows; j++) {
            int BoxSize = boxSize + 20;
            int y = 20;
            int boxTop = spacing + j * (BoxSize + spacing) - y;
            int boxBottom = boxTop + BoxSize - spacing;

            if (my >= boxTop && my < boxBottom) {
                return j;
            }
        }
        return -1;
    }

    boolean flagger;
    public boolean isActive;

    private void setNeighbourColor(Graphics2D g, int neighbourCount) {
        switch (neighbourCount) {
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
            g.drawImage(assetManager.Life, 0, 10, 80, 80, null);
            g.drawImage(assetManager.Life, 70, 10, 80, 80, null);
            g.drawImage(assetManager.Life, 140, 10, 80, 80, null);

        }
        else if (life == 2)
        {
            g.drawImage(assetManager.Life, 0, 10, 80, 80, null);
            g.drawImage(assetManager.Life, 70, 10, 80, 80, null);
            g.drawImage(assetManager.NoLife, 140, 10, 80, 80, null);

        }
        else if (life == 1)
        {
            g.drawImage(assetManager.Life, 0, 10, 80, 80, null);
            g.drawImage(assetManager.NoLife, 70, 10, 80, 80, null);
            g.drawImage(assetManager.NoLife, 140, 10, 80, 80, null);

        }
        else if (life <= 0)
        {
            g.drawImage(assetManager.NoLife, 0, 10, 80, 80, null);
            g.drawImage(assetManager.NoLife, 70, 10, 80, 80, null);
            g.drawImage(assetManager.NoLife, 140, 10, 80, 80, null);

        }


        // -- TIMER --
        g.drawImage(assetManager.Button, 1050, -10, 190, 170, null);

        if (victory() == false && life != 0)
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
            assetManager.PrintText("00" + Integer.toString(sec), timeX + 10, timeY + 68, 0, 80, false, g);
        }
        else if (sec < 100)
        {
            assetManager.PrintText("0" + Integer.toString(sec), timeX + 10, timeY + 68, 0, 80, false, g);
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
                if (revealed[i][j] == true)
                {
                    total++;
                }
            }
        }
        return total;
    }
    public boolean victory () {
        if (totalBoxesRevealed() >= TotalBoxes - totalMines()) {
            return true;
        }
        return false;
    }
    public boolean defeat() {
        if (life == 0) {
            return true;
        }
        return false;
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

        isBomb();
    }

    // ----- RANDOMIZES THE BOMBS -----
    public void BombRand()
    {
        for (int i = paddingX; i < cols; i++)
        {
            for (int j = paddingY; j < rows; j++)
            {
                if (rend.nextInt(100) < NumBomb )
                {
                    mines[i][j] = 1;
                }
                else
                {
                    mines[i][j] = 0;
                }
                revealed[i][j] = false;
                AlreadyRevealed[i][j] = false;
            }

        }
    }

    // ----- BOMB CHECKER WHEN CLICKING -----
    public boolean isBomb()
    {
        if (bomb) {
            return true;
        }

        return false;

    }

    // ----- CHECKS EVRY DIRECTION -----
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
                            if (isN(i,j,m,n) == true) {
                                neighs++;
                            }
                        }
                    }
                    neighbours[i][j] = neighs;
                }
            }
        }
    }
    public boolean isN(int mX, int mY, int cX, int cY) {
        if (cX >= 0 && cX < mines.length && cY >= 0 && cY < mines[0].length) {
            // Check if the cell (cX, cY) is within one cell distance from (mX, mY)
            if (Math.abs(mX - cX) <= 1 && Math.abs(mY - cY) <= 1 && mines[cX][cY] == 1) {
                // Ensure we are not checking the same cell
                if (mX != cX || mY != cY) {
                    return true;
                }
            }
        }
        return false;
    }
}

