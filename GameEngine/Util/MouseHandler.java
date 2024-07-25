package GameEngine.Util;

import GameEngine.States.GameStateManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseMotionListener, MouseListener {

    private static int mouseX = -1;
    private static int mouseY = -1;

    private static int mouseB = -1;

    GameStateManager gameStateManager;
    public MouseHandler(GameStateManager gameStateManager)
    {
        this.gameStateManager = gameStateManager;
    }


    public int getX() {
        return mouseX;
    }

    public int getY() {
        return mouseY;
    }

    public int getButton()
    {
        return mouseB;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gameStateManager.Input();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseB = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseB = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
