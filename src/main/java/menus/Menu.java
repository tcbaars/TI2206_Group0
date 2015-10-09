package menus;

import enumerations.MenuItems;

/**
 * The Menu interface represents the constraints that a menu of at least one menu item must follow.
 * A menu must be able to return the selected menu item.
 */
public interface Menu {

    /**
     * Returns the selected menu item.
     * If the user has selected an available menu item.
     * @return the selected menu item if the user has selected a menu item, otherwise <code>null</code>.
     */
    public MenuItems getSelection();

}
