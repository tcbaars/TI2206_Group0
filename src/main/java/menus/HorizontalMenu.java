package menus;

/**
 * The HorizontalMenu interface represents constraints that a menu which has a horizontal list of menu items must follow.
 * For example the game over layer may display a high score input screen where the user must be able to select the current letter.
 * The user should be able to navigate the horizontal list of menu items, to select the desired menu item.
 */
public interface HorizontalMenu extends Menu{

    /**
     * Select the menu item in negative horizontal direction.
     */
    public void navigateLeft();

    /**
     * Select the menu item in positive horizontal direction.
     */
    public void navigateRight();

}
