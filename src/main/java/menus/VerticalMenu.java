package menus;

/**
 * The VerticalMenu interface represents constraints that a menu which has a vertical list of menu items must follow.
 * For example the title screen has menu items listed vertically, with each item either above or below.
 * The user should be able to navigate the vertical list of menu items, to select the desired menu item.
 */
public interface VerticalMenu extends Menu{

    /**
     * Select the menu item in negative vertical direction.
     */
    void navigateUp();

    /**
     * Select the menu item in positive vertical direction.
     */
    void navigateDown();

}
