package Layers;

import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Enumerations.Key;
import Handlers.OptionsHandler;

public abstract class Layer extends JPanel{

	private Layer prev;
	private Layer next;
	
	private boolean active;
	private boolean visible;
	
	public Layer(){
		super();
		setPreferredSize(new Dimension(OptionsHandler.getInstance().getWidth(), OptionsHandler.getInstance().getHeight()));
		prev = null;
		next = null;
		active = true;
		visible = true;
	}
	
	private void setPrevious(Layer prev){
		this.prev = prev;
	}
	
	public void addLayer(Layer next){
		this.next = next;
		next.setPrevious(this);
	}
	
	public void removeLayer(){
		if(prev != null){
			prev.addLayer(next);
		}
		prev = null;
		next = null;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public void activate(){
		active = true;
	}
	
	public void deactivate(){
		active = false;
	}
	
	public boolean isVisible(){
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	public abstract void update();
	
	protected void updateNext(){
		if(next != null){
			next.update();
		}
	}

	public abstract void draw(Graphics2D g);
	
	protected void drawNext(Graphics2D g){
		if(next != null){
			next.draw(g);
		}
	}
	
	public abstract void keyPressed(Key e);
	public abstract void keyReleased(Key e);
	
	protected void passKeyPress(Key e){
		if(next != null){
			next.keyPressed(e);
		}
	}
	
	protected void passKeyRelease(Key e){
		if(next != null){
			next.keyReleased(e);
		}
	}	
}
