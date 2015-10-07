package tools.entitytools;

import java.util.Iterator;

import entities.Entity;
import enumerations.GameSounds;
import tools.resourcetools.SoundPlayer;

/**
 * The Collisions is a utility for handling collisions between entities.
 */
public class Collisions {

    /**
     * Handlers the single collision between two entities.
     * @param eater the proposed eater entity.
     * @param food the proposed food entity.
     * @return <code>true</code> if and only if the proposed eater consumed the proposed food, otherwise <code>false</code>.
     */
    private static boolean handleSingleCollision(Entity eater, Entity food){
        if (eater != null) {
            return eater.consume(food);
        }
        return false;
    }

    /**
     * Handles the single collision between two entities.
     * @param entity1 an entity.
     * @param entity2 another entity.
     */
    public static void handleCollisions(Entity entity1, Entity entity2){
        boolean playChomp = true;
        // If entity2 is not consumed by entity1
        if (!handleSingleCollision(entity1, entity2)) {
            // Then try having entity2 consume entity1
            playChomp = handleSingleCollision(entity2, entity1);
        }
        if (playChomp) {
            // The chomp sound is loaded elsewhere
            SoundPlayer.getInstance().playSound(GameSounds.CHOMP);
        }
    }

    /**
     * Handles the collisions between an entity and a list of entities.
     * @param entity an entity.
     * @param entities a list of entities.
     */
    public static void handleCollsions(Entity entity, Iterator<Entity> entities){
        while (entities.hasNext()) {
            handleCollisions(entity, entities.next());
        }
    }

    /**
     * Handles the collisions between two lists of entities.
     * @param entities1 a list of entities.
     * @param entities2 another list of entities.
     */
    public static void handleCollisions(Iterator<Entity> entities1, Iterator<Entity> entities2){
        while (entities1.hasNext()) {
            handleCollsions(entities1.next(), entities2);
        }
    }

}
