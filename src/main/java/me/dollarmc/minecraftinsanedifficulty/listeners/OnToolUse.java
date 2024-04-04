package me.dollarmc.minecraftinsanedifficulty.listeners;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

/**
 * This class listens for the PlayerItemDamageEvent in the game.
 * When a player uses a tool, this class will randomly break the tool.
 */
public class OnToolUse implements Listener {

    private static final Logger LOGGER = LogManager.getLogger(OnToolUse.class);
    /**
     * This method listens for the PlayerItemDamageEvent.
     * When a player uses a tool, this method has a 5% chance to break the tool.
     *
     * @param event The PlayerItemDamageEvent
     */

    @EventHandler
    public void onToolUsage(PlayerItemDamageEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance < 5) {
            LOGGER.info("Chance is: " + chance + ", breaking tool");
            item.setAmount((short) 0);
        }
    }
}
