package me.dollarmc.minecraftinsanedifficulty.listeners;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import me.dollarmc.minecraftinsanedifficulty.MinecraftInsaneDifficulty;
import me.dollarmc.minecraftinsanedifficulty.utilities.BreathDecreaseTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

/**
 * This class listens for when a player moves.
 */
public class OnPlayerMove implements Listener {

    private static final Logger LOGGER = LogManager.getLogger(OnPlayerMove.class);
    private Map<Player, BukkitTask> breathDecreaseTasks = new HashMap<>();

    /**
     * This method is called when a player moves.
     *
     * @param event The PlayerMoveEvent object
     */
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location loc = Objects.requireNonNull(event.getTo()).clone();
        loc.setY(loc.getY() - 1);
        Material blockType = loc.getBlock().getType();
        Location headLoc = player.getLocation().add(0, 1, 0);
        Material headBlockType = headLoc.getBlock().getType();
        if (headBlockType == Material.WATER) {
            if (player.getRemainingAir() == 270) {
                LOGGER.info("{} is in water, decreasing breath", player.getName());
                MinecraftInsaneDifficulty instance = MinecraftInsaneDifficulty.getInstance();
                BukkitTask task = new BreathDecreaseTask(player, 5, 0.5).runTaskTimer(
                        instance, 0, 1);
                breathDecreaseTasks.put(player, task);
            }
        } else if (blockType == Material.ICE) {
            LOGGER.info("{} is on ice, increasing y velocity by 2", player.getName());
            player.setVelocity(new Vector(0, 0.75, 0));
        } else if (blockType == Material.PACKED_ICE) {
            LOGGER.info("{} is on packed ice, increasing y velocity by 3", player.getName());
            player.setVelocity(new Vector(0, 1.75, 0));
        } else if (blockType == Material.BLUE_ICE) {
            LOGGER.info("{} is on blue ice, increasing y velocity by 4", player.getName());
            player.setVelocity(new Vector(0, 3, 0));
        } else if (blockType == Material.BEDROCK) {
            LOGGER.info("{} is on bedrock, applying blindness effect", player.getName());
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300, 1));
        } else if (blockType == Material.REDSTONE_ORE) {
            LOGGER.info("{} is on redstone ore, applying slowness effect", player.getName());
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 150, 1));
        } else {
            BukkitTask task = breathDecreaseTasks.get(player);
            if (task != null) {
                LOGGER.info("{} is no longer in water, cancelling breath decrease task", player.getName());
                task.cancel();
                breathDecreaseTasks.remove(player);
            }
        }
    }
}
