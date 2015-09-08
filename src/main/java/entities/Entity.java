package entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import animations.Animation;

public abstract class Entity {

    private boolean consumable;
    private boolean alive;
    private boolean visible;
    private boolean facingRight;
    
    private Animation animation;
   
    /*
     * sprite dimensions
     */
    protected double spriteWidth;
    protected double spriteHeight;
    
    /*
     * coordinates of top-left corner of sprite
     */
    protected double x;
    protected double y;
    
    /*
     * entity dimensions
     */
    protected double entityWidth;
    protected double entityHeight;
    
    /*
     * scaling information
     */
    protected double targetScale;
    protected double currentScale;    
  
   

    public Entity() {
        consumable = true;
        alive = true;
        visible = true;
        facingRight = true;
        
        initialiseEntity();
        initialiseSprite();
        animation = createAnimation();
    }
    
    
    protected abstract void initialiseEntity();
    protected abstract void initialiseSprite();
    protected abstract Animation createAnimation();
    protected abstract void update();
    protected abstract void draw(Graphics2D g);
    
    public void updateEntity(){
    	if(isAlive()){
    		animation.update();
        	update();
    	}
    }
    
    public void drawEntity(Graphics2D g){
    	if(isAlive()){
    		if(isVisible()){
    			g.drawImage(animation.getCurrentFrame(), getGlobalSpriteX(), getGlobalSpriteY(), getGlobalSpriteWidth(), getGlobalSpriteHeight(), null);
    			draw(g);
    		}
    	}
    }
    
    public boolean isConsumable(){
    	return isAlive() && consumable;
    }
    
    public void setConsumable(boolean consumable){
    	this.consumable = consumable;
    }
    
    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = false;
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public boolean isFacingRight() {
        return facingRight;
    }
    
    public double getScaling(){
    	return currentScale/targetScale;
    }
    
    public int getGlobalSpriteX(){
    	return (int)x;
    }
    
    public int getGlobalSpriteY(){
    	return (int)y;
    }
    
    public int getGlobalEntityX(double centreX){
    	return (int)(centreX - (getGlobalEntityWidth()/2));
    }
    
    public int getGlobalEntityY(double centreY){
    	return (int)(centreY - (getGlobalEntityHeight()/2));
    }
        
    public int getGlobalSpriteWidth(){
    	return (int)(spriteWidth * getScaling());
    }
    
    public int getGlobalSpriteHeight(){
    	return (int)(spriteHeight * getScaling());
    }
    
    public int getGlobalEntityWidth(){
    	return (int)(entityWidth * getScaling());
    }
    
    public int getGlobalEntityHeight(){
    	return (int)(entityWidth * getScaling());
    }
    
    public Rectangle getGlobalSpriteBoundingBox(){
    	Rectangle globalSprite = new Rectangle(getGlobalSpriteX(), getGlobalSpriteY(), getGlobalSpriteWidth(), getGlobalSpriteHeight());
    	return globalSprite;
    }
    
    public Rectangle getGlobalEntityBoundingBox(){
    	Rectangle globalSprite = getGlobalSpriteBoundingBox();
    	double centreX = globalSprite.getCenterX();
    	double centreY = globalSprite.getCenterY();
    	return new Rectangle(getGlobalEntityX(centreX), getGlobalEntityY(centreY), getGlobalEntityWidth(), getGlobalEntityHeight());
    }
    
    public boolean intersects(Entity entity){
    	if(entity != null){
    		Rectangle thisBox = getGlobalEntityBoundingBox();
    		Rectangle thatBox = entity.getGlobalEntityBoundingBox();
    		return thisBox.intersects(thatBox);
    	}
    	return false;
    }
    
    public boolean isLargerThan(Entity entity){
    	if(entity != null){
    		if(getGlobalEntityWidth() < entity.getGlobalEntityWidth()){
    			return false;
    		}
    		if(getGlobalEntityHeight() < entity.getGlobalEntityHeight()){
    			return false;
    		}
    	}
    	return true;
    }
    
    public void handleCollision(Entity entity){
    	if(intersects(entity)){
    		if(isLargerThan(entity)){
    			if(entity.isConsumable()){
    				consume(entity);
    			}
    		} else if(isConsumable()){
    			entity.consume(this);
    		}
    	}
    }
    
    protected abstract void consume(Entity food);
    protected abstract int consumedBy(Entity eater);
    
    
}
