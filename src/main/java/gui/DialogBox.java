package gui;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import exceptions.GameException;

/**
 * The DialogBox class is used to display errors which occur during runtime.
 * A list of errors is kept updated to prevent the same error being displayed over and over.
 */
public class DialogBox {

    private static ArrayList<GameException> displayedErrors = new ArrayList<GameException>();

    /**
     * Displays a severe error.
     * Which means that the error which occurred is so severe that the application cannot run any more,
     * and should be exited.
     * @param error the severe error.
     */
    public static void displayError(GameException error){
        if (!displayedErrors.contains(error)) {
            // Add error to list
            displayedErrors.add(error);
            String message = error.getDescription();
            String title = error.getTitle();
            int messageType = JOptionPane.ERROR_MESSAGE;
            // Display dialog box
            JOptionPane.showMessageDialog(MainFrame.getInstance(), message, title, messageType);
            // Exit application
            MainFrame.getInstance().exit();
        }
    }

    /**
     * Displays a warning.
     * Which is an error that is not so severe that the application cannot run any more.
     * So the process can be resumed once the dialog box has been closed.
     * @param error the warning.
     */
    public static void displayWarning(GameException error){
        if (!displayedErrors.contains(error)) {
            // Add error to list
            displayedErrors.add(error);
            String message = error.getDescription();
            String title = error.getTitle();
            int messageType = JOptionPane.WARNING_MESSAGE;
            // Display warning.
            JOptionPane.showMessageDialog(MainFrame.getInstance(), message, title, messageType);
        }
    }
}
