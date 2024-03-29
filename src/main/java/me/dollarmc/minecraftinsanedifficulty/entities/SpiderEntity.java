package me.dollarmc.minecraftinsanedifficulty.entities;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Spider;

/**
 * This class represents a Spider entity in the game.
 * It is responsible for increasing the movement speed of the Spider.
 */
public class SpiderEntity {

    private final Spider spider;

    /**
     * This constructor creates a new SpiderEntity object.
     *
     * @param spider The Spider object
     */

    public SpiderEntity(Spider spider) {
        this.spider = spider;
    }

    /**
     * This method increases the movement speed of the Spider.
     */

    public void increaseMovementSpeed() {
        AttributeInstance movementSpeed = spider.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        assert movementSpeed != null;
        movementSpeed.setBaseValue(movementSpeed.getBaseValue() * 2.5);
    }
}
