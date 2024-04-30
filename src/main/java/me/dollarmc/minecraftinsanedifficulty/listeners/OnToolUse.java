package me.dollarmc.minecraftinsanedifficulty.listeners;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Material;
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
     * This method listens for the PlayerItemDamageEvent in the game.
     * When a player uses a tool, this method will randomly break the tool.
     *
     * @param event The PlayerItemDamageEvent that is triggered when a player uses a
     *              tool.
     */

    @EventHandler
    public void onToolUsage(PlayerItemDamageEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        Material material = item.getType();
        Random random = new Random();
        float chance = random.nextFloat();
        float breakChance;

        switch (material) {
            case WOODEN_SWORD:
            case WOODEN_PICKAXE:
                breakChance = 0.1f;
                break;
            case WOODEN_AXE:
            case WOODEN_SHOVEL:
            case WOODEN_HOE:
                breakChance = 0.1f;
                break;
            case STONE_SWORD:
            case STONE_PICKAXE:
            case STONE_AXE:
            case STONE_SHOVEL:
            case STONE_HOE:
                breakChance = 0.05f;
                break;
            case IRON_SWORD:
            case IRON_PICKAXE:
            case IRON_AXE:
            case IRON_SHOVEL:
            case IRON_HOE:
                breakChance = 0.025f;
                break;
            case GOLDEN_SWORD:
            case GOLDEN_PICKAXE:
            case GOLDEN_AXE:
            case GOLDEN_SHOVEL:
            case GOLDEN_HOE:
                breakChance = 0.0125f;
                break;
            case DIAMOND_SWORD:
            case DIAMOND_PICKAXE:
            case DIAMOND_AXE:
            case DIAMOND_SHOVEL:
            case DIAMOND_HOE:
                breakChance = 0.00625f;
                break;
            case NETHERITE_SWORD:
            case NETHERITE_PICKAXE:
            case NETHERITE_AXE:
            case NETHERITE_SHOVEL:
            case NETHERITE_HOE:
                breakChance = 0.003125f;
                break;
            default:
                breakChance = 0.0f;
        }

        if (chance <= breakChance) {
            LOGGER.info("Chance is " + chance + " and breakChance is " + breakChance);
            item.setAmount(0);
        }
    }
}
