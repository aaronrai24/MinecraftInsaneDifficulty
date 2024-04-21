package me.dollarmc.minecraftinsanedifficulty.entities;

import org.bukkit.entity.Creeper;
import org.bukkit.event.Listener;

/**
 * This class represents a Creeper entity in the game.
 * It is responsible for increasing the explosion radius of the Creeper.
 */
public class CreeperEntity implements Listener {

    @SuppressWarnings("unused")
    private final Creeper creeper;

    /**
     * This constructor creates a new CreeperEntity object.
     *
     * @param creeper The Creeper object
     */

    public CreeperEntity(Creeper creeper) {
        this.creeper = creeper;
    }

    /**
     * This method increases the explosion radius of the Creeper.
     */
    public static void explodeCreeper(Creeper creeper) {
        creeper.explode();
    }

    /**
     * This method sets the Creeper to be charged.
     *
     * @param creeper The Creeper object
     */
    public static void setChargedCreeper(Creeper creeper) {
        creeper.setPowered(true);
    }

    /**
     * This method sets the fuse ticks of the Creeper.
     *
     * @param creeper The Creeper object
     * @param ticks   The number of ticks for the fuse
     */
    public static void setMaxFuseTicks(Creeper creeper, int ticks) {
        creeper.setMaxFuseTicks(ticks);
    }

    /**
     * This method increases the explosion radius of the Creeper.
     *
     * @param creeper The Creeper object
     * @param radius  The explosion radius
     */
    public static void increaseExplosionRadius(Creeper creeper, int radius) {
        creeper.setExplosionRadius(radius);
    }
}
