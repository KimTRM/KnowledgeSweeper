package GameEngine.Util.Game;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;

import java.awt.*;
import java.util.Date;
import java.util.Random;

public class GameBoard
{
    AssetManager assetManager;
    GameStateManager gsm;

    public int[][] mines;
    public int[][] neighbours;
    public boolean[][] revealed;
    public boolean[][] AlreadyRevealed;
    public boolean[][] flagged;

    public int neighs;
    Random rend = new Random();
    Date startDate = new Date();

    public GameBoard(AssetManager assetManager, GameStateManager gsm)
    {
        this.assetManager = assetManager;
        this.gsm = gsm;

        BombRand();
    }

    int spacing = 0;
    int boxSize = 70;

    int paddingX;
    int paddingY;

    int cols;
    int rows;
    int NumBomb;
    public int TotalBoxes;
    public String Difficulty;
    int rButtonX = 1120;
    int rButtonY = 650;
    public int life = 3;

    int timeX = 1090;
    int timeY = 40;

    int sec = 0;
    public int Score;

    public boolean Easy;
    public boolean Medium;
    public boolean Hard;
    public int TimerSeconds;

    int xLoc;
    int yLoc;

    // ----- GAME DIFFICULTY -----
    public void ChangeDifficulty(String difficulty, boolean activate)
    {
        if (activate)
        {
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

        NumBomb = 20;
        TotalBoxes = 36;
        TimerSeconds = 61;

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
        TimerSeconds = 31;

        xLoc = 50;
        yLoc = 10;

        init();
    }
    public void HardMode() {
        cols = 14;
        paddingX = 2;

        rows = 7;
        paddingY = 1;

        NumBomb = 25;
        TotalBoxes = 84;
        TimerSeconds = 21;

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

        bombStatus = new int[cols][rows];
        BombChanged = new boolean[cols][rows];
    }

    boolean Reset;
    public boolean Home;
    public void update()
    {
        Reset = assetManager.ArrowCollision(rButtonX, rButtonY, 28, 15, 70, 70, false);
        Home = assetManager.ArrowCollision(1228, 665, 0,0, 70, 70, false);
    }

    public void input() {
        if (isActive) {
            int x = boxX();
            int y = boxY();

            // -- RESETS THE BOARD --
            if (Reset) {
                System.out.println("Reset");
                ResetAll(TotalBoxes);

            }

            if (x != -1 && y != -1 && !AlreadyRevealed[x][y])
            {

                // -- REVEAL BOX --
                if (!flagged[x][y] && !revealed[x][y]) {
                    if (assetManager.getButton() == 1)
                    {
                        revealed[x][y] = true;
                        AlreadyRevealed[x][y] = true;

                        // Call the recursive reveal if the clicked box is empty
                        if (mines[x][y] == 0 && neighbours[x][y] == 0) {
                            revealAdjacentEmpty(x, y);
                        }

                        if (victory()) {
                            assetManager.playSE(4);
                        }
                    }
                }

                if (assetManager.getButton() == 3 && !revealed[x][y]) {
                    flagged[x][y] = !flagged[x][y] && !AlreadyRevealed[x][y];
                }

                // -- SETS THE BOMB STATUS --
                if (revealed[x][y]) {
                    if (mines[x][y] == 1) {
                        assetManager.playSE(7);

                        if (Num > 0) {
                            Num--;
                        }

                        bomb = true;
                    } else {
                        assetManager.playSE(6);
                    }
                }
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


                g.drawImage(AssetManager.Grass,  boxX, boxY, 80 - 2 * spacing, 80 - 2 * spacing, null);

                if (revealed[i][j])
                {
                    g.drawImage(AssetManager.RevGrass,boxX, boxY, 80 - 2 * spacing, 80 - 2 * spacing, null);

                    // - NEIGHBOURS -
                    if (mines[i][j] == 0 && neighbours[i][j] != 0)
                    {
                        g.drawImage(AssetManager.NumBlock,boxX, boxY, 80 - 2 * spacing, 80 - 2 * spacing, null);
                        setNeighbourColor(g, neighbours[i][j]);
                        g.setFont(g.getFont().deriveFont(Font.BOLD, 40F));
                        g.drawString(Integer.toString(neighbours[i][j]), boxX + 30, boxY + 55);
                    }

                    // - BOMBS -
                    else if (mines[i][j] == 1)
                    {
                        if (bombStatus[i][j] == DEACTIVATED)
                        {
                            g.drawImage(AssetManager.Bomb, boxX , boxY - 2, 80 - 2 * spacing, 80 - 2 * spacing, null);
                        }
                        if (bombStatus[i][j] == ACTIVATED)
                        {
                            g.drawImage(AssetManager.BombExploded, boxX, boxY, 80 - 2 * spacing, 80 - 2 * spacing, null);
                        }
                    }
                }

                // - FLAGGED -
                if (flagged[i][j])
                {
                    g.drawImage(AssetManager.ActiveBlock, boxX, boxY, 80 - 2 * spacing, 80 - 2 * spacing, null);
                }

                // - HOVER CURSOR -
                if (isActive)
                {
                    if (boxX() == i && boxY() == j)
                    {
                        g.drawImage(AssetManager.Select, boxX, boxY, 80 - 2 * spacing, 80 - 2 * spacing, null);
                    }
                }
            }
        }

        // -- RESTART GAME --
        g.drawImage(AssetManager.Reset, rButtonX, rButtonY, 60, 60, null);

        // -- HOME BUTTON --
        g.drawImage(AssetManager.Home, 1200,650, 60,60,null);

        // -- HOVER --
        if (isActive)
        {
            if (Reset)
            {
                g.drawImage(AssetManager.Reset, rButtonX - 5, rButtonY -5, 70, 70, null);
            }

            if (Home)
            {
                g.drawImage(AssetManager.Home, 1195,645, 70,70,null);
            }
        }
    }

    public static int ACTIVATED = 1;
    public static int DEACTIVATED = 0;

    int[][] bombStatus;
    boolean[][] BombChanged;
    public void BombStatus()
    {
        for (int i = paddingX; i < cols; i++)
        {
            for (int j = paddingY; j < rows; j++)
            {
                if (mines[i][j] == 1 && revealed[i][j] && gsm.quizManager.AnswerWrong && !BombChanged[i][j])
                {
                    bombStatus[i][j] = ACTIVATED;
                    BombChanged[i][j] = true;
                }

                if (mines[i][j] == 1 && revealed[i][j] && gsm.quizManager.AnswerCorrect && !BombChanged[i][j])
                {
                    bombStatus[i][j] = DEACTIVATED;
                    BombChanged[i][j] = true;
                }
            }
        }
    }

    public boolean bomb;
    public boolean Name;

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

        // -- NUMBER OF BOMBS --
        g.drawImage(AssetManager.Bomb, 10, 637, 80, 80, null);
        g.setColor(new Color(227, 133, 73));
        assetManager.PrintTexts(String.valueOf(NumberOfBombs()), 90, 710, 0, 80, false, g);

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


    public int Num;
    int NumberOfBombs()
    {
        return Num;
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

    public void revealAdjacentEmpty(int x, int y)
    {

        // Mark the current cell as revealed
        AlreadyRevealed[x][y] = true;
        revealed[x][y] = true;

        // If the cell has no neighboring mines, we need to reveal adjacent cells
        if (neighbours[x][y] == 0 ) {

            if (flagged[x][y]) {
                flagged[x][y] = false;
            }

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newX = x + i;
                    int newY = y + j;

                    // Check boundaries and ensure we don't call on the current cell
                    if (newX >= paddingX && newX < cols && newY >= paddingY && newY < rows) {
                        if (!AlreadyRevealed[newX][newY] && mines[newX][newY] == 0 && neighbours[newX][newY] >= 0) {
                            revealAdjacentEmpty(newX, newY); // Recursive call for adjacent empty cells
                        }
                    }
                }
            }
        }
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

        for (int i = paddingX; i < cols; i++) {
            for (int j = paddingY; j < rows; j++) {
                flagged[i][j] = false;
            }
        }

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

