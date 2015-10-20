package entityspawner;

import java.util.ArrayList;

import entities.Entity;

public interface EntitySpawner {
    boolean isActive();
    void update();
    ArrayList<Entity> getEntities();
}
