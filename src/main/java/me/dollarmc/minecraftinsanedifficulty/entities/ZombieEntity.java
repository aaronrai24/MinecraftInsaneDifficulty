package me.dollarmc.minecraftinsanedifficulty.entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

/**
 * This class represents a Zombie entity in the game.
 * It is responsible for setting the Zombie to be a baby.
 */
public class ZombieEntity {

    private final Zombie zombie;
    private final Map<Material, ItemStack> armorMap;
    private static final List<String> ZOMBIE_NAMES;

    static {
        try (InputStream in = ZombieEntity.class.getClassLoader().getResourceAsStream(
                "zombie_names.txt")) {
            assert in != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                ZOMBIE_NAMES = reader.lines().collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read zombie names", e);
        }
    }
    /**
     * This constructor creates a new ZombieEntity object.
     *
     * @param zombie The Zombie object
     */

    public ZombieEntity(Zombie zombie) {
        this.zombie = zombie;
        this.armorMap = new HashMap<>();
        for (Material material : new Material[]{
            Material.IRON_HELMET, Material.IRON_CHESTPLATE,
            Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.GOLDEN_HELMET,
            Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS,
            Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_BOOTS, Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE,
            Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS
        }) {
            armorMap.put(material, new ItemStack(material));
        }
    }

    /**
     * This method sets the Zombie to be a baby.
     */
    public void setBabyZombie() {
        zombie.setBaby();
    }

    /**
     * This method gives the Zombie armor.
     */
    public void giveZombieArmor() {
        Random random = new Random();
        int chance = random.nextInt(100);
        if (chance < 1) { // 1% chance
            setArmor(Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE,
                    Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS);
        } else if (chance < 16) { // 15% chance
            setArmor(Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE,
                    Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS);
        } else if (chance < 41) { // 25% chance
            setArmor(Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE,
                    Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS);
        } else { // 59% chance
            setArmor(Material.IRON_HELMET, Material.IRON_CHESTPLATE,
                    Material.IRON_LEGGINGS, Material.IRON_BOOTS);
        }
    }

    /**
     * This method sets the armor of the Zombie.
     *
     * @param helmet The helmet material
     * @param chestplate The chestplate material
     * @param leggings The leggings material
     * @param boots The boots material
     */
    private void setArmor(Material helmet, Material chestplate, Material leggings, Material boots) {
        Objects.requireNonNull(zombie.getEquipment()).setHelmet(armorMap.get(helmet));
        zombie.getEquipment().setChestplate(armorMap.get(chestplate));
        zombie.getEquipment().setLeggings(armorMap.get(leggings));
        zombie.getEquipment().setBoots(armorMap.get(boots));
    }

    /**
     * This method sets the custom name of the Zombie.
     */
    public void setZombieCustomName(Zombie zombie) {
        Random random = new Random();
        int index = random.nextInt(ZOMBIE_NAMES.size());
        zombie.setCustomName(ZOMBIE_NAMES.get(index));
    }
}
