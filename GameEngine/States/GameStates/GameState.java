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

    }

    @Override
    public void input()
    {
        if (gameStateManager.isStateActive(GameStateManager.GAME) && !gameStateManager.isStateActive(GameStateManager.STARTING))
        {
            gameBoard.input();
            gameBoard.isActive = true;
        }
    }

    @Override
    public void render(Graphics2D g)
    {
        gameBoard.render(g);
    }
}
