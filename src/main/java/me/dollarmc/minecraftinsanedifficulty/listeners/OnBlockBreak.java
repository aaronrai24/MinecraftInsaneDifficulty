package me.dollarmc.minecraftinsanedifficulty.listeners;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * This class listens for when a block is broken.
 * If a player breaks a block, there is a chance that a mob will spawn.
 */
public class OnBlockBreak implements Listener {
    
    private static final Logger LOGGER = LogManager.getLogger(OnBlockBreak.class);
    
    private static class SpawnInfo {
        EntityType type;
        int chance;

        SpawnInfo(EntityType type, int chance) {
            this.type = type;
            this.chance = chance;
        }
    }

    private static final Map<Material, List<SpawnInfo>> spawnMap = new HashMap<>();

    static {
        spawnMap.put(Material.DIAMOND_ORE, Arrays.asList(
            new SpawnInfo(EntityType.EVOKER, 10),
            new SpawnInfo(EntityType.WITHER_SKELETON, 5),
            new SpawnInfo(EntityType.WITHER, 1)
        ));
        spawnMap.put(Material.EMERALD_ORE, Arrays.asList(
            new SpawnInfo(EntityType.ENDERMAN, 10),
            new SpawnInfo(EntityType.SHULKER, 5),
            new SpawnInfo(EntityType.ENDER_DRAGON, 1)
        ));
        spawnMap.put(Material.GOLD_ORE, Arrays.asList(
            new SpawnInfo(EntityType.BLAZE, 10),
            new SpawnInfo(EntityType.MAGMA_CUBE, 5),
            new SpawnInfo(EntityType.GHAST, 1)
        ));
        spawnMap.put(Material.IRON_ORE, Arrays.asList(
            new SpawnInfo(EntityType.ZOMBIE, 10),
            new SpawnInfo(EntityType.SKELETON, 5),
            new SpawnInfo(EntityType.CREEPER, 1)
        ));
        spawnMap.put(Material.LAPIS_ORE, Arrays.asList(
            new SpawnInfo(EntityType.WITCH, 10),
            new SpawnInfo(EntityType.VINDICATOR, 5),
            new SpawnInfo(EntityType.PILLAGER, 1)
        ));
        spawnMap.put(Material.REDSTONE_ORE, Arrays.asList(
            new SpawnInfo(EntityType.SPIDER, 10),
            new SpawnInfo(EntityType.CAVE_SPIDER, 5),
            new SpawnInfo(EntityType.SILVERFISH, 1)
        ));
        spawnMap.put(Material.OAK_WOOD, Arrays.asList(
            new SpawnInfo(EntityType.SILVERFISH, 100)
        ));
        spawnMap.put(Material.BIRCH_WOOD, Arrays.asList(
            new SpawnInfo(EntityType.SILVERFISH, 100)
        ));
        spawnMap.put(Material.SPRUCE_WOOD, Arrays.asList(
            new SpawnInfo(EntityType.SILVERFISH, 100)
        ));
        spawnMap.put(Material.JUNGLE_WOOD, Arrays.asList(
            new SpawnInfo(EntityType.SILVERFISH, 100)
        ));
        spawnMap.put(Material.ACACIA_WOOD, Arrays.asList(
            new SpawnInfo(EntityType.SILVERFISH, 100)
        ));
        spawnMap.put(Material.DARK_OAK_WOOD, Arrays.asList(
            new SpawnInfo(EntityType.SILVERFISH, 100)
        ));
        spawnMap.put(Material.JUNGLE_WOOD, Arrays.asList(
            new SpawnInfo(EntityType.SILVERFISH, 100)
        ));
    }

    /**
     * This method is called when a block is broken.
     *
     * @param event The BlockBreakEvent object
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        LOGGER.info("Block broken");
        Material material = event.getBlock().getType();
        List<SpawnInfo> spawns = spawnMap.get(material);

        Location playerLocation = event.getPlayer().getLocation();
        Player player = event.getPlayer();
        Random random = new Random();
        int chance = random.nextInt(100);
        LOGGER.info("{} broke a {} block", player.getName(), material);
        for (SpawnInfo spawn : spawns) {
            if (chance < spawn.chance) {
                LOGGER.info("Random number {} is less than spawn chance {}", chance, spawn.chance);
                int spawnAmount = random.nextInt(10);
                for (int i = 0; i < spawnAmount; i++) {
                    Location spawnLocation = playerLocation.clone().add(5, 0, 0);
                    LOGGER.info("Spawning {} {} at {}", spawnAmount, spawn.type, playerLocation);
                    playerLocation.getWorld().spawnEntity(spawnLocation, spawn.type);
                }
                break;
            }
        }
    }
}