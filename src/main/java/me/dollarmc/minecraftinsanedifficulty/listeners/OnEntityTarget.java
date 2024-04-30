package me.dollarmc.minecraftinsanedifficulty.listeners;

import java.util.Random;
import me.dollarmc.minecraftinsanedifficulty.MinecraftInsaneDifficulty;
import me.dollarmc.minecraftinsanedifficulty.entities.CreeperEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This class listens for the EntityTargetEvent in the game.
 * When a Creeper targets a player, this class will explode the Creeper
 * if the player is within 3 blocks.
 */
public class OnEntityTarget implements Listener {

    private static final Logger LOGGER = LogManager.getLogger(OnEntityTarget.class);
    private final MinecraftInsaneDifficulty plugin;

    /**
     * This constructor creates a new OnEntityTarget object.
     *
     * @param plugin The MinecraftInsaneDifficulty plugin
     */

    public OnEntityTarget(MinecraftInsaneDifficulty plugin) {
        this.plugin = plugin;
    }
    /**
     * This method listens for the EntityTargetEvent.
     * When a Creeper targets a player, this method will explode the Creeper
     * if the player is within 3 blocks.
     *
     * @param event The EntityTargetEvent
     */

    @EventHandler
    public void onCreeperTarget(EntityTargetLivingEntityEvent event) {
        if (event.getEntity() instanceof Creeper && event.getTarget() instanceof Player) {
            Creeper creeper = (Creeper) event.getEntity();
            Player player = (Player) event.getTarget();
            LOGGER.info("Creeper is targeting player");
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (creeper.isDead() || player.isDead() || creeper.getTarget() != player) {
                        LOGGER.info("Canceling explosion of Creeper");
                        this.cancel();
                    } else if (creeper.getLocation().distance(player.getLocation()) <= 4) {
                        LOGGER.info("Creeper is close to player, exploding Creeper");
                        Random random = new Random();
                        int chance = random.nextInt(100);
                        if (chance == 1) {
                            CreeperEntity.increaseExplosionRadius(creeper, 50);
                        }
                        CreeperEntity.explodeCreeper(creeper);
                        this.cancel();
                    }
                }
            }.runTaskTimer(plugin, 20L, 1L);
        }
    }
}
