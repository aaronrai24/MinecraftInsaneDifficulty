package me.dollarmc.minecraftinsanedifficulty.listeners;

import me.dollarmc.minecraftinsanedifficulty.MinecraftInsaneDifficulty;
import me.dollarmc.minecraftinsanedifficulty.utilities.HealthDecreaseTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
    public static void playerDamageSilverfish(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getDamager() instanceof Silverfish) {
                LOGGER.info("{} was damaged by a Silverfish, spawning Silverfish", player.getName());
                Silverfish silverfish = (Silverfish) event.getDamager();
                silverfish.getWorld().spawnEntity(silverfish.getLocation(), EntityType.SILVERFISH);
            }
        } else {
            LOGGER.debug("Entity {} skipping damage event.", event.getEntity().getType());
        }
    }

    /**
     * This method listens for the EntityDamageEvent.
     * When a player is damaged by Lava, this method will spawn a new Silverfish entity.
     *
     * @param event The EntityDamageEvent
     */
    @EventHandler
    public static void playerDamageLava(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                MinecraftInsaneDifficulty instance = MinecraftInsaneDifficulty.getInstance();
                LOGGER.info("{} was damaged by Lava, decreasing health", player.getName());
                new HealthDecreaseTask(player, 0.5).runTaskTimer(instance, 0, 1);
            }
        }
    }

    /**
     * This method listens for the EntityDamageByBlockEvent.
     * When a player is damaged by a Cactus, this method will apply a Poison effect to the player.
     *
     * @param event The EntityDamageByBlockEvent
     */
    @EventHandler
    public static void playerDamageCactus(EntityDamageByBlockEvent event) {
        if (event.getDamager().getType() == Material.CACTUS) {
            if (event.getEntity() instanceof Player) {
                Player player = (Player) event.getEntity();
                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 1));
            }
        }
    }
}
