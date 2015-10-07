package gui;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
    private static final MainFrame instance = new MainFrame();
    private boolean displayed;
    private Screen gameScreen;
    private MainFrame(){
        super("Fishy Game");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        displayed = false;
    }
    public static MainFrame getInstance(){
        return instance;
    }

    public void display(){
        if (!displayed) {
            gameScreen = new Screen();
            add(gameScreen);
            setResizable(false);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            addWindowListener(new ScreenWindowAdapter(gameScreen));
        }
    }

    public void exit(){
        gameScreen.exit();
        dispose();
    }

}
