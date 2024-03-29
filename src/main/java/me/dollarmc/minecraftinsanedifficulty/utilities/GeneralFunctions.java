package me.dollarmc.minecraftinsanedifficulty.utilities;

import java.util.Objects;
import org.bukkit.Server;

/**
 * This class contains general utility functions.
 */
public class GeneralFunctions {

    /**
     * This method checks if it is day in the game.
     *
     * @param server The server object
     * @return true if it is day, false otherwise
     */

    public static boolean isDay(Server server) {
        long time = Objects.requireNonNull(server.getWorld("world")).getTime();
        return time >= 0 && time < 12300;
    }

    /**
     * This method checks if it is night in the game.
     *
     * @param server The server object
     * @return true if it is night, false otherwise
     */
    public static boolean isNight(Server server) {
        long time = Objects.requireNonNull(server.getWorld("world")).getTime();
        return time >= 12300 && time < 23850;
    }
}
