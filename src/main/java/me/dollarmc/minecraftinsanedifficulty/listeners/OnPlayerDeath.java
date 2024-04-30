package me.dollarmc.minecraftinsanedifficulty.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * This class listens for the PlayerDeathEvent in the game.
 */
public class OnPlayerDeath implements Listener {
    private static final Logger LOGGER = LogManager.getLogger(OnPlayerDeath.class);

    /**
     * This method listens for the PlayerDeathEvent.
     * When a player dies by lava, this method will change the death message.
     *
     * @param event The PlayerDeathEvent
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        getDeathMessage(event);
        savePlayersItemsOnDeath(event);
    }

    /**
     * This method saves the player's items in a chest at the location where they died.
     *
     * @param event The PlayerDeathEvent
     */
    public void savePlayersItemsOnDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Location loc = player.getLocation();
        String deathCoords = "You died at X: " + loc.getBlockX() + ", Y: " + loc.getBlockY() + ", Z: " + loc.getBlockZ();
        player.sendMessage(deathCoords);

        // Spawn a chest at the death location
        Block block = loc.getBlock();
        block.setType(Material.CHEST);
        LOGGER.info("Spawned a chest at the death location");
        Chest chest = (Chest) block.getState();
        int numItems = player.getInventory().getSize();
        for (int i = 0; i < 27; i++) {
            chest.getInventory().addItem(player.getInventory().getItem(i));
        }

        if (numItems > 27) {
            Block block2 = loc.clone().add(0, 0, 1).getBlock();
            block2.setType(Material.CHEST);
            LOGGER.info("Spawned a second chest at the death location");
            Chest chest2 = (Chest) block2.getState();
            for (int i = 27; i < numItems; i++) {
                chest2.getInventory().addItem(player.getInventory().getItem(i));
            }
        }

        LOGGER.info("Saved the player's items in the chest");
        event.getDrops().clear();
    }

    /**
     * This method changes the death message when a player dies by lava.
     *
     * @param event The PlayerDeathEvent
     * @return The new death message
     */
    public void getDeathMessage(PlayerDeathEvent event) {
        if (event.getEntity().getLastDamageCause() != null
        && event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.LAVA) {
        event.setDeathMessage(event.getEntity().getName() + " thought they could swim in lava. They couldn't.");
        } else if (event.getEntity().getLastDamageCause() != null
            && event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.DROWNING) {
            event.setDeathMessage(event.getEntity().getName() + " thought they were Michael Phelps and could hold"
                    + "their breath forever. They couldn't.");
        } else if (event.getEntity().getLastDamageCause() != null
            && event.getEntity().getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
            event.setDeathMessage(event.getEntity().getName() + " thought they could play with fire.");
        }
    }
}
