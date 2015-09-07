package Handlers;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Entities.Player;
import Enumerations.Direction;

public abstract class GameHandler {
	
	//private boolean gameWon;
	private boolean gameOver;
	private boolean paused;
	
	protected EnemyHandler enemies;

	public GameHandler(){
		gameOver = false;
		paused = false;
		enemies = new EnemyHandler();
	}
	
	protected void handleCollisions(Player player){
		CollisionHandler.HandleCollisions(player, enemies.getEnemies());
	}
	
	protected void updateEnemies(){
		enemies.update();
	}
	
	protected void drawEnemies(Graphics2D g){
		enemies.draw(g);
	}
	
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void drawHUD(Graphics2D g);
	public abstract void move(Direction d);
}
