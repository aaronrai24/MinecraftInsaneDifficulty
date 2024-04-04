package me.dollarmc.minecraftinsanedifficulty;


import me.dollarmc.minecraftinsanedifficulty.listeners.CreatureSpawnListener;
import me.dollarmc.minecraftinsanedifficulty.listeners.OnEntityTarget;
import me.dollarmc.minecraftinsanedifficulty.listeners.OnFireListener;
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
    /**
     * This method is called when the plugin is enabled.
     */

    @Override
    public void onEnable() {
        // Plugin startup logic
        LOGGER.info("Minecraft Insane Difficulty has started running");
        // Register the listeners
        getServer().getPluginManager().registerEvents(new OnFireListener(), this);
        getServer().getPluginManager().registerEvents(new CreatureSpawnListener(), this);
        getServer().getPluginManager().registerEvents(new OnEntityTarget(this), this);
        getServer().getPluginManager().registerEvents(new OnToolUse(), this);
    }

    /**
     * This method is called when the plugin is disabled.
     */

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        LOGGER.info("Minecraft Insane Difficulty has stopped running");
    }
}
