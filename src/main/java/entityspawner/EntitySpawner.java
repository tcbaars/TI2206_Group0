package entityspawner;

import java.util.ArrayList;

import entities.Entity;

/**
 * Interface for a generic EntitySpawner
 */
public interface EntitySpawner {
    boolean isActive();
    void update();
    ArrayList<Entity> getEntities();
}
