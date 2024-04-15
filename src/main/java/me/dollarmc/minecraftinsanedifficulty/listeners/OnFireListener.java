package me.dollarmc.minecraftinsanedifficulty.listeners;

import me.dollarmc.minecraftinsanedifficulty.utilities.GeneralFunctions;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;

/**
 * This class listens for the EntityCombustEvent in the game.
 * When an entity is set on fire, this class will ensure that the fire lasts forever
 */
public class OnFireListener implements Listener {

    /**
     * This method listens for the EntityCombustEvent.
     * When an entity is set on fire, this method will ensure that the fire lasts forever
     *
     * @param event The EntityCombustEvent
     */
    @EventHandler
    public void onFire(EntityCombustEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setDuration(99999999);
        }
    }
    /**
     * This method listens for the EntityCombustEvent.
     * When a Skeleton is set on fire, this method will cancel the event if it is day in the game.
     *
     * @param event The EntityCombustEvent
     */

    @EventHandler
    public void cancelSkeletonFire(EntityCombustEvent event) {
        if (event.getEntity().getType().equals(EntityType.SKELETON)) {
            if (GeneralFunctions.isDay(event.getEntity().getServer())) {
                event.setCancelled(true);
            }
        }
    }
}
