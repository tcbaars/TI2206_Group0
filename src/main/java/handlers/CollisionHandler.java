package handlers;

import entities.Entity;

public class CollisionHandler {

  private static void handlesinglecollison(Entity eater, Entity food) {
    if (eater != null) {
      eater.handleCollision(food);
    }
  }

  public static void handlecollisions(Entity entity1, Entity entity2) {
    handlesinglecollison(entity1, entity2);
    handlesinglecollison(entity2, entity1);
  }

  /**
   * entity list.
   */
  public static void handlecollisions(Entity entity, Entity[] entities) {
    for (int i = 0; i < entities.length; i++) {
      handlecollisions(entity, entities[i]);
    }
  }

/**
 * List list.
 */
  public static void handlecollisions(Entity[] entities1, Entity[] entities2) {
    for (int i = 0; i < entities1.length; i++) {
      handlecollisions(entities1[i], entities2);
    }
  }

}
