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

        boolean ShowQuiz = false;
        if (!gameStateManager.isStateActive(GameStateManager.QUIZ))
        {
            if (gameBoard.boxX() != -1 && gameBoard.boxY() != -1)
            {
                // -- TURN ON QUIZ --
                if (gameBoard.revealed[gameBoard.boxX()][gameBoard.boxY()] == true && gameBoard.isBomb() == true) {
                    ShowQuiz = true;
                    gameStateManager.quizManager.Confirm = false;
                }

                // -- TURN OFF QUIZ --
                if (gameStateManager.quizManager.Confirm == true) {
                    gameStateManager.removeState(GameStateManager.QUIZ);
                    ShowQuiz = false;
                }

                // -- QUIZ POPS UP --
                if (ShowQuiz == true && gameStateManager.quizManager.Confirm == false) {
                    gameStateManager.addState(GameStateManager.QUIZ);
                }
            }
        }

        if (gameStateManager.isStateActive(GameStateManager.QUIZ))
        {
            gameBoard.isActive = false;
        }
        else
        {
            gameBoard.isActive = true;
        }
    }

    @Override
    public void input()
    {
        if (gameStateManager.isStateActive(GameStateManager.QUIZ) == false)
        {
            gameBoard.input();
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        gameBoard.render(g);
    }
}
