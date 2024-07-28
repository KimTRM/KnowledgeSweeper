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
    }

    @Override
    public void update()
    {
        quizManager.update();

        if (quizManager.Confirm == true)
        {
            gameStateManager.removeState(GameStateManager.QUIZ);
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
        }
    }
}
