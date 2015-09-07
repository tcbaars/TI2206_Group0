package Handlers;

public class OptionsHandler {
	
	private static OptionsHandler instance = new OptionsHandler();
	
	private final int WIDTH = 640;
	private final int HEIGHT = 360;
	private int scale;
	
	private final int TARGET_FPS = 60;
	
	private boolean music;
	private boolean sound;
	
	private int minEnemies;
	private int maxEnemies;
		
	private OptionsHandler(){
		scale = 2;
		music = true;
		sound = true;
		minEnemies = 7;
		maxEnemies = 14;
	}

	public static OptionsHandler getInstance(){
		return instance;
	}
	
	public int getWidth(){
		return WIDTH * scale;
	}
	
	public int getHeight(){
		return HEIGHT * scale;
	}
	
	public void setScale(int scale){
		this.scale = scale;
	}
	
	public int getScale(){
		return scale;
	}
	
	public int getTargetFPS(){
		return TARGET_FPS;
	}
	
	public boolean musicOn(){
		return music;
	}
	
	public void toggleMusic(){
		music = !music;
	}
	
	public boolean soundOn(){
		return sound;
	}
	
	public void toggleSound(){
		sound = !sound;
	}
	
	public void setMinEnemies(int min){
		minEnemies = min;
	}
	
	public int getMinEnemies(){
		return minEnemies;
	}
	
	public void setMaxEnemies(int max){
		maxEnemies = max;
	}
	
	public int getMaxEnemies(){
		return maxEnemies;
	}
	
	
}
