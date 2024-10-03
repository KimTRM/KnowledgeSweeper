package GameEngine.States.GameStates;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.States.State;
import GameEngine.Util.Game.GameBoard;

import java.awt.*;

public class GameState extends State {

    GameBoard gameBoard;

    public GameState(GameStateManager gameStateManager, AssetManager assetManager, GameBoard gameBoard)
    {
        super(gameStateManager, assetManager);

        this.gameBoard = gameBoard;
    }

    @Override
    public void update()
    {
        gameBoard.update();

        if (gameBoard.boxX() != -1 && gameBoard.boxY() != -1)
        {
            // -- MAKES THE QUIZ POP UP --
            if (gameBoard.isBomb() && !gameBoard.defeat() && !gameBoard.victory())
            {
                gameStateManager.addState(GameStateManager.QUIZ);
            }
        }

        // -- RESETS THE BOMB STATUS --
        if (gameStateManager.quizManager.Confirm)
        {
            gameBoard.bomb = false;

            if (gameBoard.defeat())
            {
                assetManager.playSE(5);
            }
        }

        // -- CHECKS IF THE GAME IS OVER --
        if (gameBoard.defeat())
        {
            gameStateManager.addState(GameStateManager.ENDING);
        }


        if (gameBoard.victory() && gameBoard.isActive)
        {
            gameStateManager.gameBoard.Name = true;
            gameStateManager.addState(GameStateManager.PLAYERNAME);
        }

        // -- ACTIVATES THE GAME BOARD INPUTS --
        gameBoard.isActive = !gameStateManager.isStateActive(GameStateManager.QUIZ)
                          && !gameStateManager.isStateActive(GameStateManager.ENDING)
                          && !gameStateManager.isStateActive(GameStateManager.GAMEOPTIONS)
                          && !gameStateManager.isStateActive(GameStateManager.STARTING)
                          && !gameStateManager.isStateActive(GameStateManager.PLAYERNAME);
    }

    @Override
    public void input()
    {
        if (gameBoard.isActive)
        {
            gameBoard.input();

            if (gameBoard.Home && !gameBoard.Name)
            {
                assetManager.playSE(1);

                gameBoard.ResetAll(gameBoard.TotalBoxes);
                gameStateManager.quizManager.clear();
                Reset();

                gameStateManager.removeState(GameStateManager.GAMEOPTIONS);
                gameStateManager.removeState(GameStateManager.GAME);
                gameStateManager.removeState(GameStateManager.QUIZ);
                gameStateManager.removeState(GameStateManager.ENDING);
                gameStateManager.addState(GameStateManager.STARTING);
                gameBoard.Home = false;
            }
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        gameBoard.render(g);
        gameBoard.UI(g);
    }

    void Reset()
    {
        gameStateManager.category.Math = false;
        gameStateManager.category.History = false;
        gameStateManager.category.Science = false;

        gameStateManager.category.inMath = false;
        gameStateManager.category.inHis = false;
        gameStateManager.category.inSci = false;

        gameStateManager.level.Easy = false;
        gameStateManager.level.Mid = false;
        gameStateManager.level.Hard = false;

        gameStateManager.level.inEasy = false;
        gameStateManager.level.inMid = false;
        gameStateManager.level.inHard = false;
        gameStateManager.category.ConfirmCategory = false;
    }
}
