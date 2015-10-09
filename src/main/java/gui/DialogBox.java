package gui;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import exceptions.GameException;

public class DialogBox {

    private static ArrayList<GameException> displayedErrors = new ArrayList<GameException>();

    public static void displayError(GameException error){
        if (!displayedErrors.contains(error)) {
            displayedErrors.add(error);
            String message = error.getDescription();
            String title = error.getTitle();
            int messageType = JOptionPane.ERROR_MESSAGE;
            JOptionPane.showMessageDialog(MainFrame.getInstance(), message, title, messageType);
            MainFrame.getInstance().exit();
        }
    }

    public static void displayWarning(GameException error){
        if (!displayedErrors.contains(error)) {
            displayedErrors.add(error);
            String message = error.getDescription();
            String title = error.getTitle();
            int messageType = JOptionPane.WARNING_MESSAGE;
            JOptionPane.showMessageDialog(MainFrame.getInstance(), message, title, messageType);
        }
    }
}
