package layers;

import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import enumerations.Key;
import handlers.OptionsHandler;

public abstract class Layer extends JPanel {

    private Layer prev;
    private Layer next;

    private boolean active;
    private boolean visible;

    public Layer() {
        super();
        setPreferredSize(
                new Dimension(OptionsHandler.getInstance().getWidth(), OptionsHandler.getInstance().getHeight()));
        prev = null;
        next = null;
        active = true;
        visible = true;
    }

    private void setPrevious(Layer prev) {
        this.prev = prev;
    }

    public void addLayer(Layer layer) {
        this.next = layer;
        if(layer != null){
        	layer.setPrevious(this);
        }
        
    }

    public void removeLayer() {
        if (prev != null) {
            prev.addLayer(next);
        }
        prev = null;
        next = null;
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        active = true;
    }

    public void deactivate() {
        active = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    public void updateLayer(){
    	update();
    	updateNext();
    }
    
    protected abstract void update();
    
    private void updateNext() {
        if (next != null) {
            next.updateLayer();
        }
    }
    
    public void drawLayer(Graphics2D g){
    	if(visible){
    		g = draw(g);
    	}
    	drawNext(g);
    }
    
    protected abstract Graphics2D draw(Graphics2D g);

    private void drawNext(Graphics2D g) {
        if (next != null) {
            next.drawLayer(g);
        }
    }
    
    public void handleKeyPress(Key e){
    	keyPressed(e);
    	passKeyPress(e);
    }
    
    public void handleKeyRelease(Key e){
    	keyReleased(e);
    	passKeyRelease(e);
    }
    
    protected abstract void keyPressed(Key e);
    
    protected abstract void keyReleased(Key e);

    private void passKeyPress(Key e) {
        if (next != null) {
            next.handleKeyPress(e);
        }
    }

    private void passKeyRelease(Key e) {
        if (next != null) {
            next.handleKeyRelease(e);
        }
    }
}
