package handlers;

import java.awt.event.KeyEvent;

import enumerations.Key;

public class KeyHandler {

    public static Key convertToKey(KeyEvent keyevent) {
        int code = keyevent.getKeyCode();
        return convertToKey(code);
    }

    /**
     * Convert keycode to key.
     */
    public static Key convertToKey(int keycode) {
        for (Key k : Key.values()) {
            if (keycode == k.getKeyCode()) {
                return k;
            }
        }
        return null;
    }

}
