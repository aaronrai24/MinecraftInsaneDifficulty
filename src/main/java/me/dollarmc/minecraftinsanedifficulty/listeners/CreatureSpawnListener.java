package me.dollarmc.minecraftinsanedifficulty.listeners;

import me.dollarmc.minecraftinsanedifficulty.entities.SpiderEntity;
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
            Spider spider = (Spider) event.getEntity();
            SpiderEntity spiderEntity = new SpiderEntity(spider);
            spiderEntity.increaseMovementSpeed();
        }
    }
}
