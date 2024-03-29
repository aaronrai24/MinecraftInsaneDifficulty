package me.dollarmc.minecraftinsanedifficulty;

import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftInsaneDifficulty extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Minecraft Insane Difficulty has started running");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Minecraft Insane Difficulty has stopped running");
    }
}
