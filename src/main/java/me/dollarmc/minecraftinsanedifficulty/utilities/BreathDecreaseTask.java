package me.dollarmc.minecraftinsanedifficulty.utilities;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This class decreases the player's breath by a specified amount.
 */
public class BreathDecreaseTask extends BukkitRunnable {

    private final Player player;
    private final int decreaseAmount;
    private final double healthDecreaseAmount;

    public BreathDecreaseTask(Player player, int decreaseAmount, double healthDecreaseAmount) {
        this.player = player;
        this.decreaseAmount = decreaseAmount;
        this.healthDecreaseAmount = healthDecreaseAmount;
    }

    /**
     * This method decreases the player's breath by the decreaseAmount.
     */
    @Override
    public void run() {
        if (player.getRemainingAir() - decreaseAmount > 0) {
            player.setRemainingAir(player.getRemainingAir() - decreaseAmount);
        } else {
            player.setRemainingAir(0);
            if (player.getHealth() - healthDecreaseAmount > 0) {
                player.setHealth(player.getHealth() - healthDecreaseAmount);
            } else {
                player.setHealth(0);
                this.cancel();
            }
        }
    }
}
