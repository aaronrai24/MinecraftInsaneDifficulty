package me.dollarmc.minecraftinsanedifficulty.listeners;

import me.dollarmc.minecraftinsanedifficulty.MinecraftInsaneDifficulty;
import me.dollarmc.minecraftinsanedifficulty.utilities.BreathDecreaseTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

/**
 * This class listens for when a player moves.
 */
public class OnPlayerMove implements Listener {

    private static final Logger LOGGER = LogManager.getLogger(OnPlayerMove.class);

    /**
     * This method is called when a player moves.
     *
     * @param event The PlayerMoveEvent object
     */
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Material blockType = Objects.requireNonNull(event.getTo()).getBlock().getType();
        if (blockType == Material.WATER) {
            if (player.getRemainingAir() == 270) {
                LOGGER.info("{} is in water, decreasing breath", player.getName());
                MinecraftInsaneDifficulty instance = MinecraftInsaneDifficulty.getInstance();
                new BreathDecreaseTask(player, 7, 0.5).runTaskTimer(
                        instance, 0, 1);
            }
        } 
        else if (blockType == Material.ICE) {
            LOGGER.info("{} is on ice, increasing y velocity by 2", player.getName());
            player.setVelocity(player.getLocation().getDirection().multiply(2).setY(1));
        } 
        else if (blockType == Material.PACKED_ICE) {
            LOGGER.info("{} is on packed ice, increasing y velocity by 3", player.getName());
            player.setVelocity(player.getLocation().getDirection().multiply(3).setY(1));
        } 
        else if (blockType == Material.BLUE_ICE) {
            LOGGER.info("{} is on blue ice, increasing y velocity by 4", player.getName());
            player.setVelocity(player.getLocation().getDirection().multiply(4).setY(1));
        } 
        else if (blockType == Material.BEDROCK) {
            LOGGER.info("{} is on bedrock, applying blindness effect", player.getName());
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
        }
    }

}
