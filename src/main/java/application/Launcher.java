package application;

import gui.MainFrame;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Launched: ");
        MainFrame.getInstance().display();
        MainFrame.getInstance().setLocationRelativeTo(null);
    }
}