package gui;

import main.Dungeon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Dungeon dun;
    private boolean shiftPressed;

    public KeyInput(Dungeon d) {
        dun = d;
    }

    private String shoot() {
        return shiftPressed ? "shoot" : "";
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SHIFT)
            shiftPressed = true;

        switch (key) {
            case KeyEvent.VK_W:
                dun.userAction2(shoot() + "w");
                break;
            case KeyEvent.VK_A:
                dun.userAction2(shoot() + "a");
                break;
            case KeyEvent.VK_S:
                dun.userAction2(shoot() + "s");
                break;
            case KeyEvent.VK_D:
                dun.userAction2(shoot() + "d");
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SHIFT)
            shiftPressed = false;
    }
}
