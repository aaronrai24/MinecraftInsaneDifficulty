package me.dollarmc.minecraftinsanedifficulty.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * This class listens for the EntityDamageByEntityEvent in the game.
 */
public class OnPlayerDamage implements Listener {

    private static final Logger LOGGER = LogManager.getLogger(OnPlayerDamage.class);

    /**
     * This method listens for the EntityDamageByEntityEvent.
     * When a player is damaged by a Silverfish, this method will spawn a new Silverfish entity.
     *
     * @param event The EntityDamageByEntityEvent
     */

    @EventHandler
    public static void playerDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getDamager() instanceof Silverfish) {
                LOGGER.info(player.getName() + " was damaged by a Silverfish, spawning Silverfish");
                Silverfish silverfish = (Silverfish) event.getDamager();
                silverfish.getWorld().spawnEntity(silverfish.getLocation(), EntityType.SILVERFISH);
            }
        } else {
            LOGGER.debug("Entity " + event.getEntity().getType() + " skipping damage event.");
        }
    }
}
