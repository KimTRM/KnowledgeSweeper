package GameEngine.States.GameStates;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.States.State;
import GameEngine.Util.Game.QuizManager;

import java.awt.*;

public class QuizState extends State {

    QuizManager quizManager;

    public QuizState(GameStateManager gameStateManager, AssetManager assetManager, QuizManager quizManager)
    {
        super(gameStateManager, assetManager);
        this.quizManager = quizManager;
        quizManager.Timer();
    }

    @Override
    public void update()
    {
        if (gameStateManager.isStateActive(GameStateManager.QUIZ))
        {
            quizManager.update();

            if (quizManager.Confirm)
            {
                quizManager.Confirm = false;
                quizManager.Stoptimer = true;
                gameStateManager.removeState(GameStateManager.QUIZ);
            }

            if (gameStateManager.quizManager.seconds == 0 && gameStateManager.isStateActive(GameStateManager.QUIZ))
            {
                gameStateManager.removeState(GameStateManager.QUIZ);
                gameStateManager.quizManager.checkAnswer();
                gameStateManager.quizManager.Confirm = true;
                gameStateManager.quizManager.random();
            }
        }
    }

    @Override
    public void input()
    {
        if (gameStateManager.isStateActive(GameStateManager.QUIZ))
        {
            quizManager.input();
        }

    }

    @Override
    public void render(Graphics2D g)
    {
        if (gameStateManager.isStateActive(GameStateManager.QUIZ))
        {
            quizManager.render(g);
            gameStateManager.gameBoard.UI(g);
        }
    }
}
