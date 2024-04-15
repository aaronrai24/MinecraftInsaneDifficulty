package me.dollarmc.minecraftinsanedifficulty.utilities;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This class decreases the player's health by a specified amount.
 */
public class HealthDecreaseTask extends BukkitRunnable {

    private final Player player;
    private final double decreaseAmount;

    public HealthDecreaseTask(Player player, double decreaseAmount) {
        this.player = player;
        this.decreaseAmount = decreaseAmount;
    }

    /**
     * This method decreases the player's health by the decreaseAmount.
     */
    @Override
    public void run() {
        if (player.getHealth() - decreaseAmount > 0) {
            player.setHealth(player.getHealth() - decreaseAmount);
        } else {
            player.setHealth(0);

            this.cancel();
        }
    }
}
