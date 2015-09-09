package entities;

import animations.Animation;
import enumerations.Direction;
import handlers.OptionsHandler;

import java.awt.Graphics2D;

public class Player extends Entity {


  private final double movespeed = 10;

  private final double entitywidth = 100;
  private final double entityheight = 50;

  private final double spritewidth = 300;
  private final double spriteheight = 300;
  private final String animationkey = "Player";
  private final String animationurl = "/sprites/Player.png";
  private final int numberframes = 2;
  private final int animationdely = 10;

  private final double startscale = 20;
  private final double targetscale = 100;

  private int currentScore;
  private int numberFishEaten;

   /**
    * Player.
    */
  public Player() {
    super();
    currentScore = 0;
    numberFishEaten = 0;
  }

  @Override
   protected void initialiseEntity() {
    entityWidth = entitywidth;
    entityHeight = entityheight;
  }

  @Override
  protected void initialiseSprite() {
    topLeftX = OptionsHandler.getInstance().getWidth() / 2;
    topLeftY = OptionsHandler.getInstance().getHeight() / 2;
    spriteWidth = spritewidth;
    spriteHeight = spriteheight;
    currentScale = startscale;
    targetScale = targetscale;
  }

  @Override
  protected Animation createAnimation() {
    return Animation.createAnimation(animationkey, animationurl, numberframes, (int)spritewidth,
        (int)spriteheight, animationdely);
  }

  @Override
  protected void update() {}

  @Override
  protected void draw(Graphics2D graphic) {}

  public void moveUp() {
    topLeftY = 0;
  }

  public void moveDown() {
    topLeftY = 50;
  }

  public void moveLeft() {
    topLeftX = 0;
  }

  public void moveRight() {
    topLeftX = 150;
  }

  /**
   * Move.
   */
  public void move(Direction direction) {
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
    }
  }

  /**
     * Assuming not null and consumable.
     */
  @Override
  protected void consume(Entity food) {
    numberFishEaten++;
    currentScore += food.consumedBy(this);
  }

  @Override
  protected int consumedBy(Entity eater) {
    kill();
    return 0;
  }

  public boolean isFull() {
    return currentScale >= targetScale;
  }

  public int getScore() {
    return currentScore;
  }



}
