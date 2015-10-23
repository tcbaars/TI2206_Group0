package entities.powerups;

import entities.Entity;
import entities.Powerup;
import enumerations.GameEntities;
import settings.PowerupSettings;

/**
 * Created by Adriaan on 23-10-2015.
 */
public class Speedup extends Powerup {
    public Speedup() {
        super(GameEntities.SPEEDUP);
    }

    public void consumedBy(Entity entity) {
        if (entity != null) {
            if (!PowerupSettings.getInstance().powerupIsActive()) {
                PowerupSettings.getInstance().togglePowerup();
            }
            kill();
        }
    }
}
