package me.dollarmc.minecraftinsanedifficulty.listeners;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.dollarmc.minecraftinsanedifficulty.MinecraftInsaneDifficulty;

/**
 * This class listens for the EntityDeathEvent.
 * When an EnderDragon dies, this class has a 5% chance to spawn a new
 * EnderDragon.
 */
public class OnEntityDeath implements Listener {

    /**
     * This method listens for the EntityDeathEvent.
     * When an EnderDragon dies, this method has a 5% chance to spawn a new
     * EnderDragon.
     *
     * @param event The EntityDeathEvent
     */
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof EnderDragon) {
            Random random = new Random();
            int chance = random.nextInt(100);
            if (chance <= 3) {
                EnderDragon enderDragon = (EnderDragon) event.getEntity();
                Location location = enderDragon.getLocation();
                World world = enderDragon.getWorld();
                MinecraftInsaneDifficulty instance = MinecraftInsaneDifficulty.getInstance();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        world.spawnEntity(location, EntityType.ENDER_DRAGON);
                    }
                }.runTaskLater(instance, 20);
            }
        }
    }
}
