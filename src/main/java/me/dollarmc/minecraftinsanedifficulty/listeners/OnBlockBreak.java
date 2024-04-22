package me.dollarmc.minecraftinsanedifficulty.listeners;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
        spawnMap.put(Material.ANCIENT_DEBRIS, Arrays.asList(
                new SpawnInfo(EntityType.WARDEN, 30)));
        spawnMap.put(Material.DIAMOND_ORE, Arrays.asList(
                new SpawnInfo(EntityType.EVOKER, 5),
                new SpawnInfo(EntityType.WITHER_SKELETON, 3),
                new SpawnInfo(EntityType.WITHER, 1)));
        spawnMap.put(Material.EMERALD_ORE, Arrays.asList(
                new SpawnInfo(EntityType.ENDERMAN, 5),
                new SpawnInfo(EntityType.SHULKER, 3),
                new SpawnInfo(EntityType.ENDER_DRAGON, 1)));
        spawnMap.put(Material.GOLD_ORE, Arrays.asList(
                new SpawnInfo(EntityType.BLAZE, 5),
                new SpawnInfo(EntityType.MAGMA_CUBE, 3),
                new SpawnInfo(EntityType.GHAST, 1)));
        spawnMap.put(Material.IRON_ORE, Arrays.asList(
                new SpawnInfo(EntityType.ZOMBIE, 5),
                new SpawnInfo(EntityType.SKELETON, 3),
                new SpawnInfo(EntityType.CREEPER, 1)));
        spawnMap.put(Material.LAPIS_ORE, Arrays.asList(
                new SpawnInfo(EntityType.WITCH, 5),
                new SpawnInfo(EntityType.VINDICATOR, 3),
                new SpawnInfo(EntityType.PILLAGER, 1)));
        spawnMap.put(Material.REDSTONE_ORE, Arrays.asList(
                new SpawnInfo(EntityType.SPIDER, 5),
                new SpawnInfo(EntityType.CAVE_SPIDER, 3),
                new SpawnInfo(EntityType.SILVERFISH, 1)));
        spawnMap.put(Material.COAL_ORE, Arrays.asList(
                new SpawnInfo(EntityType.PHANTOM, 5),
                new SpawnInfo(EntityType.VEX, 3),
                new SpawnInfo(EntityType.STRAY, 1)));
        spawnMap.put(Material.OAK_LOG, Arrays.asList(
                new SpawnInfo(EntityType.SILVERFISH, 3)));
        spawnMap.put(Material.BIRCH_LOG, Arrays.asList(
                new SpawnInfo(EntityType.SILVERFISH, 3)));
        spawnMap.put(Material.SPRUCE_LOG, Arrays.asList(
                new SpawnInfo(EntityType.SILVERFISH, 3)));
        spawnMap.put(Material.JUNGLE_LOG, Arrays.asList(
                new SpawnInfo(EntityType.SILVERFISH, 3)));
        spawnMap.put(Material.ACACIA_LOG, Arrays.asList(
                new SpawnInfo(EntityType.SILVERFISH, 3)));
        spawnMap.put(Material.DARK_OAK_LOG, Arrays.asList(
                new SpawnInfo(EntityType.SILVERFISH, 3)));
        spawnMap.put(Material.JUNGLE_LOG, Arrays.asList(
                new SpawnInfo(EntityType.SILVERFISH, 3)));
    }

    /**
     * This method is called when a block is broken.
     *
     * @param event The BlockBreakEvent object
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Material material = event.getBlock().getType();
        List<SpawnInfo> spawns = spawnMap.get(material);

        Location playerLocation = event.getPlayer().getLocation();
        Player player = event.getPlayer();
        Random random = new Random();
        int chance = random.nextInt(50);
        LOGGER.debug("{} broke a {} block", player.getName(), material);
        if (spawns != null) {
            for (SpawnInfo spawn : spawns) {
                if (chance < spawn.chance) {
                    LOGGER.info("Random number {} is less than spawn chance {}", chance,
                            spawn.chance);
                    int spawnAmount = random.nextInt(5);
                    for (int i = 0; i < spawnAmount; i++) {
                        LOGGER.info("Spawning {} {} at {}", spawnAmount, spawn.type,
                                playerLocation);
                        playerLocation.getWorld().spawnEntity(playerLocation, spawn.type);
                    }
                    break;
                }
            }
        }
    }
}