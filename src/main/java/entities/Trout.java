package entities;

import animations.Animation;
import enumerations.Direction;

import java.awt.Graphics2D;
import java.util.Random;

public class Trout extends Enemy {

  private final double movespeed = 10;
  private final double basevalue = 10;

  private final double entitywidth = 100;
  private final double entityheight = 50;

  private final double spritewidth = 300;
  private final double spriteheight = 300;
  private final String animationkey = "Trout";
  private final String animationurl = "/sprites/Trout.png";
  private final int numberframes = 2;
  private final int animationdelay = 10;

  private final double minscale = 20;
  private final double maxscale = 100;

  private Direction movingDirection;

  public Trout() {
      super();
  }

  @Override
  protected void initialiseEntity() {
    entityWidth = entitywidth;
    entityHeight = entityheight;
    moveSpeed = movespeed;
  }

  @Override
  protected void initialiseSprite() {
    generator = new Random(System.currentTimeMillis());
    spriteWidth = spritewidth;
    spriteHeight = spriteheight;
    setRandomSide();
    setRandomDepth();
    setRandomScale(minscale, maxscale);
  }

  @Override
  protected Animation createAnimation() {
    return Animation.createAnimation(animationkey, animationurl, numberframes,
        (int)spritewidth, (int)spriteheight, animationdelay);
  }

  public void setDirection(Direction direction) {
    this.movingDirection = direction;
  }

  @Override
  protected void update() {
    move(movingDirection);
  }

  @Override
  protected void draw(Graphics2D graphics) {}


  public double getBaseValue() {
    return basevalue;
  }

  @Override
  protected void consume(Entity food) {
    food.consumedBy(this);
  }

  @Override
  protected int consumedBy(Entity eater) {
    kill();
    return calculateValue();
  }

}
