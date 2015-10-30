package launcher;

import exceptions.GameException;
import gui.DialogBox;
import gui.MainFrame;
import util.Logger;

/**
 * The Launcher class launches the application.
 */
public class Launcher {

    public static void main(String[] args) {
        try{
            // Display the application's main frame
            Logger.info("Application launched.");
            MainFrame.getInstance().display();
        } catch (GameException e){
            // If an unhandled game error occurred display pop-up box, log, and exit
            Logger.error("GameException Occured: " + e.getMessage());
            DialogBox.displayError(e);
        } catch (Exception e){
            // If an unhandled error occurred log and exit
            Logger.error("Exception Occured: " + e.getMessage());
            MainFrame.getInstance().exit();
        }
    }
}
