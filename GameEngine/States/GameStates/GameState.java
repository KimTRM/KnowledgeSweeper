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
            if (gameBoard.isBomb()) {
                gameStateManager.addState(GameStateManager.QUIZ);
            }
        }

        // -- RESETS THE BOMB STATUS --
        if (gameStateManager.quizManager.Confirm)
        {
            gameBoard.bomb = false;

            // -- REMOVES ONE LIFE --
            if (gameStateManager.quizManager.AnswerWrong)
            {
                gameBoard.life--;
            }

            if (gameBoard.defeat())
            {
                assetManager.playSE(5);
            }
        }

        // -- CHECKS IF THE GAME IS OVER --
        if (gameBoard.defeat() || gameBoard.victory())
        {
            gameStateManager.addState(GameStateManager.ENDING);
        }

        // -- ACTIVATES THE GAME BOARD INPUTS --
        gameBoard.isActive = !gameStateManager.isStateActive(GameStateManager.QUIZ) && !gameStateManager.isStateActive(GameStateManager.ENDING);
    }

    @Override
    public void input()
    {
        if (gameBoard.isActive)
        {
            gameBoard.input();
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        gameBoard.render(g);
        gameBoard.UI(g);
    }
}
