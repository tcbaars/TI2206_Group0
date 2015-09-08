package layers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import enumerations.Key;
import handlers.GameHandler;

public class WinLayer extends Layer{

	private GameHandler game;
	private Color textColor;
	private Font textFont;
	
	public WinLayer(GameHandler game){
		this.game = game;
		textColor = Color.WHITE;
        textFont = new Font("Times New Roman", Font.PLAIN, 16);
	}
	
	private void newGame(){
		addLayer(new GameLayer());
        removeLayer();
	}
	
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Graphics2D draw(Graphics2D g) {
		g.setColor(textColor);
        g.setFont(textFont);
		g.drawString("Win", 20, 20);
		return g;
	}

	@Override
	public void keyPressed(Key e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(Key e) {
		switch (e) {
        case ESC:
	        System.exit(0);
            break;
        case ENTER:
        	newGame();
        	break;
        }
		
	}
	

}
