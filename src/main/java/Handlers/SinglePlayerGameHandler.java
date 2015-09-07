package Handlers;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Entities.Enemy;
import Entities.Player;
import Enumerations.Direction;

public class SinglePlayerGameHandler extends GameHandler{

	private Player player;
	
	public SinglePlayerGameHandler(){
		super();
		player = new Player();
	}
	
	@Override
	public void update() {
		handleCollisions(player);
		player.update();
		updateEnemies();
	}

	@Override
	public void draw(Graphics2D g) {
		player.draw(g);	
		drawEnemies(g);
	}

	@Override
	public void drawHUD(Graphics2D g) {		
	}

	@Override
	public void move(Direction d) {
		player.move(d);
	}
}
