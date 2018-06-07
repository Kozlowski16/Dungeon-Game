import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
    private Dungeon dun;
    private boolean shiftPressed;
    public KeyInput(Dungeon d){
        dun=d;
    }

    private String shoot(){
        return shiftPressed ? "shoot":"";
    }
    public void keyPressed(KeyEvent e) {
        int key=e.getKeyCode();

        if(key==KeyEvent.VK_SHIFT)
            shiftPressed=true;

        switch (key) {
            case KeyEvent.VK_W:
                dun.userAction(shoot() + "w");
                break;
            case KeyEvent.VK_A:
                dun.userAction(shoot() + "a");
                break;
            case KeyEvent.VK_S:
                dun.userAction(shoot() + "s");
                break;
            case KeyEvent.VK_D:
                dun.userAction(shoot() + "d");
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_SHIFT)
            shiftPressed=false;
    }
}
