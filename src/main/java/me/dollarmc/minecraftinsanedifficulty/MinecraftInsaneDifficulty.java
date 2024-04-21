package me.dollarmc.minecraftinsanedifficulty;

import me.dollarmc.minecraftinsanedifficulty.listeners.CreatureSpawnListener;
import me.dollarmc.minecraftinsanedifficulty.listeners.OnBlockBreak;
import me.dollarmc.minecraftinsanedifficulty.listeners.OnEntityTarget;
import me.dollarmc.minecraftinsanedifficulty.listeners.OnFireListener;
import me.dollarmc.minecraftinsanedifficulty.listeners.OnPlayerDamage;
import me.dollarmc.minecraftinsanedifficulty.listeners.OnPlayerDeath;
import me.dollarmc.minecraftinsanedifficulty.listeners.OnPlayerMove;
import me.dollarmc.minecraftinsanedifficulty.listeners.OnToolUse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class is the main class for the Minecraft Insane Difficulty plugin.
 * It is responsible for starting and stopping the plugin.
 */

public final class MinecraftInsaneDifficulty extends JavaPlugin {

    private static final Logger LOGGER = LogManager.getLogger(MinecraftInsaneDifficulty.class);
    private static MinecraftInsaneDifficulty instance;

    /**
     * This method is called when the plugin is enabled.
     */

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        LOGGER.info("Minecraft Insane Difficulty started up successfully.");
        // Register the listeners
        getServer().getPluginManager().registerEvents(new OnFireListener(), this);
        getServer().getPluginManager().registerEvents(new CreatureSpawnListener(), this);
        getServer().getPluginManager().registerEvents(new OnEntityTarget(this), this);
        getServer().getPluginManager().registerEvents(new OnToolUse(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDamage(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerMove(), this);
        getServer().getPluginManager().registerEvents(new OnBlockBreak(), this);
    }

    /**
     * Returns the instance of the MinecraftInsaneDifficulty class.
     *
     * @return The instance of the MinecraftInsaneDifficulty class.
     */
    public static MinecraftInsaneDifficulty getInstance() {
        return instance;
    }
}
