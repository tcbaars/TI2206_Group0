package menus;

/**
 * The InputMenu interface represents the constraints that should be applied to layers which contain a menu that expect input.
 * The user should be able to either increment, decrement or set the value of the selected input item.
 */
public interface InputMenu {

    /**
     * Increment the value related to the current selection.
     * If there is a value related to the current selection.
     */
    void incrementSelection();

    /**
     * Decrement the value related to the current selection.
     * If there is a value related to the current selection.
     */
    void decrementSelection();

    /**
     * Set the value related to the current selection.
     * If there is a value related to the current selection.
     * This is used to pass values typed by the user.
     * @param value the value typed.
     */
    void setValue(char value);

}
