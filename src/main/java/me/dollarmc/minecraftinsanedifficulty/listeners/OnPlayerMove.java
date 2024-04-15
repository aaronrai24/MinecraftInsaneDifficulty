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

import java.util.Objects;

public class OnPlayerMove implements Listener {

    private static final Logger LOGGER = LogManager.getLogger(OnPlayerMove.class);

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
    }
}
