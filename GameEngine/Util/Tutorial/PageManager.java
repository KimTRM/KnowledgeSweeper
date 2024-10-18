package GameEngine.Util.Tutorial;

import GameEngine.Graphics.AssetManager;
import GameEngine.States.GameStateManager;
import GameEngine.Util.Tutorial.Pages.*;

import java.awt.*;

public class PageManager
{
    public AssetManager assetManager;
    public GameStateManager gameStateManager;

    public Page[] pages;
    public static final int PAGE1 = 0;
    public static final int PAGE2 = 1;
    public static final int PAGE3 = 2;
    public static final int PAGE4 = 3;
    public static final int PAGE5 = 4;
    public static final int PAGE6 = 5;
    public static final int PAGE7 = 6;

    public PageManager(AssetManager assetManager, GameStateManager gameStateManager)
    {
        pages = new Page[6];

        this.assetManager = assetManager;
        this.gameStateManager = gameStateManager;
    }

    public void addPage(int pageNum)
    {
        if (pages[pageNum] != null)
            return;

        switch (pageNum)
        {
            case PAGE1:
                pages[PAGE1] = new Page1(assetManager, gameStateManager);
                break;
            case PAGE2:
                pages[PAGE2] = new Page2(assetManager, gameStateManager);
                break;
            case PAGE3:
                pages[PAGE3] = new Page3(assetManager, gameStateManager);
                break;
            case PAGE4:
                pages[PAGE4] = new Page4(assetManager, gameStateManager);
                break;
            case PAGE5:
                pages[PAGE5] = new Page5(assetManager, gameStateManager);
                break;
            case PAGE6:
                pages[PAGE6] = new Page6(assetManager, gameStateManager);
                break;
            case PAGE7:
                pages[PAGE7] = new Page7(assetManager, gameStateManager);
                break;
        }
    }

    public void removePage(int pageNum)
    {

        pages[pageNum] = null;
    }

    public void addAndRemovePages(int Add, int Remove)
    {
        addPage(Add);
        removePage(Remove);
    }

    public void update()
    {
        for (Page page : pages)
        {
            if (page != null)
                page.update();
        }
    }

    public void input()
    {
        for (Page page : pages)
        {
            if (page != null)
                page.input();
        }
    }

    public void render(Graphics2D g)
    {
        for (Page page : pages)
        {
            if (page != null)
                page.render(g);
        }
    }

}
