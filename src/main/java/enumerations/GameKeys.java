package enumerations;

import java.awt.event.KeyEvent;

/**
 * The GameKeys enumeration is used as a way to easily reference keyboard key events.
 */
public enum GameKeys {
    UP(KeyEvent.VK_UP),
    DOWN(KeyEvent.VK_DOWN),
    LEFT(KeyEvent.VK_LEFT),
    RIGHT(KeyEvent.VK_RIGHT),
    MUSIC(KeyEvent.VK_M),
    SOUND(KeyEvent.VK_S),
    ENTER(KeyEvent.VK_ENTER),
    ESC(KeyEvent.VK_ESCAPE),
    DEBUG(KeyEvent.VK_X);

    private int keycode;

    /**
     * The properties of the key.
     * @param keycode unique identifier for the key.
     */
    private GameKeys(int keycode){
        this.keycode = keycode;
    }

    /**
     * Returns the identifier for the key.
     * @return the key code.
     */
    public int getKeyCode(){
        return keycode;
    }
}
