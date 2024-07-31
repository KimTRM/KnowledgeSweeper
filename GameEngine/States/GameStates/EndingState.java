package GameEngine.States.GameStates;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.States.State;

import java.awt.*;

public class EndingState extends State
{
    public EndingState(GameStateManager gameStateManager, AssetManager assetManager)
    {
        super(gameStateManager, assetManager);
    }

    @Override
    public void update()
    {
        if (gameStateManager.gameBoard.victory()) {
            gameStateManager.victoryEnd.update();
        }
        if (gameStateManager.gameBoard.defeat()) {
            gameStateManager.lossEnd.update();
        }
    }

    @Override
    public void input()
    {
        if (gameStateManager.gameBoard.victory()) {
            gameStateManager.victoryEnd.input();

            // -- RESTARTS THE GAME --
            if(gameStateManager.victoryEnd.inRestart)
            {
                gameStateManager.removeState(GameStateManager.ENDING);
            }

            // -- GOES BACK TO START --
            if (gameStateManager.victoryEnd.inBacktoStart)
            {
                gameStateManager.removeState(GameStateManager.GAME);
                gameStateManager.removeState(GameStateManager.QUIZ);
                gameStateManager.removeState(GameStateManager.ENDING);
                gameStateManager.addState(GameStateManager.STARTING);
            }
        }
        if (gameStateManager.gameBoard.defeat()) {
            gameStateManager.lossEnd.input();

            // -- RESTARTS THE GAME --
            if(gameStateManager.lossEnd.inRestart)
            {
                gameStateManager.removeState(GameStateManager.ENDING);
            }

            // -- GOES BACK TO START --
            if (gameStateManager.lossEnd.inBacktoStart)
            {
                gameStateManager.removeState(GameStateManager.GAME);
                gameStateManager.removeState(GameStateManager.QUIZ);
                gameStateManager.removeState(GameStateManager.ENDING);
                gameStateManager.addState(GameStateManager.STARTING);
            }
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        if (gameStateManager.gameBoard.victory()) {
            gameStateManager.victoryEnd.render(g);
        }
        if (gameStateManager.gameBoard.defeat()) {
            gameStateManager.lossEnd.render(g);
        }

        gameStateManager.gameBoard.UI(g);
    }
}
