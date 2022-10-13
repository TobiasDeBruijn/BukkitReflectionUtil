package dev.array21.bukkitreflectionlib.abstractions.entity.player;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;
import org.bukkit.entity.Player;

/**
 * Utilities for the Player
 */
public class PlayerUtil {

    /**
     * Update the Player's scaled health
     * @param player The Playerd
     * @throws ReflectException
     */
    public static void updatePlayerScaledHealth(Player player) throws ReflectException {
        try {
            ReflectionUtil.invokeMethod(player, "updateScaledHealth");
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    /**
     * Update the Player's inventory
     * @param player The Player
     * @throws ReflectException
     */
    public static void updatePlayerInventory(Player player) throws ReflectException {
        try {
            ReflectionUtil.invokeMethod(player, "updateInventory");
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }
}
