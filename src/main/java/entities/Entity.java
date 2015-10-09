package entities;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public interface Entity {
    public boolean isAlive();
    public void kill();
    public boolean isConsumable();
    public void update();
    public void drawEntityToScreen(Graphics2D screen);
    public double getEntityX();
    public double getEntityY();
    public void translateSpriteX(double dX);
    public void translateSpriteY(double dY);
    public double getEntityWidth();
    public double getEntityHeight();
    public boolean isEntityFacingLeft();
    public void flipHorizontally();
    public Ellipse2D getEntityBoundingBox();
    public double getArea();
    public boolean intersects(Entity entity);
    public boolean isLargerThan(Entity entity);
    public boolean consume(Entity entity);
    public void consumedBy(Entity entity);
    public double getScoreIncrement();
    public double getSizeIncrement();
    public boolean hasSubEntities();
}
