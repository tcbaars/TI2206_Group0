package entities;

import java.util.ArrayList;

import entityspawner.BubbleSpawner;
import enumerations.Directions;
import enumerations.GameEntities;
import settings.ScreenSettings;
import sprites.PlayerSprite;
import sprites.Sprite;

public abstract class Player extends EntityBase{

    private PlayerSprite sprite;
    private double localEntityWidth;
    private double localEntityHeight;
    private double scoreIncrementScalingFactor;
    private double movementSpeedScalingFactor;
    private BubbleSpawner bubbles;
    private boolean consumable;
    private int score;
    private int numberFishEaten;

    public Player(GameEntities entity){
        super();
        sprite = new PlayerSprite(entity.getSprite());
        localEntityWidth =  GameEntities.GUPPY.getEntityWidth();
        localEntityHeight  =  GameEntities.GUPPY.getEntityHeight();
        scoreIncrementScalingFactor = GameEntities.GUPPY.getScoreScalingFactor();
        movementSpeedScalingFactor = GameEntities.GUPPY.getMovementSpeedScalingFactor();
        double screenWidth = ScreenSettings.getInstance().getWidth();
        double centreX = (screenWidth / 2) - (getEntityWidth() / 2);
        double screenHeight = ScreenSettings.getInstance().getHeight();
        double centreY = (screenHeight / 2) - (getEntityHeight() / 2);
        sprite.translateSpriteX(centreX);
        sprite.translateSpriteY(centreY);
        consumable = entity.isConsumable();
        score = 0;
        numberFishEaten = 0;
        bubbles = new BubbleSpawner(this);
    }

    public boolean isConsumable() {
        return consumable;
    }

    private void moveUp(){
        double dY = (-1) * getMovementSpeed();
        double y = getEntityY();
        if ((y + dY) < 0) {
            dY = (0 - y);
        }
        translateSpriteY(dY);
    }
    private void moveDown(){
        double dY = getMovementSpeed();
        double y = getEntityY();
        double screenHeight = ScreenSettings.getInstance().getHeight();
        double height = getEntityHeight();
        if ((y + dY) > (screenHeight - height)) {
            dY = (screenHeight - height) - y;
        }
        translateSpriteY(dY);
    }
    private void moveLeft(){
        if (!isEntityFacingLeft()) {
            flipHorizontally();
        }
        double dX = (-1) * getMovementSpeed();
        double x = getEntityX();
        if ((x + dX) < 0) {
            dX = (0 - x);
        }
        translateSpriteX(dX);
    }
    private void moveRight(){
        if (isEntityFacingLeft()) {
            flipHorizontally();
        }
        double dX = getMovementSpeed();
        double x = getEntityX();
        double screenWidth = ScreenSettings.getInstance().getWidth();
        double width = getEntityWidth();
        if ((x + dX) > (screenWidth - width)) {
            dX = (screenWidth - width) - x;
        }
        translateSpriteX(dX);
    }

    public void move(Directions direction) {
        switch (direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            default:
                break;
        }
    }

    public boolean hasSubEntities() {
        if (bubbles != null) {
            return bubbles.isActive();
        }
        return false;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
    protected void setBubbles(BubbleSpawner bubbles){
        this.bubbles = bubbles;
    }
    public ArrayList<Entity> getSubEntities() {
        if (bubbles != null) {
            return bubbles.getEntities();
        }
        return null;
    }

    @Override
    public void update() {
        super.update();
        if (bubbles != null) {
            if (!bubbles.isActive()) {
                bubbles = new BubbleSpawner(this);
            } else {
                bubbles.update();
            }
        }
    }

    public int getCurrentScore(){
        return score;
    }

    public int getNumberFishEaten(){
        return numberFishEaten;
    }
    @Override
    public double getScoreScalingFactor() {
        return scoreIncrementScalingFactor;
    }

    @Override
    public double getMovementSpeedScalingFactor() {
        return movementSpeedScalingFactor;
    }

    @Override
    protected double getLocalEntityWidth() {
        return localEntityWidth;
    }

    @Override
    protected double getLocalEntityHeight() {
        return localEntityHeight;
    }

    private void increaseScore(int scoreIncrement){
        score += scoreIncrement;
    }
    private void increaseSize(double sizeIncrement){
        sprite.incrementSprite(sizeIncrement);
    }
    public boolean consume(Entity entity) {
        if (isAlive()) {
            if (entity != null) {
                if (entity.isAlive()) {
                    if (entity.isConsumable()) {
                        if (intersects(entity)) {
                            if (isLargerThan(entity)) {
                                entity.consumedBy(this);
                                double sizeIncrement = entity.getSizeIncrement();
                                double scoreincrement = entity.getScoreIncrement();
                                increaseSize(sizeIncrement);
                                increaseScore((int)scoreincrement);
                                numberFishEaten++;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean isFull(){
        return sprite.isFull();
    }
}
