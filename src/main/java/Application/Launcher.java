package Application;

import GUI.MainFrame;
import Handlers.OptionsHandler;

public class Launcher {
	public static void main(String[] args){
		System.out.println("Launched: ");
		MainFrame.getInstance().display();
	}
}
