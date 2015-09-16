package application;

import gui.MainFrame;
import utils.Logger;

public class Launcher {
    public static void main(String[] args) {
        Logger.info("Launching application!");
        MainFrame.getInstance().display();
        MainFrame.getInstance().setLocationRelativeTo(null);
    }
}