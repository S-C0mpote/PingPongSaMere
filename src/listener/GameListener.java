package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameListener implements KeyListener {
    private IGameListener iGameListener;

    public GameListener(IGameListener iGameListener){
        this.iGameListener = iGameListener;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        iGameListener.onEvent(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        iGameListener.stopEvent(e);
    }
}
