package entityspawner;

import java.util.ArrayList;

import entities.Entity;

public interface EntitySpawner {
    public boolean isActive();
    public void update();
    public ArrayList<Entity> getEntities();
}
