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

    public GameBoard(AssetManager assetManager) {
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

    // ----- GAME DIFFICULTY -----
    public void ChangeDifficulty(String difficulty, boolean activate) {
        if (activate) {
            System.out.println(difficulty + " mode activated");
        }

        String Difficulty = difficulty;

        switch (Difficulty) {
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

    public void render(Graphics2D g) {
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
                if (boxX() == i && boxY() == j)
                {
                    g.drawImage(assetManager.Select, boxX, boxY, boxSize, boxSize, null);
                }
            }
        }

        g.drawImage(assetManager.Restart, 550, -10, 190, 170, null);
    }
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

    public void input()
    {
        if (isActive)
        {
            if (boxX() != -1 && boxY() != -1)
            {
                System.out.println("Selected: " + boxX() + ", " + boxY());

                if ( !AlreadyRevealed[boxX()][boxY()])
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
                }

            }
        }
    }

    boolean flagger;
    public boolean isActive;

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
        if (totalBoxesRevealed() >= TotalBoxes - totalMines() + 3) {
            return true;
        }
        return false;
    }
    public boolean defeat () {



        return false;
    }

    // ----- RESETS ALL -----
    public void ResetAll(int TotalBoxes)
    {
        this.TotalBoxes = TotalBoxes;
//        restarter = true;

        flagger = false;

        startDate = new Date();

//        gp.life = 3;

        BombRand();
        BombCheck();

        isBomb();

//        restarter = false;
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
            }

        }
    }

    // ----- BOMB CHECKER WHEN CLICKING -----
    public boolean isBomb()
    {
        for (int i = paddingX; i < cols; i++)
        {
            for (int j = paddingY; j < rows; j++)
            {
                if (revealed[i][j] == true && mines[i][j] == 1)
                {
                    return true;
                }
            }
        }

        return false;

    }

    // ----- CHECKS EVRY DIRECTION -----
    public void BombCheck() {
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
