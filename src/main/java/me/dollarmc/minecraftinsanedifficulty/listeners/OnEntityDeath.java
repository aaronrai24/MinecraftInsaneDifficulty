package me.dollarmc.minecraftinsanedifficulty.listeners;

import java.util.Random;

import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class OnEntityDeath implements Listener {
    
    /**
     * This method listens for the EntityDeathEvent.
     * When an EnderDragon dies, this method has a 5% chance to spawn a new EnderDragon.
     *
     * @param event The EntityDeathEvent
     */
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof EnderDragon) {
            Random random = new Random();
            int chance = random.nextInt(100);
            if (chance <= 100) {
                EnderDragon enderDragon = (EnderDragon) event.getEntity();
                enderDragon.getWorld().spawnEntity(enderDragon.getLocation(), EntityType.ENDER_DRAGON);
            }
        }
    }
}
