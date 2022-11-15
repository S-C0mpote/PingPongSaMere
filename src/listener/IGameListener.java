package listener;

import java.awt.event.KeyEvent;

public interface IGameListener {
    void onEvent(KeyEvent event);
    void stopEvent(KeyEvent event);
}
