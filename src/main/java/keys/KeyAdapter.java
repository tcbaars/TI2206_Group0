package keys;

import java.awt.event.KeyEvent;

/**
 * The KeyAdapter class converts keyboard key events into a more usable form.
 */
public class KeyAdapter {

    /**
     * Converts key pressed event into a more usable form.
     * Which means that the pressed key event is converted into a key object which makes identifying the key pressed easier.
     * The key event is only convertible if it is not null.
     * The key event is only convertible if it is not a typed key event.
     * The key event is only convertible if it is identifiable.
     * @param keyEvent the key pressed event.
     * @return identifiable key object, if and only if the key pressed event is convertible. Otherwise <code>null</code>.
     */
    public static Key convertKeyPressed(KeyEvent keyEvent){
        return Key.convertToKey(keyEvent);
    }

    /**
     * Converts key released event into a more usable form.
     * Which means that the released key event is converted into a key object which makes identifying the key released easier.
     * The key event is only convertible if it is not null.
     * The key event is only convertible if it in not a typed key event.
     * The key event is only convertible if it is identifiable.
     * @param keyEvent the key released event
     * @return identifiable key object, if and only if the key released event is convertible. Otherwise <code>null</code>.
     */
    public static Key convertKeyReleased(KeyEvent keyEvent){
        return Key.convertToKey(keyEvent);
    }

    /**
     * Converts key typed event into a more usable form.
     * Which means that the typed key event is converted into a typed key object which makes getting the value of the key typed easier.
     * The key event is only convertible if it is not null.
     * The key event is only convertible if it is actually a typed key event.
     * The key event is only convertible if it has an alphanumeric value
     * @param keyEvent the key typed event.
     * @return typed key object, if and only if the key typed event is convertible. Otherwise <code>null</code>.
     */
    public static TypedKey convertKeyTyped(KeyEvent keyEvent){
        return TypedKey.convertToTypedKey(keyEvent);
    }

}
