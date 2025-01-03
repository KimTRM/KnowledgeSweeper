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
        if (gameStateManager.gameBoard.defeat()) {
            gameStateManager.lossEnd.update();
        }
    }

    @Override
    public void input()
    {
        if (gameStateManager.isStateActive(GameStateManager.ENDING))
        {
            if (gameStateManager.gameBoard.defeat()) {
                gameStateManager.lossEnd.input();

                // -- RESTARTS THE GAME --
                if (gameStateManager.lossEnd.inRestart) {
                    gameStateManager.gameBoard.Name = false;
                    gameStateManager.removeState(GameStateManager.ENDING);
                }

                // -- GOES BACK TO START --
                if (gameStateManager.lossEnd.inBacktoStart) {
                    gameStateManager.gameBoard.bomb = false;
                    gameStateManager.gameBoard.Name = false;

                    Reset();

                    gameStateManager.removeState(GameStateManager.GAME);
                    gameStateManager.removeState(GameStateManager.QUIZ);
                    gameStateManager.removeState(GameStateManager.ENDING);
                    gameStateManager.addState(GameStateManager.STARTING);
                }
            }
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        if (gameStateManager.gameBoard.defeat()) {
            gameStateManager.lossEnd.render(g);
        }

        gameStateManager.gameBoard.UI(g);
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
