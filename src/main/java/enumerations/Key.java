package enumerations;

import java.awt.event.KeyEvent;

public enum Key {
    UP(KeyEvent.VK_UP), DOWN(KeyEvent.VK_DOWN), LEFT(KeyEvent.VK_LEFT), RIGHT(KeyEvent.VK_RIGHT), MUSIC(
            KeyEvent.VK_M), SOUND(KeyEvent.VK_S), ENTER(KeyEvent.VK_ENTER), ESC(KeyEvent.VK_ESCAPE), DEBUG(KeyEvent.VK_X);

    private int keycode;

    private Key(int keycode) {
        this.keycode = keycode;
    }

    public int getKeyCode() {
        return keycode;
    }
}
