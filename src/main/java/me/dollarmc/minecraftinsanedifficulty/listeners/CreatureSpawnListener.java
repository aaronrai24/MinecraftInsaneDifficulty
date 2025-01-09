package me.dollarmc.minecraftinsanedifficulty.listeners;

import java.util.Objects;
import java.util.Random;

import me.dollarmc.minecraftinsanedifficulty.MinecraftInsaneDifficulty;
import me.dollarmc.minecraftinsanedifficulty.entities.BomberEntity;
import me.dollarmc.minecraftinsanedifficulty.entities.CreeperEntity;
import me.dollarmc.minecraftinsanedifficulty.entities.SpiderEntity;
import me.dollarmc.minecraftinsanedifficulty.entities.ZombieEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;

/**
 * This class listens for the CreatureSpawnEvent in the game.
 * When a creature spawns, this class will increase the movement speed of the
 * Spider entity.
 */
public class CreatureSpawnListener implements Listener {

    private static final Logger LOGGER = LogManager.getLogger(CreatureSpawnListener.class);
    private final MinecraftInsaneDifficulty plugin;

    public CreatureSpawnListener(MinecraftInsaneDifficulty plugin) {
        this.plugin = plugin;
    }

    /**
     * This method listens for the CreatureSpawnEvent.
     * When a creature spawns, this method will create a new entity based on the
     * type of creature.
     *
     * @param event The CreatureSpawnEvent
     */

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        EntityType entity = event.getEntityType();
        if (EntityType.SPIDER.equals(entity)) {
            Spider spider = createSpider(event);
        } else if (EntityType.CREEPER.equals(entity)) {
            Creeper creeper = createCreeper(event);
        } else if (EntityType.ZOMBIE.equals(entity)) {
            Zombie zombie = createZombie(event);
        } else if (EntityType.SKELETON.equals(entity)) {
            Skeleton skeleton = createSkeleton(event);
        } else if (EntityType.WITHER_SKELETON.equals(entity)) {
            WitherSkeleton witherSkeleton = createWitherSkeleton(event);
        }
    }

    /**
     * This method creates a new Spider entity.
     *
     * @param event The CreatureSpawnEvent
     * @return The Spider entity
     */
    public Spider createSpider(CreatureSpawnEvent event) {
        LOGGER.debug("Spider entity spawned");
        Spider spider = (Spider) event.getEntity();
        SpiderEntity spiderEntity = new SpiderEntity(spider);
        spiderEntity.increaseMovementSpeed();
        LOGGER.debug("Spider entity movement speed increased");
        return spider;
    }

    /**
     * This method creates a new Creeper entity.
     *
     * @param event The CreatureSpawnEvent
     * @return The Creeper entity
     */
    public Creeper createCreeper(CreatureSpawnEvent event) {
        LOGGER.debug("Creeper entity spawned");
        Creeper creeper = (Creeper) event.getEntity();
        CreeperEntity.setChargedCreeper(creeper);
        LOGGER.debug("Creeper entity set to be charged");
        return creeper;
    }

    /**
     * This method creates a new Zombie entity.
     *
     * @param event The CreatureSpawnEvent
     * @return The Zombie entity
     */
    public Zombie createZombie(CreatureSpawnEvent event) {
        LOGGER.debug("Zombie entity spawned");
        Zombie zombie = (Zombie) event.getEntity();

        // Add a random chance to create a bomber
        Random rand = new Random();
        if (rand.nextInt(100) < 100) {
            LOGGER.debug("Creating a Bomber entity");
            BomberEntity bomberEntity = new BomberEntity(zombie, plugin);
            bomberEntity.createBomber();
            return zombie;
        }

        ZombieEntity zombieEntity = new ZombieEntity(zombie);
        zombieEntity.setBabyZombie();
        LOGGER.debug("Zombie entity set to be a baby");
        LOGGER.debug("Giving Zombie armor");
        zombieEntity.giveZombieArmor();
        LOGGER.debug("Zombie armor given");
        zombieEntity.setZombieCustomName(zombie);
        return zombie;
    }

    /**
     * This method creates a new Skeleton entity.
     *
     * @param event The CreatureSpawnEvent
     * @return The Skeleton entity
     */
    public Skeleton createSkeleton(CreatureSpawnEvent event) {
        LOGGER.debug("Skeleton entity spawned");
        Skeleton skeleton = (Skeleton) event.getEntity();
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.FLAME, 1);
        Objects.requireNonNull(skeleton.getEquipment()).setItemInMainHand(bow);
        return skeleton;
    }

    /**
     * This method creates a new Wither Skeleton entity.
     *
     * @param event The CreatureSpawnEvent
     * @return The Wither Skeleton entity
     */
    public WitherSkeleton createWitherSkeleton(CreatureSpawnEvent event) {
        LOGGER.debug("Wither Skeleton entity spawned");
        WitherSkeleton witherSkeleton = (WitherSkeleton) event.getEntity();
        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        sword.addEnchantment(Enchantment.KNOCKBACK, 2);
        Objects.requireNonNull(witherSkeleton.getEquipment()).setItemInOffHand(sword);
        Objects.requireNonNull(witherSkeleton.getEquipment()).setItemInMainHand(sword);
        return witherSkeleton;
    }
}
