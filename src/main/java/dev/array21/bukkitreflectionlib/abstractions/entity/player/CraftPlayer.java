package dev.array21.bukkitreflectionlib.abstractions.entity.player;

import dev.array21.bukkitreflectionlib.ReflectionUtil;
import dev.array21.bukkitreflectionlib.exceptions.ReflectException;
import org.bukkit.entity.Player;

/**
 * The NMS version of the Player
 * @param inner The inner object
 */
public record CraftPlayer(Object inner) {
    /**
     * Get an instance for the Player
     * @param player The Player
     * @return The CraftPlayer object of the Player
     * @throws ReflectException
     */
    public static CraftPlayer getInstance(Player player) throws ReflectException {
        try {
            final Class<?> clazz = ReflectionUtil.getBukkitClass("entity.CraftPlayer");
            final Object entityPlayer = ReflectionUtil.invokeMethod(clazz, player, "getHandle");

            return new CraftPlayer(entityPlayer);
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    /**
     * Get an object representing the Player's connection with the server
     * @return Returns the Player's connection
     * @throws ReflectException
     */
    public PlayerConnection getPlayerConnection() throws ReflectException {
        return PlayerConnection.getInstance(this);
    }

    /**
     * Get the Player's interaction manager
     * @return The interaction manager
     * @throws ReflectException
     */
    public PlayerInteractManager getPlayerInteractManager() throws ReflectException {
        return PlayerInteractManager.getInstance(this);
    }

    /**
     * Update the Player's abilities, server side
     * @throws ReflectException
     */
    public void updatePlayerAbilities() throws ReflectException {
        try {
            ReflectionUtil.invokeMethod(this.inner, "updateAbilities");
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    /**
     * Trigger a health update to the player
     * @throws ReflectException
     */
    public void triggerPlayerHealthUpdate() throws ReflectException {
        try {
            ReflectionUtil.invokeMethod(this.inner, "triggerHealthUpdate");
        } catch (Exception e) {
            throw new ReflectException(e);
        }
    }

    /**
     * Get the game profile for the player
     * @return The Player's game profile
     * @throws ReflectException
     */
    public GameProfile getGameProfile() throws ReflectException {
        return GameProfile.getInstance(this);
    }
}
