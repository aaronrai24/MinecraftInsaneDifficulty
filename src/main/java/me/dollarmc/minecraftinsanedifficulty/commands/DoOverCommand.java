package me.dollarmc.minecraftinsanedifficulty.commands;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DoOverCommand implements CommandExecutor {

    private static final Logger LOGGER = LogManager.getLogger(DoOverCommand.class);
    private final Plugin plugin;

    public DoOverCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * This method listens for the /restart command.
     * When the command is executed, this method will restart the server and
     * regenerate the world.
     *
     * @param sender  The command sender
     * @param command The command
     * @param label   The command label
     * @param args    The command arguments
     * @return true if the command was executed successfully, false otherwise
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Restarting server and regenerating world...");

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.kickPlayer("Server is restarting. Please rejoin in a few minutes.");
        }

        Bukkit.getScheduler().runTaskLater(plugin, this::restartServer, 100L);

        return true;
    }

    /**
     * This method restarts the server and regenerates the world.
     */
    private void restartServer() {
        File worldContainer = Bukkit.getWorldContainer();
        for (World world : Bukkit.getWorlds()) {
            File worldFolder = new File(worldContainer, world.getName());
            deleteWorldFolder(worldFolder);
        }
        LOGGER.info("Restarting server...");
        Bukkit.spigot().restart();
        LOGGER.info("Server restarted successfully.");
    }

    /**
     * This method deletes the world folder.
     *
     * @param worldFolder The world folder to delete
     */
    private void deleteWorldFolder(File worldFolder) {
        if (worldFolder.exists()) {
            File[] files = worldFolder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        LOGGER.info("Deleting folder: " + file.getName());
                        deleteWorldFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
            worldFolder.delete();
        }
    }
}
