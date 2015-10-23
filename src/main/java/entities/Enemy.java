package entities;

import java.util.ArrayList;

import entityspawner.BubbleSpawner;
import enumerations.Directions;
import enumerations.GameEntities;
import settings.ScreenSettings;
import sprites.EnemySprite;
import sprites.Sprite;
import tools.entitytools.Generator;

public abstract class Enemy extends EntityBase{

    private Sprite sprite;
    private BubbleSpawner bubbles;
    private boolean consumable;
    private double localEntityWidth;
    private double localEntityHeight;
    private double scoreIncrementScalingFactor;
    private double movementSpeedScalingFactor;
    private Directions direction;

    public Enemy(GameEntities entity){
        sprite = new EnemySprite(entity.getSprite());
        localEntityWidth = entity.getEntityWidth();
        localEntityHeight = entity.getEntityHeight();
        scoreIncrementScalingFactor = entity.getScoreScalingFactor();
        movementSpeedScalingFactor = entity.getMovementSpeedScalingFactor();
        spawn();
        consumable = entity.isConsumable();
    }

    public double getScoreScalingFactor() {
        return scoreIncrementScalingFactor;
    }

    @Override
    public double getMovementSpeedScalingFactor() {
        return movementSpeedScalingFactor;
    }
    @Override
    public void update() {
        super.update();
        move(direction);
        if (bubbles != null){
            bubbles.update();
        }
    }

    protected double getLocalEntityWidth() {
        return localEntityWidth;
    }

    protected double getLocalEntityHeight() {
        return localEntityHeight;
    }

    protected void spawn(){
        if (Generator.generateBoolean(10)) {
            bubbles  = new BubbleSpawner(this);
        }
        double screenHeight = ScreenSettings.getInstance().getHeight();
        double height = getEntityHeight();
        int y = Generator.generateInteger((int)(screenHeight - (height + 10)));
        sprite.translateSpriteY(y);
        boolean spawnLeft = Generator.generateBoolean();
        if (spawnLeft) {
            spawnLeft();
        } else {
            spawnRight();
        }
    }

    protected void spawnLeft(){
        if (!isEntityFacingLeft()) {
            flipHorizontally();
        }
        double width = getEntityWidth();
        double x = 0 - width;
        sprite.translateSpriteX(x);
        direction = Directions.RIGHT;
    }

    protected void spawnRight(){
        if (isEntityFacingLeft()) {
            flipHorizontally();
        }
        double screenWidth = ScreenSettings.getInstance().getWidth();
        double x = screenWidth;
        sprite.translateSpriteX(x);
        direction = Directions.LEFT;
    }

    public boolean isConsumable() {
        return consumable;
    }
    private void moveUp(){
        double dY = (-1) * getMovementSpeed();
        double y = getEntityY();
        double height = getEntityHeight();
        if (((y - height) + dY) < 0) {
            kill();
        }
        translateSpriteY(dY);
    }
    private void moveDown(){
        double dY = getMovementSpeed();
        double y = getEntityY();
        double screenHeight = ScreenSettings.getInstance().getHeight();
        if ((y + dY) > screenHeight ) {
            kill();
        }
        translateSpriteY(dY);
    }
    private void moveLeft(){
        if (!isEntityFacingLeft()) {
            flipHorizontally();
        }
        double dX = (-1) * getMovementSpeed();
        double x = getEntityX();
        double width = getEntityWidth();
        if (((x + dX) + width) < 0) {
            kill();
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
        if ((x + dX) > screenWidth) {
            kill();
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

    public ArrayList<Entity> getSubEntities() {
        if (bubbles != null) {
            return bubbles.getEntities();
        }
        return null;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public boolean consume(Entity entity) {
        if (isAlive() &&
                entity != null &&
                entity.isAlive() &&
                entity.isConsumable() &&
                intersects(entity) &&
                isLargerThan(entity) &&
                isLargerThan(entity)) {
                    entity.consumedBy(this);
                    return true;
        }

        return false;
    }
}
