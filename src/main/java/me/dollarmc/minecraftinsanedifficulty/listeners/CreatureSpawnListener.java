package me.dollarmc.minecraftinsanedifficulty.listeners;

import me.dollarmc.minecraftinsanedifficulty.entities.CreeperEntity;
import me.dollarmc.minecraftinsanedifficulty.entities.SpiderEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * This class listens for the CreatureSpawnEvent in the game.
 * When a creature spawns, this class will increase the movement speed of the Spider entity.
 */
public class CreatureSpawnListener implements Listener {

    private static final Logger LOGGER = LogManager.getLogger(CreatureSpawnListener.class);
    /**
     * This method listens for the CreatureSpawnEvent.
     * When a creature spawns, this method will increase the movement speed of the Spider entity.
     *
     * @param event The CreatureSpawnEvent
     */
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        EntityType entity = event.getEntityType();
        if (EntityType.SPIDER.equals(entity)) {
            // Increase the movement speed of the Spider entity
            LOGGER.debug("Spider entity spawned");
            Spider spider = (Spider) event.getEntity();
            SpiderEntity spiderEntity = new SpiderEntity(spider);
            spiderEntity.increaseMovementSpeed();
            LOGGER.debug("Spider entity movement speed increased");
        } else if (EntityType.CREEPER.equals(entity)) {
            // Set the Creeper to be charged
            LOGGER.debug("Creeper entity spawned");
            Creeper creeper = (Creeper) event.getEntity();
            CreeperEntity.setChargedCreeper(creeper);
            LOGGER.debug("Creeper entity set to be charged");
        }
    }
}
