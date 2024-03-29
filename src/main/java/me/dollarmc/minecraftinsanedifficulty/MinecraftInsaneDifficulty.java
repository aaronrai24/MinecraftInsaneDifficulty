package me.dollarmc.minecraftinsanedifficulty;

import me.dollarmc.minecraftinsanedifficulty.listeners.CreatureSpawnListener;
import me.dollarmc.minecraftinsanedifficulty.listeners.OnFireListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This class is the main class for the Minecraft Insane Difficulty plugin.
 * It is responsible for starting and stopping the plugin.
 */

public final class MinecraftInsaneDifficulty extends JavaPlugin {

    /**
     * This method is called when the plugin is enabled.
     */

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Minecraft Insane Difficulty has started running");
        // Register the listeners
        getServer().getPluginManager().registerEvents(new OnFireListener(), this);
        getServer().getPluginManager().registerEvents(new CreatureSpawnListener(), this);
    }

    /**
     * This method is called when the plugin is disabled.
     */

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Minecraft Insane Difficulty has stopped running");
    }
}
