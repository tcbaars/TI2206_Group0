package layers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import enumerations.Key;
import handlers.GameHandler;

public class PauseLayer extends Layer{

	private GameHandler game;
	private Color textColor;
	private Font textFont;
	
	public PauseLayer(GameHandler game){
		this.game = game;
		textColor = Color.WHITE;
        textFont = new Font("Times New Roman", Font.PLAIN, 16);
	}
	
	@Override
	protected void update() {
		if(!game.isPaused()){
			removeLayer();
		}
	}

	@Override
	public Graphics2D draw(Graphics2D g) {
		g.setColor(textColor);
        g.setFont(textFont);
		g.drawString("Paused", 20, 20);
		return g;
	}

	@Override
	public void keyPressed(Key e) {
		
	}

	@Override
	public void keyReleased(Key e) {
		 switch (e) {
         case ESC:
             game.resume();
             break;
         }
	}
	
	

}
