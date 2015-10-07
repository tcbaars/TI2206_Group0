package keys;

import java.awt.event.KeyEvent;

import enumerations.GameKeys;

/**
 * The Key class represents a keyboard key that has been pressed or released by the user.
 * It is used to easily identify a key that has been pressed or released.
 */
public class Key {

    private GameKeys key;

    /**
     * Creates a key object based on the specified key identifier.
     * @param key identifier of the key pressed or released.
     */
    private Key(GameKeys key){
        this.key = key;
    }

    /**
     * Returns an identifier of the key pressed or released.
     * @return identifier of the key pressed or released
     */
    public GameKeys getKey(){
        return key;
    }

    /**
     * Creates a key object based on the specified key event.
     * A key object is only created if a key is pressed or released.
     * A key object is only created if the key pressed or released is identifiable.
     * A key is identifiable if the key code is defined in the GameKeys enumeration.
     * @param keyEvent the key event.
     * @return the key object if the key pressed or released is an identifiable key, otherwise <code>null</code>.
     */
    public static Key convertToKey(KeyEvent keyEvent){
        if (keyEvent != null) {
            // Check if the key event is not a typed key event
            if (keyEvent.getID() != KeyEvent.KEY_TYPED) {
                int keyEventCode = keyEvent.getKeyCode();
                // Check if the key is identifiable
                for (GameKeys gameKey : GameKeys.values()) {
                    if (gameKey.getKeyCode() == keyEventCode) {
                        // Create a key object if the key is identifiable
                        return new Key(gameKey);
                    }
                }
            }
        }
        return null;
    }

}
