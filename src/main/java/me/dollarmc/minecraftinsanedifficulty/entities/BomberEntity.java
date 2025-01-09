package me.dollarmc.minecraftinsanedifficulty.entities;

import java.util.Objects;
import me.dollarmc.minecraftinsanedifficulty.MinecraftInsaneDifficulty;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This class represents a Bomber entity in the game.
 * It is responsible for creating a Bomber entity.
 */
public class BomberEntity extends ZombieEntity {

    private final Zombie zombie;
    private final MinecraftInsaneDifficulty plugin;

    /**
     * This constructor creates a new BomberEntity object.
     *
     * @param zombie The Zombie object
     * @param plugin The MinecraftInsaneDifficulty plugin
     */
    public BomberEntity(Zombie zombie, MinecraftInsaneDifficulty plugin) {
        super(zombie);
        this.zombie = zombie;
        this.plugin = plugin;
    }

    /**
     * This method creates a Bomber entity.
     */
    public void createBomber() {
        setBabyZombie();
        setZombieHeath(zombie, 20.0);
        zombie.setCustomName(ChatColor.RED + "Bomber");
        Objects.requireNonNull(zombie.getEquipment()).setItemInMainHand(new ItemStack(Material.FLINT_AND_STEEL, 1));
        zombie.getEquipment().setHelmet(new ItemStack(Material.TNT, 1));

        new BukkitRunnable() {
            @Override
            public void run() {
                if (zombie.isDead()) {
                    return;
                }

                if (zombie.getTarget() == null) {
                    for (Entity entity : zombie.getNearbyEntities(10, 10, 10)) {
                        if (entity instanceof Player) {
                            zombie.setTarget((Player) entity);

                        }
                    }
                }

                zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 120, 2));

            }
        }.runTaskTimer(plugin, 100, 100);
    }
}