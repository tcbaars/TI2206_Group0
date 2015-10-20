package keys;

import java.awt.event.KeyEvent;

/**
 * The TypedKey class represents the value of a keyboard key that has been typed by the user.
 * It is used to easily get the value of the key typed.
 * Currently the only values which are allowed are alphanumeric values.
 */
public class TypedKey {

    private char value;

    /**
     * Creates a typed key object based on the specified value typed.
     * @param value the value of the key typed.
     */
    private TypedKey(char value){
        this.value = value;
    }

    /**
     * Returns the value of the typed key.
     * @return the value of the typed key.
     */
    public char getValue(){
        return value;
    }

    /**
     * Creates a typed key object based on the specified key event.
     * A key object is only created if a key is typed.
     * A key object is only created if the typed key's value is alphanumeric.
     * @param keyEvent the key event.
     * @return typed key if the key's value is valid, otherwise <code>null</code>.
     */
    public static TypedKey convertToTypedKey(KeyEvent keyEvent){
        if (keyEvent != null &&
                keyEvent.getID() == KeyEvent.KEY_TYPED &&
                keyEvent.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
                    // Get the Unicode value of the key event
                    char keyEventValue = keyEvent.getKeyChar();
                    // Check if the value is alphanumeric
                    if (Character.isLetter(keyEventValue)) {
                        // Create a new typed key object if the value is alphabetic.
                        return new TypedKey(keyEventValue);
                    } else if (Character.isDigit(keyEventValue)) {
                        // Create a new typed key object if the value is numeric.
                        return new TypedKey(keyEventValue);
                    }
        }
        return null;
    }

}
