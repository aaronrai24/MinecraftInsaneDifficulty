package me.dollarmc.minecraftinsanedifficulty.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * This class listens for the PlayerDeathEvent in the game.
 */
public class OnPlayerDeath implements Listener {

    /**
     * This method listens for the PlayerDeathEvent.
     * When a player dies by lava, this method will change the death message.
     *
     * @param event The PlayerDeathEvent
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity().getLastDamageCause() != null &&
                event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.LAVA) {
            event.setDeathMessage(event.getEntity().getName() + " thought they could swim in lava. They couldn't.");
        }
        else if (event.getEntity().getLastDamageCause() != null &&
                event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.DROWNING) {
            event.setDeathMessage(event.getEntity().getName() + " thought they were Michael Phelps and could hold" +
                    "their breath forever. They couldn't.");
        }
        else if (event.getEntity().getLastDamageCause() != null &&
                event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
            event.setDeathMessage(event.getEntity().getName() + " thought they could play with fire.");
        }
    }
}
