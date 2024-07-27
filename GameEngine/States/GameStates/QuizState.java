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

    }

    @Override
    public void input()
    {
        quizManager.input();
    }

    @Override
    public void render(Graphics2D g)
    {
        quizManager.render(g);
    }
}
